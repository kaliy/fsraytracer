package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.fsraytracer.GeometryData;

import java.util.Arrays;
import java.util.HashSet;

public class FaceTests extends Assert {

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

}
