package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;
import org.kalimullin.fsraytracer.utils.CollectionUtils;

import java.util.*;

/**
 * Triangle face than contains three edges
 */
public class Face implements Traceable {

    private List<Point> vertexSet;

    public Face(Point point1, Point point2, Point point3) {
        this(Arrays.asList(point1, point2, point3));
    }

    public Face(List<Point> vertexSet) {
        if (vertexSet.size() != 3)
            throw new IllegalArgumentException("Face should have three points");
        else
            this.vertexSet = vertexSet;
    }

    @Override
    public HitPoint getHitPoint(Ray ray) {
        return getBarycentricCoordinatesAlgorithmHitPoint(ray);
    }


    /**
     * Detecting ray and rectangle face intersection by Tomas Möller and Ben Trumbore method.
     * @see <a href="http://goo.gl/RZkys">Fast Minimum Storage Ray Triangle Intersection</a>
     * @param ray ray that produced intersection
     * @return true if point belong to the face, false otherwise
     */
    private HitPoint getBarycentricCoordinatesAlgorithmHitPoint(Ray ray) {
        List<Point> vList = new ArrayList<>(vertexSet);
        // 1 / P dot E1 == 1/(Dx(V2-V0))dot(V1-V0)
        double coefficient = 1 / ray.getDirectionVector().getCrossProduct(vList.get(2).getSubtraction(vList.get(0)))
                .getDotProduct(vList.get(1).getSubtraction(vList.get(0)));
        // coeff * QdotE2 = coeff * (O-V0)x(V1-V0)dot(V2-V0)
        double t = coefficient * ray.getOriginPoint().getSubtraction(vList.get(0))
                .getCrossProduct(vList.get(1).getSubtraction(vList.get(0)))
                .getDotProduct(vList.get(2).getSubtraction(vList.get(0)));
        if (t < 0)
            return HitPoint.MISSED;
        // coeff * DdotT = coeff * Dx(V2-V0)dot(O-V0)
        double u = coefficient * (ray.getDirectionVector().getCrossProduct(vList.get(2).getSubtraction(vList.get(0)))
                .getDotProduct(ray.getOriginPoint().getSubtraction(vList.get(0))));
        // coeff * QdotD = coeff * (O-V0)x(V1-V0)dotD
        double v = coefficient * (ray.getOriginPoint().getSubtraction(vList.get(0))
                .getCrossProduct(vList.get(1).getSubtraction(vList.get(0)))).getDotProduct(ray.getDirectionVector());
        if (u >= 0 && v >= 0 && u + v <= 1) {
            Point hitPoint = ray.getOriginPoint().getAddition(ray.getDirectionVector().getMultiplication(t));
            return new HitPoint(hitPoint, ray.getOriginPoint().getDistanceTo(hitPoint));
        }
        return HitPoint.MISSED;
    }

    //<editor-fold desc="Getters and setters">
    public List<Point> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(List<Point> vertexSet) {
        this.vertexSet = vertexSet;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Face face = (Face) o;
        if (vertexSet != null ? !CollectionUtils.isListEqualsIgnoreOrder(vertexSet, face.vertexSet) : face.vertexSet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return vertexSet != null ? vertexSet.hashCode() : 0;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Face{" +
                "vertexSet=" + vertexSet +
                '}';
    }
}