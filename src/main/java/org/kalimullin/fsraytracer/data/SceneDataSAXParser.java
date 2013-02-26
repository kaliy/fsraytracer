package org.kalimullin.fsraytracer.data;

import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.scene.PolygonalSceneObject;
import org.kalimullin.fsraytracer.scene.SceneObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class SceneDataSAXParser extends DefaultHandler implements SceneDataProvider {

    // I really don't like the way I wrote this parser. But it's fast. Now i think that Stax will be better for this case :)
    public SceneDataSAXParser(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Error while parsing: ", e);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(SceneDataSAXParser.class);
    private Set<SceneObject> sceneObjectSet = new HashSet<>();
    private Integer currentId = null;
    private Point currentPoint = null;
    private Set<Point> currentFacePoints = null;
    private Map<Integer, Point> pointMap = new HashMap<>();
    private Map<Integer, Polygon> polygonMap = new HashMap<>();
    private String currentElementValue;

    private String currentObjectName;
    private Set<Polygon> polygonSet = new HashSet<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // main objects
        if ("point".equalsIgnoreCase(qName))
            currentPoint = new Point();
        else if ("face".equalsIgnoreCase(qName))
            currentFacePoints = new HashSet<>();
        else if ("id".equalsIgnoreCase(qName))
            currentId = null;
        else if ("object".equalsIgnoreCase(qName))
            polygonSet = new HashSet<>();
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        currentElementValue = new String(ac, i, j).trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("id".equalsIgnoreCase(qName))
            currentId = Integer.parseInt(currentElementValue);
        // Point parsing
        else if ("x".equalsIgnoreCase(qName))
            currentPoint.setX(Double.parseDouble(currentElementValue));
        else if ("y".equalsIgnoreCase(qName))
            currentPoint.setY(Double.parseDouble(currentElementValue));
        else if ("z".equalsIgnoreCase(qName))
            currentPoint.setZ(Double.parseDouble(currentElementValue));
        else if ("point".equalsIgnoreCase(qName))
            pointMap.put(currentId, currentPoint);
        // Face parsing
        else if ("point-id".equalsIgnoreCase(qName))
            currentFacePoints.add(pointMap.get(Integer.parseInt(currentElementValue)));
        else if ("face".equalsIgnoreCase(qName))
            polygonMap.put(currentId, new Polygon(new Face(currentFacePoints)));
        // SceneObject parsing
        else if ("face-id".equalsIgnoreCase(qName))
            polygonSet.add(polygonMap.get(Integer.parseInt(currentElementValue)));
        else if ("name".equalsIgnoreCase(qName))
            currentObjectName = currentElementValue;
        else if ("object".equalsIgnoreCase(qName)) {
            sceneObjectSet.add(new PolygonalSceneObject(currentObjectName, polygonSet));
        }
    }

    @Override
    public Set<SceneObject> getSceneObjects() {
        return sceneObjectSet;
    }
}
