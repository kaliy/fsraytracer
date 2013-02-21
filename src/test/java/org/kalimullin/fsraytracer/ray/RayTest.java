package org.kalimullin.fsraytracer.ray;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kalimullin.fsraytracer.geometry.Point;

public class RayTest extends Assert {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testParsingString() {
        assertEquals(new Ray(new Point(-1,-5,-6), new Point(2,5,7), 17), Ray.getRayFromString("17;-1,-5,-6;2,5,7"));
    }

    @Test
    public void testParsingIllegalString() {
        expectedException.expect(IllegalArgumentException.class);
        Ray.getRayFromString("1,5,6");
    }


}
