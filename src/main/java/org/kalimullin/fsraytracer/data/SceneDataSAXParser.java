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
        switch (qName.toLowerCase()) {
            case "point":
                currentPoint = new Point();
                break;
            case "face":
                currentFacePoints = new HashSet<>();
                break;
            case "id":
                currentId = null;
                break;
            case "object":
                polygonSet = new HashSet<>();
                break;
        }
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        currentElementValue = new String(ac, i, j).trim();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "id":
                currentId = Integer.parseInt(currentElementValue);
                break;
            // Point parsing
            case "x":
                currentPoint.setX(Double.parseDouble(currentElementValue));
                break;
            case "y":
                currentPoint.setY(Double.parseDouble(currentElementValue));
                break;
            case "z":
                currentPoint.setZ(Double.parseDouble(currentElementValue));
                break;
            case "point":
                pointMap.put(currentId, currentPoint);
                break;
            // Face parsing
            case "point-id":
                currentFacePoints.add(pointMap.get(Integer.parseInt(currentElementValue)));
                break;
            case "face":
                polygonMap.put(currentId, new Polygon(new Face(currentFacePoints)));
                break;
            // SceneObject parsing
            case "face-id":
                polygonSet.add(polygonMap.get(Integer.parseInt(currentElementValue)));
                break;
            case "name":
                currentObjectName = currentElementValue;
                break;
            case "object":
                sceneObjectSet.add(new PolygonalSceneObject(currentObjectName, polygonSet));
                break;
        }
    }

    @Override
    public Set<SceneObject> getSceneObjects() {
        return sceneObjectSet;
    }
}
