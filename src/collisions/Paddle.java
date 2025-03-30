/**
 * @author roei derman 322467184
*/
package collisions;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import Geometry.Rectangle;
import Geometry.Point;
import game.Ball;
import sprites.Sprite;
import sprites.Velocity;
import game.Game;

/**
 * Class of Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int WIDTH = 800;
    private final Rectangle paddle;
    private final KeyboardSensor keyboard;
    private double dx = 5;
    private static final int ANGLE_STEPS = 30;
    private static final int BORDER_SIZE = 9;


    /**
     * constructor.
     *
     * @param paddle   - of the game
     * @param keyboard - to get sensor from the keyboard
     */
    public Paddle(Rectangle paddle, KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.keyboard = keyboard;
    }

    /**
     * @param paddle   -  of the game
     * @param dx       - steps that the paddle moves.
     * @param keyboard - to get sensor from the keyboard
     */
    public Paddle(Rectangle paddle, double dx, KeyboardSensor keyboard) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        this.dx = dx;
    }

    /**
     * Move Left the paddle.
     */
    public void moveLeft() {
        if (this.paddle.getUpperRight().getX() <= BORDER_SIZE) {
            this.paddle.setUpperLeft(new Point(WIDTH - BORDER_SIZE,
                    this.paddle.getUpperLeft().getY()));
        } else {
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() - this.dx,
                    this.paddle.getUpperLeft().getY()));
        }
    }

    /**
     * Move Right the paddle.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() > WIDTH) {
            this.paddle.setUpperLeft(new Point(BORDER_SIZE - this.paddle.getWidth(),
                    this.paddle.getUpperLeft().getY()));
        } else {
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX() + this.dx,
                    this.paddle.getUpperLeft().getY()));
        }
    }


    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double disPart = this.paddle.getWidth() / 5;
        Velocity newV = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        if (this.paddle.inLeftLine(collisionPoint)) {
            newV.setDx(-currentVelocity.getDx());
        }
        if (this.paddle.inRightLine(collisionPoint)) {
            newV.setDx(-currentVelocity.getDx());
        }
        if (this.paddle.inDownLine(collisionPoint)) {
            newV.setDy(-currentVelocity.getDy());
        }

        if (this.paddle.inUpLine(collisionPoint)) {
            // find the speed of the ball
            double speed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                    + (currentVelocity.getDy() * currentVelocity.getDy()));

            for (int i = 1; i <= 5; i++) {
                // check if the ball hit the third regime.
                if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + (disPart * 3)
                        && collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + disPart * 2) {
                    newV.setDy(-currentVelocity.getDy());
                    break;
                }
                // check what is the region that the ball hit.
                if (collisionPoint.getX() <= this.paddle.getUpperLeft().getX() + (disPart * i)
                        && collisionPoint.getX() >= this.paddle.getUpperLeft().getX() + disPart * (i - 1)) {
                    newV = Velocity.fromAngleAndSpeed(-60 + ((i - 1) * ANGLE_STEPS), speed);
                    break;
                }
            }
        }
        return newV;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void addToGame(Game g) {
        if (g == null) {
            System.out.println("Not a game");
            return;
        }
        g.addSprite(this);
        g.addCollidable(this);
    }
}
