package org.kalimullin.fsraytracer.data;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.scene.PolygonalSceneObject;
import org.kalimullin.fsraytracer.scene.SceneObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SceneDataXpathParser implements SceneDataProvider {

    // TODO refactor this class (to factory with interface implementations)
    public SceneDataXpathParser(File file) {
        try {
            logger.info("Initializing XML Parsing");
            this.document = getParsedDocument(file);
        } catch (SAXException e) {
            logger.error("There was an error while parsing XML: ", e);
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(SceneDataProvider.class);
    //TODO switch to SAX parser. XPath is not necessary for this structure
    private XPathFactory xPathFactory = XPathFactory.newInstance();
    private XPath xpath = xPathFactory.newXPath();
    //TODO null handling
    private Document document = null;

    private void validate(File file) throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("scene.xsd"));
        Validator validator = schema.newValidator();
        try {
            // Actually double-creating StreamSource can cause performance problems while parsing really big files.
            // If this is really big deal, this method should be inlined
            validator.validate(new StreamSource(file));
        } catch (IOException e) {
            // TODO somehow get rid of IOException by refactoring all Source-related operations
            logger.error("IOException while parsing XML: ", e);
        }
    }

    /**
     * Parsing Scene XML with validation.
     * @param file input XML
     * @return parsed Document
     * @throws SAXException if XML is invalid
     */
    private Document getParsedDocument(File file) throws SAXException {
        validate(file);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(file);
        } catch (ParserConfigurationException | IOException e) {
            logger.error("Exception while parsing file {}:", file, e);
        }
        return new DOMParser().getDocument();
    }


    /**
     * Parsing scene objects from XML.
     * @return List<SceneObject> with PolygonalSceneObject
     */
    public Set<SceneObject> getSceneObjects() {
        Set<SceneObject> sceneObjectSet = new HashSet<>();
        Map<Integer, Polygon> polygonMap = getPolygons();
        try {
            XPathExpression xPathExpression = xpath.compile("/scene/objects/object");
            NodeList objects = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            logger.trace("Objects NodeList size: {}", objects.getLength());
            for (int facesIndex = 0; facesIndex < objects.getLength(); facesIndex++) {
                Node faceNode = objects.item(facesIndex);
                String name = (String)xpath.evaluate("name", faceNode, XPathConstants.STRING);
                int polygonsCount = ((Number)xpath.evaluate("count(face-id)", faceNode, XPathConstants.NUMBER)).intValue();
                Set<Polygon> polygonSet = new HashSet<>();
                for(int polygonIndex = 1; polygonIndex <= polygonsCount; polygonIndex++) {
                    polygonSet.add(polygonMap.get(((Number) xpath.evaluate("(face-id)[" + polygonIndex + "]",
                            faceNode, XPathConstants.NUMBER)).intValue()));
                }
                PolygonalSceneObject polygonalSceneObject = new PolygonalSceneObject(name, polygonSet);
                sceneObjectSet.add(polygonalSceneObject);
                logger.debug("Added SceneObject[{}]: {}", name , polygonalSceneObject);
            }
        } catch (XPathExpressionException e) {
            logger.error("Wrong XPath expression:", e);
        }
        logger.info("SceneObjects have been parsed");
        return sceneObjectSet;
    }

    /**
     * Parsing points list
     * @return Map<Integer, Point> where Integer - point/id
     */
    private Map<Integer, Point> getPoints() {
        Map<Integer, Point> pointMap = new HashMap<>();
        try {
            XPathExpression xPathExpression = xpath.compile("/scene/points/point");
            NodeList points = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            logger.trace("Points NodeList size: {}", points.getLength());
            for (int pointsIndex = 0; pointsIndex < points.getLength(); pointsIndex++) {
                Node pointNode = points.item(pointsIndex);
                pointNode.getParentNode().removeChild(pointNode);
                int id = ((Number)xpath.evaluate("id", pointNode, XPathConstants.NUMBER)).intValue();
                double x = ((Number)xpath.evaluate("x", pointNode, XPathConstants.NUMBER)).doubleValue();
                double y = ((Number)xpath.evaluate("y", pointNode, XPathConstants.NUMBER)).doubleValue();
                double z = ((Number)xpath.evaluate("z", pointNode, XPathConstants.NUMBER)).doubleValue();
                logger.trace("Point {}: ID={}, x={}, y={}, z={}", pointsIndex, id, x, y, z);
                Point point = new Point(x, y, z);
                pointMap.put(id, point);
                logger.debug("Added Point[{}]: {}", id, point);
            }
        } catch (XPathExpressionException e) {
            logger.error("Wrong XPath expression:", e);
        }
        logger.info("Points have been parsed");
        return pointMap;
    }

    /**
     * Parsing faces list
     * @return Map<Integer, Face> where Integer - face/id
     */
    private Map<Integer, Face> getFaces() {
        Map<Integer, Face> faceMap = new HashMap<>();
        Map<Integer, Point> pointMap = getPoints();
        try {
            XPathExpression xPathExpression = xpath.compile("/scene/faces/face");
            NodeList faces = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            logger.trace("Faces NodeList size: {}", faces.getLength());
            for (int facesIndex = 0; facesIndex < faces.getLength(); facesIndex++) {
                Node faceNode = faces.item(facesIndex);
                faceNode.getParentNode().removeChild(faceNode);
                int id = ((Number)xpath.evaluate("id", faceNode, XPathConstants.NUMBER)).intValue();
                int pointsCount = ((Number)xpath.evaluate("count(point-id)", faceNode, XPathConstants.NUMBER)).intValue();
                Set<Point> pointSet = new HashSet<>();
                if (pointsCount == 3) {
                    for(int pointsIndex = 1; pointsIndex <= pointsCount; pointsIndex++) {
                        pointSet.add(pointMap.get(((Number)xpath.evaluate("(point-id)[" + pointsIndex + "]",
                                faceNode, XPathConstants.NUMBER)).intValue()));
                    }
                }
                Face face = new Face(pointSet);
                faceMap.put(id, face);
                logger.debug("Added Face[{}]: {}", id, face);
            }
        } catch (XPathExpressionException e) {
            logger.error("Wrong XPath expression:", e);
        }
        logger.info("Faces have been parsed");
        return faceMap;
    }

    /**
     * Parsing polygons. In our XML all polygons are faces.
     * @return Map<Integer, Polygon> where Integer - face/id
     */
    private Map<Integer, Polygon> getPolygons() {
        Map<Integer, Polygon> polygonMap = new HashMap<>();
        Map<Integer, Face> faceMap = getFaces();
        for (Integer key: faceMap.keySet()) {
            Polygon polygon = new Polygon(faceMap.get(key));
            polygonMap.put(key, polygon);
            logger.debug("Added Polygon[{}]: {}", key, polygon);
        }
        return polygonMap;
    }

}
