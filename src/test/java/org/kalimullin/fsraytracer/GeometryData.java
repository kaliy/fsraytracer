package org.kalimullin.fsraytracer;

import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import java.util.Arrays;
import java.util.HashSet;

public class GeometryData {
    public static final Point POINT_ZERO = new Point(0,0,0);
    public static final Point POINT_ONE = new Point(1,1,1);
    public static final Point POINT_TWO = new Point(2,2,2);
    public static final Point POINT_THREE = new Point(3,3,3);
    public static final Face FACE_ONE = new Face(new HashSet<>(Arrays.asList(POINT_ZERO, POINT_ONE, POINT_TWO)));
    public static final Face FACE_TWO = new Face(new HashSet<>(Arrays.asList(POINT_ONE, POINT_TWO, POINT_THREE)));
    public static final Polygon POLYGON_ONE = new Polygon(FACE_ONE);
    public static final Polygon POLYGON_TWO = new Polygon(FACE_TWO);
}
