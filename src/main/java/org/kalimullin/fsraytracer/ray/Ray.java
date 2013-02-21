package org.kalimullin.fsraytracer.ray;

import org.kalimullin.fsraytracer.geometry.Point;

public class Ray {

    public Ray(Point originPoint, Point directionVector) {
        this(originPoint, directionVector, null);
    }

    public Ray(Point originPoint, Point directionVector, Integer id) {
        this.originPoint = originPoint;
        this.directionVector = directionVector.getNormalizedVector(); //TODO check this pile of code
        this.id = id;
    }

    private Point originPoint;
    private Point directionVector;
    private Integer id;

    /**
     * Creates ray from unparsed string. {@code Ray.getPointFromString("1;1,-0.5,5;0,0,-1")} equals
     * {@code new Ray(new Point(1,-0.5,5), new Point(0,0,-1), 1)}
     * @param string format "id;originX,originY,originZ;dirX,dirY,dirZ"
     */
    public static Ray getRayFromString(String string) {
        String[] rayData = string.split(";");
        if (3 != rayData.length)
            throw new IllegalArgumentException("String must be following format: " +
                    "\"id;originX,originY,originZ;dirX,dirY,dirZ\"");
        Integer id = Integer.parseInt(rayData[0]);
        Point originPoint = Point.getPointFromString(rayData[1]);
        Point directionVector = Point.getPointFromString(rayData[2]);
        return new Ray(originPoint, directionVector, id);
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (id != null ? !id.equals(ray.id) : ray.id != null) return false;
        if (originPoint != null ? !originPoint.equals(ray.originPoint) : ray.originPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originPoint != null ? originPoint.hashCode() : 0;
        result = 31 * result + (directionVector != null ? directionVector.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Ray{" +
                "originPoint=" + originPoint +
                ", directionVector=" + directionVector +
                ", id=" + id +
                '}';
    }
}
