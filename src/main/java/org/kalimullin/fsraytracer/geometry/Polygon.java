package org.kalimullin.fsraytracer.geometry;

import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;
import org.kalimullin.fsraytracer.utils.CollectionUtils;

import java.util.*;

/**
 * Polygon that may contain many faces
 */
public class Polygon implements Traceable {

    public Polygon(Face face) {
        List<Face> faceSet1 = new ArrayList<>();
        faceSet1.add(face);
        this.faceSet = faceSet1;
    }

    public Polygon(List<Face> faceSet) {
        this.faceSet = faceSet;
    }

    private List<Face> faceSet;

    @Override
    public HitPoint getHitPoint(Ray ray) {
        //TODO implement this method
        List<HitPoint> hitPointList = new ArrayList<>(faceSet.size());
        for (Face face: getFaceSet()) {
            hitPointList.add(face.getHitPoint(ray));
        }
        Collections.sort(hitPointList);
        return hitPointList.get(0);
    }

    //<editor-fold desc="Getters and setters">
    public List<Face> getFaceSet() {
        return faceSet;
    }

    public void setFaceSet(List<Face> faceSet) {
        this.faceSet = faceSet;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;

        if (faceSet != null ? !CollectionUtils.isListEqualsIgnoreOrder(faceSet, polygon.faceSet) : polygon.faceSet != null) return false;

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
