package org.kalimullin.fsraytracer.data;

import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.scene.SceneObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneDataProvider {

    public SceneDataProvider(File file) {
        try {
            this.document = new SceneParser().getParsedDocument(file);
        } catch (SAXException e) {
            logger.error("There was an error while parsing XML: {}", e);
        }
    }

    private final static Logger logger = LoggerFactory.getLogger(SceneDataProvider.class);

    //TODO improve null handling
    private Document document = null;

    public List<SceneObject> getSceneObjects() {
        return new ArrayList<>();
    }

    private Map<Integer, Point> getPoints() {
        return new HashMap<>();
    }

    private Map<Integer, Face> getFaces() {
        return new HashMap<>();
    }

}
