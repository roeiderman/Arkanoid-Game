/**
 * @author roei derman 322467184
 */
package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * Sprite collection class.
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }
    /**
     * @param s - sprite object to add
     */
    public void addSprite(Sprite s) {
        if (s != null) {
            this.spriteList.add(s);
        }
    }
    public void removeSprite(Sprite s) {
        if (s != null) {
            this.spriteList.remove(s);
        }
    }
    /**
     *call timePassed on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySpriteList = new ArrayList<>(this.spriteList);
        for (Sprite s : copySpriteList) {
            s.timePassed();
        }
    }

    /**
     * call drawOn on all sprites.
     * @param d - drawsurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> copySpriteList = new ArrayList<>(this.spriteList);
        for (Sprite s : copySpriteList) {
            s.drawOn(d);
        }
    }
}
