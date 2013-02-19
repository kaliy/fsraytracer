package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.HashSet;
import java.util.Set;

/**
 * Polygon that may contain many faces
 */
public class Polygon implements Traceable {

    public Polygon(Face face) {
        Set<Face> faceSet1 = new HashSet<>();
        faceSet1.add(face);
        this.faceSet = faceSet1;
    }

    public Polygon(Set<Face> faceSet) {
        this.faceSet = faceSet;
    }

    private Set<Face> faceSet;

    @Override
    public HitPoint getHitPoint(Ray ray) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet");
    }

    //<editor-fold desc="Getters and setters">
    public Set<Face> getFaceSet() {
        return faceSet;
    }

    public void setFaceSet(Set<Face> faceSet) {
        this.faceSet = faceSet;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;

        if (faceSet != null ? !faceSet.equals(polygon.faceSet) : polygon.faceSet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return faceSet != null ? faceSet.hashCode() : 0;
    }
    //</editor-fold>


    @Override
    public String toString() {
            return "Polygon{" +
                    "faceList=" + faceSet +
                    '}';
    }
}
