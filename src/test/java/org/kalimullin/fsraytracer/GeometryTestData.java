package org.kalimullin.fsraytracer;

import org.kalimullin.fsraytracer.geometry.Face;
import org.kalimullin.fsraytracer.geometry.Point;
import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.scene.PolygonalSceneObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GeometryTestData {
    public static final Point POINT_ZERO = new Point(0,0,0);
    public static final Point POINT_ONE = new Point(1,1,1);
    public static final Point POINT_TWO = new Point(2,2,2);
    public static final Point POINT_THREE = new Point(3,3,3);
    public static final Face FACE_ONE = new Face(new HashSet<>(Arrays.asList(POINT_ZERO, POINT_ONE, POINT_TWO)));
    public static final Face FACE_TWO = new Face(new HashSet<>(Arrays.asList(POINT_ONE, POINT_TWO, POINT_THREE)));
    public static final Face XY_PYRAMID_POLYGON_FACE = new Face(new Point(10,0,0), new Point(0,0,0), new Point(0,10,0));
    public static final Polygon POLYGON_ONE = new Polygon(FACE_ONE);
    public static final Polygon POLYGON_TWO = new Polygon(FACE_TWO);
    public static final Polygon XY_PYRAMID_POLYGON = new Polygon(XY_PYRAMID_POLYGON_FACE);
    public static final Polygon YZ_PYRAMID_POLYGON = new Polygon(new Face(new Point(0,0,0), new Point(0,0,10), new Point(0,10,0)));
    public static final Polygon XZ_PYRAMID_POLYGON = new Polygon(new Face(new Point(0,0,0), new Point(0,0,10), new Point(10,0,0)));
    public static final Polygon XYZ_PYRAMID_POLYGON = new Polygon(new Face(new Point(10,0,0), new Point(0,10,0), new Point(0,0,10)));
    private static Set<Polygon> polygonSet = new HashSet<>();
    public static final PolygonalSceneObject PYRAMID = new PolygonalSceneObject("Pyramid", polygonSet);
    static {
        polygonSet.add(XY_PYRAMID_POLYGON);
        polygonSet.add(YZ_PYRAMID_POLYGON);
        polygonSet.add(XZ_PYRAMID_POLYGON);
        polygonSet.add(XYZ_PYRAMID_POLYGON);
    }
}
