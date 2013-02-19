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
}
