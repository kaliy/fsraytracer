package org.kalimullin.fsraytracer.scene;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kalimullin.fsraytracer.GeometryTestData;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.HashSet;
import java.util.Set;

public class PolygonalSceneObjectTest extends Assert {

    @Before
    public void setUp() {
    }

    @Test
    public void testEquals() {
        Set<Polygon> polygonSet = new HashSet<>();
        polygonSet.add(GeometryTestData.XY_PYRAMID_POLYGON);
        polygonSet.add(GeometryTestData.YZ_PYRAMID_POLYGON);
        polygonSet.add(GeometryTestData.XZ_PYRAMID_POLYGON);
        polygonSet.add(GeometryTestData.XYZ_PYRAMID_POLYGON);
        SceneObject pyramid = new PolygonalSceneObject("Pyramid", polygonSet);
        assertEquals(GeometryTestData.PYRAMID, pyramid);
    }

    @Test
    public void testHitPoints() {
        Ray xyRay = new Ray(new Point(3, 3, -5), new Point(0, 0, 2));
        assertEquals(new HitPoint(new Point(3,3,0), 5), GeometryTestData.PYRAMID.getHitPoint(xyRay));
        Ray yzRay = new Ray(new Point(-10, 3, 3), new Point(2, 0, 0));
        assertEquals(new HitPoint(new Point(0, 3, 3), 10), GeometryTestData.PYRAMID.getHitPoint(yzRay));
        Ray xzRay = new Ray(new Point(3, -5, 3), new Point(0, 2, 0));
        assertEquals(new HitPoint(new Point(3, 0, 3), 5), GeometryTestData.PYRAMID.getHitPoint(xzRay));
    }

    @Test
    public void testMiss() {
        Ray missRay = new Ray(new Point(20,20,20), new Point(1,1,1));
        assertEquals(HitPoint.MISSED, GeometryTestData.PYRAMID.getHitPoint(missRay));
    }

}
