package org.kalimullin.fsraytracer.data;

import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.scene.SceneObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.xpath.*;
import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SceneDataProvider {

    // TODO refactor this class (to factory with interface implementations)
    public SceneDataProvider(File file) {
        try {
            this.document = new SceneParser().getParsedDocument(file);
        } catch (SAXException e) {
            logger.error("There was an error while parsing XML: {}", e);
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(SceneDataProvider.class);
    private XPathFactory xPathFactory = XPathFactory.newInstance();
    private XPath xpath = xPathFactory.newXPath();
    //TODO null handling
    private Document document = null;

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
