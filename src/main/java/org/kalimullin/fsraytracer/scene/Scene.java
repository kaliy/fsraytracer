package org.kalimullin.fsraytracer.scene;

import org.kalimullin.fsraytracer.ray.HitData;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.HashSet;
import java.util.Set;

public class Scene {

    public Scene() {
        this(new HashSet<SceneObject>(), Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public Scene(Set<SceneObject> sceneObjects, int height, int width) {
        this.sceneObjects = sceneObjects;
        this.height = height;
        this.width = width;
    }

    private Set<SceneObject> sceneObjects = new HashSet<>();
    private int height;
    private int width;

    public Set<SceneObject> addSceneObject(SceneObject sceneObject) {
        getSceneObjects().add(sceneObject);
        return getSceneObjects();
    }

    /**
     * Tracing ray and returning it's hit data.
     */
    public HitData traceRay(Ray ray) {
        HitData hitData = HitData.MISS;
        for (SceneObject sceneObject: getSceneObjects()) {
            HitPoint hitPoint = sceneObject.getHitPoint(ray);
            if (!HitPoint.MISSED.equals(hitPoint) && hitData.getHitPoint().compareTo(hitPoint) == 1)
                hitData = new HitData(hitPoint, sceneObject);
        }
        return hitData;
    }

    //<editor-fold desc="Getters and setters">
    public Set<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void setSceneObjects(Set<SceneObject> sceneObjects) {
        this.sceneObjects = sceneObjects;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    //</editor-fold>
}
