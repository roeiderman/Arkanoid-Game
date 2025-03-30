/**
 * @author roei derman 322467184
 * class of Rectangle.
 */
package Geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * class of Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private final double width;
    private final double height;

    /**
     * constructor.
     *
     * @param upperLeft - point of the upper left
     * @param width     - of the rectangle
     * @param height    - of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * set the upper left point of the rectangle.
     *
     * @param p - point to set to/
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }

    /**
     * @return the upper right Point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * @param line - to intersect with.
     * @return a (possibly empty) List of intersection points
     * with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        if (line == null) {
            return intersectionPoints;
        }
        Line left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        Line up = new Line(this.upperLeft, new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY()));
        Line down = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        Line right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);

        if (up.intersectionWith(line) != null) {
            intersectionPoints.add(up.intersectionWith(line));
        }
        if (left.intersectionWith(line) != null) {
            intersectionPoints.add(left.intersectionWith(line));
        }
        if (down.intersectionWith(line) != null) {
            intersectionPoints.add(down.intersectionWith(line));
        }
        if (right.intersectionWith(line) != null) {
            intersectionPoints.add(right.intersectionWith(line));
        }
        return intersectionPoints;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @param col - point
     * @return true if the point is in the right line of the rectangle
     */
    public boolean inRightLine(Point col) {
        Line l = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return l.pointInLine(col);
    }

    /**
     * @param col - point
     * @return true if the point is in the left line of the rectangle
     */
    public boolean inLeftLine(Point col) {
        Line l = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return l.pointInLine(col);
    }

    /**
     * @param col - point
     * @return true if the point is in the up line of the rectangle
     */
    public boolean inUpLine(Point col) {
        Line l = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return l.pointInLine(col);
    }

    /**
     * @param col - point
     * @return true if the point is in the down line of the rectangle
     */
    public boolean inDownLine(Point col) {
        Line l = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return l.pointInLine(col);
    }
}
