package org.kalimullin.fsraytracer.ray;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kalimullin.fsraytracer.geometry.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HitPointTest extends Assert {

    HitPoint hitPoint;

    @Before
    public void setUp() {
        hitPoint = new HitPoint(new Point(5,5,5), 10);
    }

    @Test
    public void testEquals() {
        assertEquals(new HitPoint(new Point(5,5,5), 10), hitPoint);
    }

    @Test
    public void testComparator() {
        List<HitPoint> hitPointList = new ArrayList<>();
        HitPoint hitPointSeven = new HitPoint(new Point(4,9,1), 7);
        HitPoint hitPointNine = new HitPoint(new Point(6,1,9), 9);
        HitPoint hitPointTen = new HitPoint(new Point(1,1,0), 10);
        HitPoint hitPointTwenty = new HitPoint(new Point(6,9,6), 20);
        HitPoint hitPointTwentyTwo = new HitPoint(new Point(0,0,1), 22);
        hitPointList.add(hitPointNine);
        hitPointList.add(hitPointTwenty);
        hitPointList.add(HitPoint.MISSED);
        hitPointList.add(hitPointTwentyTwo);
        hitPointList.add(hitPointTen);
        hitPointList.add(hitPointSeven);
        Collections.sort(hitPointList);
        assertEquals(hitPointList.get(0), hitPointSeven);
        assertEquals(hitPointList.get(1), hitPointNine);
        assertEquals(hitPointList.get(2), hitPointTen);
        assertEquals(hitPointList.get(3), hitPointTwenty);
        assertEquals(hitPointList.get(4), hitPointTwentyTwo);
        assertEquals(hitPointList.get(5), HitPoint.MISSED);
    }
}
