/**
 * @author roei derman 322467184
 */
package Listeners;
import collisions.Block;
import game.Ball;
/**
 * hit listener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - block that hit.
     * @param hitter - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

