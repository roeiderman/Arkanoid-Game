/**
 * @author roei derman 322467184
 */
package sprites;
import Listeners.Counter;
import biuoop.DrawSurface;
import game.Game;

import java.awt.Color;

/**
 * score indicator class.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructor.
     *
     * @param score - score of the player.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * @param d to draw
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.drawRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(370, 20, "Score: " + score.getValue(), 20);
    }

    /**
     * time passed.
     */
    public void timePassed() {

    }

    @Override
    public void addToGame(Game g) {

    }
}
