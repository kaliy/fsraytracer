package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.List;

/**
 * Triangle face than contains three edges
 */
public class Face implements Traceable {

    private List<Point> vertexList;

    public Face(List<Point> vertexList) {
        this.vertexList = vertexList;
    }

    @Override
    public HitPoint getHitPoint(Ray ray) {
        //TODO implement it
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //<editor-fold desc="Getters and setters">
    public List<Point> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Point> vertexList) {
        this.vertexList = vertexList;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Face face = (Face) o;

        if (vertexList != null ? !vertexList.equals(face.vertexList) : face.vertexList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return vertexList != null ? vertexList.hashCode() : 0;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Face{" +
                "vertexList=" + vertexList +
                '}';
    }
}