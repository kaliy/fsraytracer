package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.fsraytracer.GeometryData;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.Arrays;
import java.util.HashSet;

public class FaceTest extends Assert {

    Traceable faceToTest;

    @Before
    public void setUp() {
        this.faceToTest = new Face(new Point(10,0,0), new Point(0,10,0), new Point(1,1,0));
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEquals() {
        assertEquals(new Face(GeometryData.POINT_ONE, GeometryData.POINT_ZERO, GeometryData.POINT_TWO), GeometryData.FACE_ONE);
        assertNotEquals(GeometryData.FACE_ONE, GeometryData.FACE_TWO);
    }

    @Test
    public void tetIllegalSetConstructor() {
        expectedException.expect(IllegalArgumentException.class);
        new Face(new HashSet<>(Arrays.asList(GeometryData.POINT_ONE, GeometryData.POINT_ZERO, GeometryData.POINT_TWO, GeometryData.POINT_THREE)));
    }

    @Test
    public void testHit() {
        Ray hitRay = new Ray(new Point(5,5,3), new Point(-1,-1,-1));
        assertEquals(new HitPoint(new Point(2, 2, 0), Math.sqrt(27)), faceToTest.getHitPoint(hitRay));
    }

    @Test
    public void testHitFromAnotherSide() {
        Ray hitRay = new Ray(new Point(5,5,-3), new Point(-1,-1,1));
        assertEquals(new HitPoint(new Point(2, 2, 0), Math.sqrt(27)), faceToTest.getHitPoint(hitRay));
    }

    @Test
    public void testMissNeverIntersectedPlaneRay() {
        Ray missRay = new Ray(new Point(5,5,3), new Point(1,1,1));
        assertEquals(HitPoint.MISSED, faceToTest.getHitPoint(missRay));
    }

    @Test
    public void testMissedButIntersectedPlaneRay() {
        Ray missRayButIntersectsPlane = new Ray(new Point(25,25,2), new Point(-1,-1,-1));
        assertEquals(HitPoint.MISSED, faceToTest.getHitPoint(missRayButIntersectsPlane));
    }

    @Test
    public void testParallelPlaneAndRay() {
        Ray parallelRay = new Ray(new Point(10,25,1), new Point(4,3,0));
        assertEquals(HitPoint.MISSED, faceToTest.getHitPoint(parallelRay));
    }
}
