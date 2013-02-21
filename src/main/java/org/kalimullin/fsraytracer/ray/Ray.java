package org.kalimullin.fsraytracer.ray;

import org.kalimullin.fsraytracer.geometry.Point;

public class Ray {

    public Ray(Point originPoint, Point directionVector) {
        this.originPoint = originPoint;
        this.directionVector = directionVector.getNormalizedVector(); //TODO check this pile of code
    }

    private Point originPoint;
    private Point directionVector;

    //<editor-fold desc="Getters and setters">
    public Point getOriginPoint() {
        return originPoint;
    }

    public void setOriginPoint(Point originPoint) {
        this.originPoint = originPoint;
    }

    public Point getDirectionVector() {
        return directionVector;
    }

    public void setDirectionVector(Point directionVector) {
        this.directionVector = directionVector;
    }
    //</editor-fold>


    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (directionVector != null ? !directionVector.equals(ray.directionVector) : ray.directionVector != null)
            return false;
        if (originPoint != null ? !originPoint.equals(ray.originPoint) : ray.originPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originPoint != null ? originPoint.hashCode() : 0;
        result = 31 * result + (directionVector != null ? directionVector.hashCode() : 0);
        return result;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Ray{" +
                "originPoint=" + originPoint +
                ", directionVector=" + directionVector +
                '}';
    }
}
