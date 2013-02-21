package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.fsraytracer.GeometryTestData;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.Arrays;
import java.util.HashSet;

public class FaceTest extends Assert {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testEquals() {
        assertEquals(new Face(GeometryTestData.POINT_ONE, GeometryTestData.POINT_ZERO,
                GeometryTestData.POINT_TWO), GeometryTestData.FACE_ONE);
        assertNotEquals(GeometryTestData.FACE_ONE, GeometryTestData.FACE_TWO);
    }

    @Test
    public void tetIllegalSetConstructor() {
        expectedException.expect(IllegalArgumentException.class);
        new Face(new HashSet<>(Arrays.asList(GeometryTestData.POINT_ONE, GeometryTestData.POINT_ZERO,
                GeometryTestData.POINT_TWO, GeometryTestData.POINT_THREE)));
    }

    @Test
    public void testHit() {
        Ray hitRay = new Ray(new Point(5,5,3), new Point(-1,-1,-1));
        assertEquals(new HitPoint(new Point(2, 2, 0), Math.sqrt(27)),
                GeometryTestData.XY_PYRAMID_POLYGON_FACE.getHitPoint(hitRay));
    }

    @Test
    public void testHitFromAnotherSide() {
        Ray hitRay = new Ray(new Point(5,5,-3), new Point(-1,-1,1));
        assertEquals(new HitPoint(new Point(2, 2, 0), Math.sqrt(27)),
                GeometryTestData.XY_PYRAMID_POLYGON_FACE.getHitPoint(hitRay));
    }

    @Test
    public void testMissNeverIntersectedPlaneRay() {
        Ray missRay = new Ray(new Point(5,5,3), new Point(1,1,1));
        assertEquals(HitPoint.MISSED, GeometryTestData.XY_PYRAMID_POLYGON_FACE.getHitPoint(missRay));
    }

    @Test
    public void testMissedButIntersectedPlaneRay() {
        Ray missRayButIntersectsPlane = new Ray(new Point(25,25,2), new Point(-1,-1,-1));
        assertEquals(HitPoint.MISSED, GeometryTestData.XY_PYRAMID_POLYGON_FACE.getHitPoint(missRayButIntersectsPlane));
    }

    @Test
    public void testParallelPlaneAndRay() {
        Ray parallelRay = new Ray(new Point(10,25,1), new Point(4,3,0));
        assertEquals(HitPoint.MISSED, GeometryTestData.XY_PYRAMID_POLYGON_FACE.getHitPoint(parallelRay));
    }
}
