/**
 * @author roei derman 322467184
 */
package collisions;

import Geometry.Rectangle;
import Geometry.Point;
import game.Ball;
import sprites.Velocity;

/**
 * @author roei derman 322467184
 * interface of collidable.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * receive a collision point and check if the ball is going to hit this collision point,
     * and change the velocity.
     *
     * @param collisionPoint  - point to check collision.
     * @param currentVelocity - velocity of the ball.
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
