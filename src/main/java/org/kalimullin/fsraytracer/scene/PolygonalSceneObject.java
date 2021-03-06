package org.kalimullin.fsraytracer.scene;

import org.kalimullin.fsraytracer.geometry.Polygon;
import org.kalimullin.fsraytracer.geometry.Traceable;
import org.kalimullin.fsraytracer.ray.HitPoint;
import org.kalimullin.fsraytracer.ray.Ray;

import java.util.*;

/**
 * Scene object that consists of many polygons
 * @see org.kalimullin.fsraytracer.geometry.Polygon
 */
public class PolygonalSceneObject implements SceneObject {

    public PolygonalSceneObject(String name) {
        this(name, new HashSet<Polygon>());
    }

    public PolygonalSceneObject(String name, Set<Polygon> polygons) {
        this.name = name;
        this.polygons = polygons;
    }

    private Set<Polygon> polygons;
    private String name;

    @Override
    public HitPoint getHitPoint(Ray ray) {
        List<HitPoint> hitPointList = new ArrayList<>(getPolygons().size());
        for (Polygon polygon: getPolygons())
            hitPointList.add(polygon.getHitPoint(ray));
        Collections.sort(hitPointList);
        return hitPointList.get(0);
    }

    //<editor-fold desc="Getters and setters">
    public Set<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(Set<Polygon> polygons) {
        this.polygons = polygons;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PolygonalSceneObject that = (PolygonalSceneObject) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (polygons != null ? !polygons.equals(that.polygons) : that.polygons != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = polygons != null ? polygons.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "PolygonalSceneObject{" +
                "polygons=" + polygons +
                ", name='" + name + '\'' +
                '}';
    }
}
