package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * Polygon that may contain many faces
 */
public class Polygon implements Traceable {

    public Polygon(Face face) {
        List<Face> faceList1 = new ArrayList<>();
        faceList1.add(face);
        this.faceList = faceList1;
    }

    public Polygon(List<Face> faceList) {
        this.faceList = faceList;
    }

    private List<Face> faceList;

    @Override
    public HitPoint getHitPoint(Ray ray) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //<editor-fold desc="Getters and setters">
    public List<Face> getFaceList() {
        return faceList;
    }

    public void setFaceList(List<Face> faceList) {
        this.faceList = faceList;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;

        if (faceList != null ? !faceList.equals(polygon.faceList) : polygon.faceList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return faceList != null ? faceList.hashCode() : 0;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Polygon{" +
                "faceList=" + faceList +
                '}';
    }
}
