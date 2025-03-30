/**
 * @author roei derman 322467184
 */
package Listeners;
import collisions.Block;
import game.Ball;
import game.Game;

/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     * @param g - game
     * @param counter - remaining balls
     */
    public BallRemover(Game g, Counter counter) {
        this.game = g;
        this.remainingBalls = counter;
    }

    /**
     * This method is called whenever the beingHit object is hit,
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - block that hit.
     * @param hitter   - the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
