package org.kalimullin.fsraytracer.scene;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kalimullin.fsraytracer.data.SceneDataProvider;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.ray.HitData;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.io.File;

public class SceneTest extends Assert {

    Scene scene;

    @Before
    public void setUp() {
        scene = new Scene();
        scene.setSceneObjects(new SceneDataProvider(new File("src/test/resources/simpleScene.xml")).getSceneObjects());
    }

    @Test
    public void testRayTraceHit() {
        // test data has only one object
        SceneObject pyramid = (SceneObject) scene.getSceneObjects().toArray()[0];
        HitData expectedHitData = new HitData(new HitPoint(new Point(-0.5, -0.5, 0), Math.sqrt(9 * 3)), pyramid);
        assertEquals(expectedHitData, scene.traceRay(new Ray(new Point(-3.5, -3.5, -3), new Point(1, 1, 1))));
    }

    @Test
    public void testRayMiss() {
        assertEquals(HitData.MISS, scene.traceRay(new Ray(new Point(10,10,10), new Point(1,1,1))));
    }
}
