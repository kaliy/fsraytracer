package org.kalimullin.fsraytracer.geometry;

import com.google.common.math.DoubleMath;

public class Point {

    public Point() {
        this(0.0, 0.0, 0.0);
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private double x;
    private double y;
    private double z;

    public static final Point UNEXISTED_POINT = new Point(Double.NaN, Double.NaN, Double.NaN);

    /**
     * Get distance from this point to another
     */
    public double getDistanceTo(Point point) {
        return Math.sqrt(Math.pow(point.getX() - getX(), 2)
                + Math.pow(point.getY() - getY(), 2)
                + Math.pow(point.getZ() - getZ(), 2));
    }

    public Point getSubtraction(Point point) {
        return getSubtraction(point.getX(), point.getY(), point.getZ());
    }

    public Point getSubtraction(double x, double y, double z) {
        return new Point(getX() - x, getY() - y, getZ() - z);
    }

    public Point getAddition(Point point) {
        return getAddition(point.getX(), point.getY(), point.getZ());
    }

    public Point getAddition(double x, double y, double z) {
        return new Point(getX() + x, getY() + y, getZ() + z);
    }

    public Point getMultiplication(double multiplicator) {
        return new Point(getX() * multiplicator, getY() * multiplicator, getZ() * multiplicator);
    }

    /**
     * Dot product of this vector (represented by three coordinates) and another vector
     *
     * @param point another vector
     * @return vector with
     */
    //TODO extract these methods to another class (that should be ancestor, smth like Vector3)
    public double getDotProduct(Point point) {
        return getDotProduct(point.getX(), point.getY(), point.getZ());
    }

    public double getDotProduct(double x, double y, double z) {
        return getX() * x + getY() * y + getZ() * z;
    }

    public Point getCrossProduct(Point vector) {
        return getCrossProduct(vector.getX(), vector.getY(), vector.getZ());
    }

    public Point getCrossProduct(double x, double y, double z) {
        return new Point((getY() * z - getZ() * y), (getZ() * x - getX() * z), (getX() * y - getY() * x));
    }

    /**
     * Normalize vector
     *
     * @return normalized vector (this)
     */
    public Point getNormalizedVector() {
        double norm = Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2));
        Point point = new Point();
        if (norm != 0) {
            point.setX(getX() / norm);
            point.setY(getY() / norm);
            point.setZ(getZ() / norm);
        }
        return point;
    }

    /**
     * Creates point from unparsed string. {@code Point.getPointFromString("0,1,2")} equals {@code new Point(0,1,2)}
     * @param string format "x,y,z".
     */
    public static Point getPointFromString(String string) {
        String[] coordinates = string.split(",");
        if (3 != coordinates.length)
            throw new IllegalArgumentException("String must be following format: \"x,y,z\"");
        return new Point(
                Double.parseDouble(coordinates[0]),
                Double.parseDouble(coordinates[1]),
                Double.parseDouble(coordinates[2])
        );
    }


    //<editor-fold desc="Getters and setters">
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    //</editor-fold>

    //<editor-fold desc="equals() and hashCode()">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (DoubleMath.fuzzyCompare(point.x, x, 0.00001) != 0) return false;
        if (DoubleMath.fuzzyCompare(point.y, y, 0.00001) != 0) return false;
        if (DoubleMath.fuzzyCompare(point.z, z, 0.00001) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        //TODO double check all hash methods
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = z != +0.0d ? Double.doubleToLongBits(z) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
