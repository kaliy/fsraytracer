package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        throw new UnsupportedOperationException("Not implemented yet");
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