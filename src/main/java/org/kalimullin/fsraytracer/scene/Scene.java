package org.kalimullin.fsraytracer.scene;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    public Scene() {
        this(new ArrayList<SceneObject>(), Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public Scene(List<SceneObject> sceneObjects, int height, int width) {
        this.sceneObjects = sceneObjects;
        this.height = height;
        this.width = width;
    }

    private List<SceneObject> sceneObjects = new ArrayList<>();
    private int height;
    private int width;

    public List<SceneObject> addSceneObject(SceneObject sceneObject) {
        getSceneObjects().add(sceneObject);
        return getSceneObjects();
    }

    //<editor-fold desc="Getters and setters">
    public List<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public void setSceneObjects(List<SceneObject> sceneObjects) {
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
