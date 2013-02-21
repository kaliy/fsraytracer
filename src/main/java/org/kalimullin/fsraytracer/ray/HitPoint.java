package org.kalimullin.fsraytracer.ray;

import com.google.common.math.DoubleMath;
import org.kalimullin.fsraytracer.geometry.Point;

public class HitPoint implements Comparable {

    public HitPoint(Point point, double hitLength) {
        this.point = point;
        this.hitLength = hitLength;
    }

    // if the ray doesn't hit an object
    public static final HitPoint MISSED = new HitPoint(new Point(), Double.POSITIVE_INFINITY);

    private Point point;
    private double hitLength;

    /**
     * Compares hitLength between two point.
     */
    @Override
    public int compareTo(Object o) {
        HitPoint temp = (HitPoint)o;
        return DoubleMath.fuzzyCompare(this.getHitLength(), temp.getHitLength(), 0.00001);
    }

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

        if (DoubleMath.fuzzyCompare(hitPoint.hitLength, hitLength, 0.00001) != 0) return false;
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


    @Override
    public String toString() {
        return "HitPoint{" +
                "point=" + point +
                ", hitLength=" + hitLength +
                '}';
    }

}
