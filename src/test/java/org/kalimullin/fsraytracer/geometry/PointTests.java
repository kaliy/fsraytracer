package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Test;
import org.kalimullin.fsraytracer.GeometryData;

public class PointTests extends Assert {
    @Test
    public void testEquals() {
        assertEquals(GeometryData.POINT_ZERO, new Point(0,0,0));
        assertNotEquals(GeometryData.POINT_ZERO, new Point(0,0,1));
        assertNotEquals(GeometryData.POINT_ZERO, new Point(0,1,0));
        assertNotEquals(GeometryData.POINT_ZERO, new Point(1,0,0));
    }

    @Test
    public void testDistance() {
        Point point1 = new Point(1,1,1);
        Point point2 = new Point(4,6,5);
        assertEquals(Math.sqrt(50), point1.getDistanceTo(point2), 0.00001);
    }

}
