package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Test;
import org.kalimullin.fsraytracer.GeometryTestData;

public class PointTest extends Assert {
    @Test
    public void testEquals() {
        assertEquals(GeometryTestData.POINT_ZERO, new Point(0,0,0));
        assertNotEquals(GeometryTestData.POINT_ZERO, new Point(0,0,1));
        assertNotEquals(GeometryTestData.POINT_ZERO, new Point(0,1,0));
        assertNotEquals(GeometryTestData.POINT_ZERO, new Point(1,0,0));
    }

    @Test
    public void testDistance() {
        Point point1 = new Point(1,1,1);
        Point point2 = new Point(4,6,5);
        assertEquals(Math.sqrt(50), point1.getDistanceTo(point2), 0.00001);
    }

    @Test
    public void testSubtraction() {
        Point point = new Point(6,8,9);
        assertEquals(new Point(4,5,5), point.getSubtraction(2,3,4));
        assertEquals(new Point(4,5,5), point.getSubtraction(new Point(2,3,4)));
    }

    @Test
    public void testAddition() {
        Point point = new Point(6,8,9);
        assertEquals(new Point(8,11,13), point.getAddition(2, 3, 4));
        assertEquals(new Point(8,11,13), point.getAddition(new Point(2, 3, 4)));
    }
}
