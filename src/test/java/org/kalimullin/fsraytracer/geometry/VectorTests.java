package org.kalimullin.fsraytracer.geometry;

import org.junit.Assert;
import org.junit.Test;

public class VectorTests extends Assert {
    @Test
    public void testNormalize() {
        Point vector = new Point(1, 4, 2);
        assertEquals(new Point(1 / Math.sqrt(21), 4 / Math.sqrt(21), 2 / Math.sqrt(21)), vector.getNormalizedVector());
    }

    @Test
    public void testDotProduct() {
        assertEquals(52, new Point(4, 5, 2).getDotProduct(8, 2, 5), 0.00001);
    }

    @Test
    public void testCrossProduct() {
        Point point = new Point(4, 5, 2);
        assertEquals(new Point(21, -4, -32), point.getCrossProduct(new Point(8, 2, 5)));
        assertEquals(new Point(21, -4, -32), point.getCrossProduct(8, 2, 5));
    }
}
