package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kalimullin.fsraytracer.GeometryTestData;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PolygonTest extends Assert {

    Traceable polygonOneFace;
    Traceable polygonTwoFaces;

    @Before
    public void setUp() {
        polygonOneFace = new Polygon(new Face(new Point(10,0,0), new Point(0,10,0), new Point(0,0,0)));
        List<Face> faceSet = new ArrayList<>();
        faceSet.add(new Face(new Point(10,0,0), new Point(0,10,0), new Point(0,0,0)));
        faceSet.add(new Face(new Point(10,0,0), new Point(0,10,0), new Point(10,10,0)));
        polygonTwoFaces = new Polygon(faceSet);
    }

    @Test
    public void testEquals() {
        assertEquals(new Polygon(new Face(Arrays.asList(
                GeometryTestData.POINT_TWO, GeometryTestData.POINT_ZERO, GeometryTestData.POINT_ONE))), GeometryTestData.POLYGON_ONE);
        assertNotEquals(GeometryTestData.POLYGON_ONE, GeometryTestData.POLYGON_TWO);
    }

    @Test
    public void testHitOneFace() {
        Ray hitRay = new Ray(new Point(5,5,3), new Point(-1,-1,-1));
        assertEquals(new HitPoint(new Point(2, 2, 0), Math.sqrt(27)), polygonOneFace.getHitPoint(hitRay));
    }

    @Test
    public void testHitTwoFaces() {
        Ray hitRay = new Ray(new Point(5,5,3), new Point(1,1,-1));
        assertEquals(new HitPoint(new Point(8, 8, 0), Math.sqrt(27)), polygonTwoFaces.getHitPoint(hitRay));
    }

    @Test
    public void testMissOneFace() {
        Ray missRay = new Ray(new Point(5,5,3), new Point(1,1,1));
        assertEquals(HitPoint.MISSED, polygonOneFace.getHitPoint(missRay));
    }

    @Test
    public void testMissTwoFaces() {
        Ray missRay = new Ray(new Point(5,5,3), new Point(1,1,1));
        assertEquals(HitPoint.MISSED, polygonOneFace.getHitPoint(missRay));
    }

    @Test
    public void testMissParallel() {
        Ray missRay = new Ray(new Point(10,2,6), new Point(5,12,0));
        assertEquals(HitPoint.MISSED, polygonOneFace.getHitPoint(missRay));
        assertEquals(HitPoint.MISSED, polygonTwoFaces.getHitPoint(missRay));
    }

    @Test
    public void testMissPlaneIntersectedRay() {
        Ray hitRay = new Ray(new Point(25,25,3), new Point(1,1,-1));
        assertEquals(HitPoint.MISSED, polygonTwoFaces.getHitPoint(hitRay));
        assertEquals(HitPoint.MISSED, polygonOneFace.getHitPoint(hitRay));
    }

}
