/**
 * @author roei derman 322467184
 * class of Point
 */
package Geometry;
/**
 * class of Point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * constructor of a Point.
     * @param x of the point.
     * @param y of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * @param a - first double
     * @param b - second double
     * @return true if equals, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return  Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    /**
     * Return the distance of this point to the other point.
     * @param other - other point to find distance.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * return true is the points are equal, false otherwise.
     * @param other - other point to check equals.
     * @return true if the points are equal and false otherwise.
     */
    public boolean equals(Point other) {
        return doubleEquals(this.x, other.x) && doubleEquals(this.y, other.y);
    }
    //Return the x and y values of this point

    /**
     * @return x of the Point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return y of the Point
     */
    public double getY() {
        return this.y;
    }
}
