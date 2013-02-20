package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.*;

/**
 * Triangle face than contains three edges
 */
public class Face implements Traceable {

    private Set<Point> vertexSet;

    public Face(Point point1, Point point2, Point point3) {
        this(new HashSet<>(Arrays.asList(point1, point2, point3)));
    }

    public Face(Set<Point> vertexSet) {
        if (vertexSet.size() != 3)
            throw new IllegalArgumentException("Face should have three points");
        else
            this.vertexSet = vertexSet;
    }

    @Override
    public HitPoint getHitPoint(Ray ray) {
        //TODO implement it
        return getPlaneIntersection(ray);
    }


    /**
     * Calculating normal to the face
     * @return normalized normal
     */
    private Point getNormalizedNormal() {
        List<Point> vList = new ArrayList<>(vertexSet);
        return vList.get(1).getSubtraction(vList.get(0))
                .getCrossProduct(vList.get(2).getSubtraction(vList.get(0)))
                .getNormalizedVector();
    }

    /**
     * Calculating plane coefficient (D in canonical plane equation - Ax+By+Cz+D=0)
     */
    private double getPlaneCoefficient() {
        return getNormalizedNormal().getDotProduct(vertexSet.iterator().next());
    }

    /**
     * Calculating ray and plane (formed by triangle) intersection point
     * @param ray
     * @return HitPoint
     */
    private HitPoint getPlaneIntersection(Ray ray) {
        double rayDirectionAndNormalDotProduct = getNormalizedNormal().getDotProduct(ray.getDirectionVector());
        if (0 != rayDirectionAndNormalDotProduct) {
            double distanceCoefficient = -1 * (getPlaneCoefficient() + ray.getOriginPoint().getDotProduct(getNormalizedNormal()))
                    / ray.getDirectionVector().getDotProduct(getNormalizedNormal());
            if (0 > distanceCoefficient)
                return HitPoint.MISSED;
            Point hitPoint = ray.getOriginPoint().getAddition(ray.getDirectionVector().getMultiplication(distanceCoefficient));
            return new HitPoint(hitPoint, ray.getOriginPoint().getDistanceTo(hitPoint));
        }
        return HitPoint.MISSED;
    }

    //<editor-fold desc="Getters and setters">
    public Set<Point> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(Set<Point> vertexSet) {
        this.vertexSet = vertexSet;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Face face = (Face) o;

        if (vertexSet != null ? !vertexSet.equals(face.vertexSet) : face.vertexSet != null) return false;

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