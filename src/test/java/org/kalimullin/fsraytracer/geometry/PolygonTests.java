package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Test;
import org.kalimullin.fsraytracer.GeometryData;

import java.util.Arrays;
import java.util.HashSet;

public class PolygonTests extends Assert {
    @Test
    public void testEquals() {
        assertEquals(new Polygon(new Face(new HashSet<>(Arrays.asList(
                GeometryData.POINT_TWO, GeometryData.POINT_ZERO, GeometryData.POINT_ONE)))), GeometryData.POLYGON_ONE);
        assertNotEquals(GeometryData.POLYGON_ONE, GeometryData.POLYGON_TWO);
    }
}
