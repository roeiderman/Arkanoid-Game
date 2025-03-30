package sprites;
import biuoop.DrawSurface;
import game.Game;
/**
 * @author roei derman 322467184
 * sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d to draw
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * add the Sprite to the game.
     * @param g - game object to add to it the sprites
     */
    void addToGame(Game g);
}
