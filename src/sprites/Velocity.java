package sprites;
import Geometry.Point;

/**
 * @author roei derman 322467184
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
        private double dx;
        private double dy;

    /**
     * Constructor.
     * @param dx - first double
     * @param dy - second double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @param angle of the ball
     * @param speed of the ball
     * @return new velocity with dx and dy from the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = Math.cos(angle) * speed;
        return new Velocity(dx, -dy);
    }

    /**
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * set dx of the velocity.
     * @param dx - receive dx the change
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * set dy of the velocity.
     * @param dy - receive dy the change
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * @param p - point to change.
     * @return a new point with new coordination
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        } else {
            return new Point(p.getX() + dx, p.getY() + dy);
        }
    }
}
