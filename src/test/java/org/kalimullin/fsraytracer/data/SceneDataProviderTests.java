package org.kalimullin.fsraytracer.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.scene.PolygonalSceneObject;
import org.kalimullin.fsraytracer.scene.SceneObject;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SceneDataProviderTests extends Assert {
    private Set<SceneObject> sceneObjectSet;

    @Before
    public void setUp() {
        sceneObjectSet = new HashSet<>();
        Point point1 = new Point(-1.8, -1.8, 0);
        Point point2 = new Point(1.8, -1.8, 0);
        Point point3 = new Point(0, 1.8, 0);
        Point point4 = new Point(0, 0, 1.2);
        Face face1 = new Face(point1, point2, point3);
        Face face2 = new Face(point1, point2, point4);
        Face face3 = new Face(point1, point4, point3);
        Face face4 = new Face(point4, point3, point2);
        Polygon polygon1 = new Polygon(face1);
        Polygon polygon2 = new Polygon(face2);
        Polygon polygon3 = new Polygon(face3);
        Polygon polygon4 = new Polygon(face4);
        sceneObjectSet.add(new PolygonalSceneObject("pyramid",
                new HashSet<>(Arrays.asList(polygon1, polygon2, polygon3, polygon4))));
    }

    @Test
    public void testParsingData() {
        assertEquals(sceneObjectSet, new SceneDataProvider(new File("src/test/resources/simpleScene.xml")).getSceneObjects());
    }
}
