package org.kalimullin.fsraytracer.ray;

import org.kalimullin.fsraytracer.scene.SceneObject;

/**
 * Class for storing ray hit data (scene object and it's hit point)
 */
// this shouldn't be a HitPoint ancestor!
public class HitData implements Comparable {

    private HitPoint hitPoint;
    private SceneObject sceneObject;

    public HitData(HitPoint hitPoint, SceneObject sceneObject) {
        this.hitPoint = hitPoint;
        this.sceneObject = sceneObject;
    }

    public static final HitData MISS = new HitData(HitPoint.MISSED, null);

    //<editor-fold desc="Getters and setters">
    public HitPoint getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(HitPoint hitPoint) {
        this.hitPoint = hitPoint;
    }

    public SceneObject getSceneObject() {
        return sceneObject;
    }

    public void setSceneObject(SceneObject sceneObject) {
        this.sceneObject = sceneObject;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HitData hitData = (HitData) o;

        if (hitPoint != null ? !hitPoint.equals(hitData.hitPoint) : hitData.hitPoint != null) return false;
        if (sceneObject != null ? !sceneObject.equals(hitData.sceneObject) : hitData.sceneObject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hitPoint != null ? hitPoint.hashCode() : 0;
        result = 31 * result + (sceneObject != null ? sceneObject.hashCode() : 0);
        return result;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "HitData{" +
                "sceneObject=" + sceneObject +
                ", hitPoint=" + hitPoint +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return hitPoint.compareTo(o);
    }
}
