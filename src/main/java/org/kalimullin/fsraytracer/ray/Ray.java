package org.kalimullin.fsraytracer.ray;

import org.kalimullin.fsraytracer.geometry.Point;

public class Ray {

    public Ray(Point initialPoint, Point directionPoint) {
        this.initialPoint = initialPoint;
        this.directionPoint = directionPoint;
    }

    private Point initialPoint;
    private Point directionPoint;

    //<editor-fold desc="Getters and setters">
    public Point getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(Point initialPoint) {
        this.initialPoint = initialPoint;
    }

    public Point getDirectionPoint() {
        return directionPoint;
    }

    public void setDirectionPoint(Point directionPoint) {
        this.directionPoint = directionPoint;
    }
    //</editor-fold>


    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (directionPoint != null ? !directionPoint.equals(ray.directionPoint) : ray.directionPoint != null)
            return false;
        if (initialPoint != null ? !initialPoint.equals(ray.initialPoint) : ray.initialPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = initialPoint != null ? initialPoint.hashCode() : 0;
        result = 31 * result + (directionPoint != null ? directionPoint.hashCode() : 0);
        return result;
    }
    //</editor-fold>
}
