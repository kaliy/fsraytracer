package org.kalimullin.fsraytracer.ray;

import org.kalimullin.fsraytracer.geometry.Point;

public class HitPoint {

    public HitPoint(Point point, double hitLength) {
        this.point = point;
        this.hitLength = hitLength;
    }

    // if the ray doesn't hit an object
    public static final HitPoint MISSED = new HitPoint(null, -1);

    private Point point;
    private double hitLength;

    //<editor-fold desc="Getters and setters">
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getHitLength() {
        return hitLength;
    }

    public void setHitLength(double hitLength) {
        this.hitLength = hitLength;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HitPoint hitPoint = (HitPoint) o;

        if (Double.compare(hitPoint.hitLength, hitLength) != 0) return false;
        if (!point.equals(hitPoint.point)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = point.hashCode();
        temp = hitLength != +0.0d ? Double.doubleToLongBits(hitLength) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    //</editor-fold>
}
