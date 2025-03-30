/**
 * @author roei derman 322467184
 */
package game;

import Geometry.Point;
import Geometry.Line;
import biuoop.DrawSurface;
import collisions.CollisionInfo;
import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;

/**
 * class of ball.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private java.awt.Color color;
    private Velocity vel = new Velocity(3, 4);
    private GameEnvironment gameEnvironment;

    /**
     * constructor of the Ball.
     *
     * @param center - of the circle
     * @param r      - radius of the circle
     * @param color  - of the circle
     */
    public Ball(Point center, int r, Color color) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
    }

    /**
     * constructor of the Ball.
     *
     * @param x     - X of the center of the circle
     * @param y     - Y of the center of the circle
     * @param r     - radius of the circle
     * @param color - of the circle
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball object with the specified center point, radius, color, and game environment.
     *
     * @param center          the center point of the ball.
     * @param r               the radius of the ball.
     * @param color           the color of the ball.
     * @param gameEnvironment the game environment in which the ball exists.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(center.getX(), center.getY());
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @param c the color of the ball
     */
    public void setColor(Color c) {
        this.color = c;
    }
    /**
     * set the game environment.
     *
     * @param g - game environment to set
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
    }


    /**
     * set velocity.
     *
     * @param v - new velocity to set
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }
    /**
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * move the ball one step.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.gameEnvironment == null) {
            System.out.println("Cant be game without environments");
            System.exit(0);
        }
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.getVelocity().getDx(),
                this.center.getY() + this.getVelocity().getDy());
        CollisionInfo collisionInfo;
        collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo != null) {
            this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                    this.getVelocity()));
            trajectory = new Line(this.center.getX(), this.center.getY(),
                    this.center.getX() + this.getVelocity().getDx(),
                    this.center.getY() + this.getVelocity().getDy());
            collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
            if (collisionInfo != null) {
                this.setVelocity(collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(),
                        this.getVelocity()));
            }

        }
    }

//    /**
//     * move the ball in the square.
//     *
//     * @param edgeStart of the square
//     * @param edgeEnd   of the square
//     */
//    public void moveOneStep(int edgeStart, int edgeEnd) {
//        this.center = this.getVelocity().applyToPoint(this.center);
//        if (this.center.getX() <= edgeStart + r) {
//            this.center.setX(edgeStart + r);
//        }
//        if (this.center.getX() >= edgeEnd - r) {
//            this.center.setX(edgeEnd - r);
//        }
//        if (this.center.getY() <= edgeStart + r) {
//            this.center.setY(edgeStart + r);
//        }
//        if (this.center.getY() >= edgeEnd - r) {
//            this.center.setY(edgeEnd - r);
//        }
//        if (this.center.getX() + r >= edgeEnd) {
//            this.vel.setDx(-this.vel.getDx());
//        }
//        if (this.center.getY() + r >= edgeEnd) {
//            this.vel.setDy(-this.vel.getDy());
//        }
//        if (this.center.getX() - r <= edgeStart) {
//            this.vel.setDx(-this.vel.getDx());
//        }
//        if (this.center.getY() - r <= edgeStart) {
//            this.vel.setDy(-this.vel.getDy());
//        }
//    }
//
//    /**
//     * move the ball in the rectangle.
//     *
//     * @param edgeStart    - of the rectangle
//     * @param edgeEndRight - of the rectangle
//     * @param edgeEndDown  - of the rectangle
//     */
//    public void moveOneStepInRectangle(int edgeStart, int edgeEndRight, int edgeEndDown) {
//        this.center = this.getVelocity().applyToPoint(this.center);
//        if (this.center.getX() <= edgeStart + r) {
//            this.center.setX(edgeStart + r);
//        }
//        if (this.center.getY() <= edgeStart + r) {
//            this.center.setY(edgeStart + r);
//        }
//        if (this.center.getX() >= edgeEndRight - r) {
//            this.center.setX(edgeEndRight - r);
//        }
//        if (this.center.getY() >= edgeEndDown - r) {
//            this.center.setY(edgeEndDown - r);
//        }
//        if (this.center.getX() + r >= edgeEndRight) {
//            this.vel.setDx(-this.vel.getDx());
//        }
//        if (this.center.getY() + r >= edgeEndDown) {
//            this.vel.setDy(-this.vel.getDy());
//        }
//        if (this.center.getX() - r <= edgeStart) {
//            this.vel.setDx(-this.vel.getDx());
//        }
//        if (this.center.getY() - r <= edgeStart) {
//            this.vel.setDy(-this.vel.getDy());
//        }
//    }

    /**
     * @param leftUp    edge left of the square
     * @param rightDown edge right of the square
     * @return true if the ball will touch , false otherwise
     */
    private boolean willTouch(Point leftUp, Point rightDown) {
        return this.center.getX() + this.vel.getDx() - r < rightDown.getX()
                && this.center.getX() + this.vel.getDx() + r > leftUp.getX()
                && this.center.getY() + this.vel.getDy() + r > leftUp.getY()
                && this.center.getY() + this.vel.getDy() - r < rightDown.getY();
    }

//    /**
//     * change the velocity of the ball if it will touch the square.
//     *
//     * @param edgeLeftUp    edge left of the square
//     * @param edgeRightDown edge right of the square
//     */
//    public void intersectingSquare(Point edgeLeftUp, Point edgeRightDown) {
//        if (willTouch(edgeLeftUp, edgeRightDown)) {
//            if (!(this.center.getY() + r > edgeLeftUp.getY()
//                    && this.center.getY() - r < edgeRightDown.getY())) {
//                this.vel.setDy(-this.vel.getDy());
//            } else if (!(this.center.getX() - r < edgeRightDown.getX()
//                    && this.center.getX() + r > edgeLeftUp.getX())) {
//                this.vel.setDx(-this.vel.getDx());
//            }
//        }
//    }

    /**
     * remove ball from the game.
     *
     * @param g - to remove from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}
