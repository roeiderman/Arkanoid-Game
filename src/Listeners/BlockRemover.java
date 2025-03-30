/**
 * @author roei derman 322467184
 */
package Listeners;
import game.Ball;
import game.Game;
import collisions.Block;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * @return number of blocks remain in the game.
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    /**
     * @param game            - game.
     * @param remainingBlocks - num of blocks remain in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed.
     *
     * @param beingHit - block that hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.ballColorMatch(hitter)) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
            hitter.setColor(beingHit.getRectColor());
        }
    }
}
