/**
 * @author roei derman 322467184
 * class of Line
 */
package Geometry;

import java.util.List;

import static java.lang.Double.isInfinite;

/**
 * class of Line.
 */

public class Line {
    private final Point start;
    private final Point end;

    /**
     * receive two points and constructor a Line.
     *
     * @param start - first Point to make a line
     * @param end   - second Point to make a line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }


    /**
     * receive x and y for two points , construct al two points and make a Line.
     *
     * @param x1 - x of the first Point
     * @param y1 - y of the first Point
     * @param x2 - x of the second Point
     * @param y2 - y of the second Point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * @param a - first double
     * @param b - second double
     * @return true if equals, false otherwise
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < COMPARISON_THRESHOLD;
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return end.distance(start);
    }

//    /**
//     * @return the middle point of the line
//     */
//    public Point middle() {
//        Point mid;
//        if (this.start == null || this.end == null) {
//            return null;
//        } else {
//            mid = new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
//            return mid;
//        }
//    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * check if point is in line.
     *
     * @param p - point to check
     * @return true if the point is in the line and false otherwise
     */
    public boolean pointInLine(Point p) {
        return doubleEquals(start.distance(p) + end.distance(p), length());
    }

    /**
     * check intersects for parallel lines.
     *
     * @param other line to compare intersects
     * @return 2 if the lines one on the other, 1 if only one point from one line inside the other line
     * and 0 if only one point share in the two lines
     */
    private int parallelIntersect(Line other) {
        if (this.start.equals(other.start) && this.end.equals(other.end())
                || this.end.equals(other.start) && this.start.equals(other.end())) {
            return 2;
        }
        if (this.pointInLine(other.end) || this.pointInLine(other.start)
                || other.pointInLine(this.start) || other.pointInLine(this.end)) {
            if (this.start.equals(other.start) && !this.end.equals(other.end)) {
                return 0;
            }
            if (this.end.equals(other.end) && !this.start.equals(other.start)) {
                return 0;
            }
            if (this.start.equals(other.end) && !this.end().equals(other.start())) {
                return 0;
            }
            if (this.end.equals(other.start) && !this.start().equals(other.end())) {
                return 0;
            }
            return 1;
        }
        return -1;
    }

    /**
     * check if to lines are intersecting.
     *
     * @param other - the other line to check if they are intersecting
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double intersectX, b, incline, intersectY;
        // Check if the lines are point
        if (this.start.equals(this.end)) {
            Point p = new Point(this.start.getX(), this.start.getY());
            return other.pointInLine(p);
        } else if (other.start.equals(other.end)) {
            Point p = new Point(other.start.getX(), other.start.getY());
            return pointInLine(p);
        } else {
            // find inclines of the two lines
            double firstIncline = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            double otherIncline = (other.start.getY() - other.end.getY())
                    / (other.start.getX() - other.end.getX());
            // If they are parallel they aren't intersecting
            if (doubleEquals(firstIncline, otherIncline)
                    || (isInfinite(firstIncline) && (isInfinite(otherIncline)))) {
                if (parallelIntersect(other) == 2) {
                    return true;
                }
                if (parallelIntersect(other) == 1) {
                    return true;
                }
                return parallelIntersect(other) == 0;
            }
            if (isInfinite(firstIncline)) {
                double b2 = other.start.getY() - (otherIncline * other.start.getX());
                intersectY = otherIncline * this.start.getX() + b2;
                Point p = new Point(this.start.getX(), intersectY);
                return other.pointInLine(p) && pointInLine(p);
            } else if (isInfinite(otherIncline)) {
                double b1 = this.start.getY() - (firstIncline * this.start.getX());
                intersectY = firstIncline * other.start.getX() + b1;
                Point p = new Point(other.start.getX(), intersectY);
                return other.pointInLine(p) && pointInLine(p);
            } else {
                double b1 = this.start.getY() - (firstIncline * this.start.getX());
                double b2 = other.start.getY() - (otherIncline * other.start.getX());
                b = b1 - b2;
                incline = otherIncline - firstIncline;
                intersectX = b / incline;
                intersectY = firstIncline * intersectX + b1;
                Point p = new Point(intersectX, intersectY);
                return other.pointInLine(p) && pointInLine(p);
            }
        }
    }

    /**
     * check if this 2 lines intersect with this line, false otherwise.
     *
     * @param other1 - first line to check.
     * @param other2 - second line to check.
     * @return true if this 2 lines intersect with this line, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    /**
     * receive line and return the point of the intersect point between them.
     *
     * @param other - the other line to find the intersection
     * @return the point of the intersect point between them
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            if (this.start.equals(this.end) && other.start.equals(other.end)) {
                return other.start;
            } else {
                double intersectX, intersectY, b, incline;
                // Find the inclines of the lines
                double m1 = (this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX());
                double m2 = (other.start.getY() - other.end.getY())
                        / (other.start.getX() - other.end.getX());
                if (doubleEquals(m1, m2) || (isInfinite(m1) && (isInfinite(m2)))) {
                    if (parallelIntersect(other) == 1 || parallelIntersect(other) == 2) {
                        return null;
                    }
                    if (parallelIntersect(other) == 0) {
                        if (this.start().equals(other.start()) || this.start().equals(other.end())) {
                            return this.start();
                        }
                        if (this.end().equals(other.end()) || this.end().equals(other.start())) {
                            return this.end();
                        }
                    }
                }
                if (isInfinite(m1)) {
                    double b2 = other.start.getY() - (m2 * other.start.getX());
                    intersectY = m2 * this.start.getX() + b2;
                    Point p = new Point(this.start.getX(), intersectY);
                    if (other.pointInLine(p) && pointInLine(p)) {
                        return p;
                    }
                } else if (isInfinite(m2)) {
                    double b1 = this.start.getY() - (m1 * this.start.getX());
                    intersectY = m1 * other.start.getX() + b1;
                    Point p = new Point(other.start.getX(), intersectY);
                    if (other.pointInLine(p) && pointInLine(p)) {
                        return p;
                    }
                } else {
                    double b1 = this.start.getY() - (m1 * this.start.getX());
                    double b2 = other.start.getY() - (m2 * other.start.getX());

                    b = b1 - b2;
                    incline = m2 - m1;
                    intersectX = b / incline;

                    intersectY = m1 * intersectX + b1;

                    return new Point(intersectX, intersectY);
                }
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * check if two lines are equals.
     *
     * @param other - other line to check if they are equals
     * @return true if the lines are equals
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()))
                || (this.start.equals(other.end()) && this.end.equals(other.start()));
    }

    /**
     * receive rectangle and find the closet point if there is one.
     *
     * @param rect - rectangle to find the closet rectangle
     * @return closet intersection point with the rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double minDis;
        int minIndex = 0;
        if (rect == null) {
            return null;
        }
        List<Point> intersectionPoints;
        intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point first = intersectionPoints.get(0);
        minDis = first.distance(this.start());
        // Run through all the points and check find the closet one.
        for (int i = 1; i < intersectionPoints.size(); i++) {
            double dis = intersectionPoints.get(i).distance(this.start());

            if (dis < minDis) {
                minDis = dis;
                minIndex = i;
            }
        }
        return intersectionPoints.get(minIndex);
    }
}
