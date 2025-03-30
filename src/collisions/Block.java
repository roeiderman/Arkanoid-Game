/**
 * @author roei derman 322467184
 */
package collisions;

import Listeners.HitListener;
import Listeners.HitNotifier;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Geometry.Point;
import Geometry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;
import game.Game;
import game.Ball;

/**
 * @author roei derman 322467184
 * class of block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rect;
    private Color rectColor = Color.green;
    private final List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param r - rectangle
     */
    public Block(Rectangle r) {
        this.rect = r;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @param r - rectangle
     * @param c - color of the block
     */
    public Block(Rectangle r, Color c) {
        this.rect = r;
        this.rectColor = c;
        this.hitListeners = new ArrayList<>();
    }

    public Color getRectColor() {
        return this.rectColor;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        if (currentVelocity.getDy() > 0 && rect.inUpLine(collisionPoint)) {
            newV.setDy(-currentVelocity.getDy());
        } else if (currentVelocity.getDy() < 0 && rect.inDownLine(collisionPoint)) {
            newV.setDy(-currentVelocity.getDy());
        } else if (currentVelocity.getDx() > 0 && rect.inLeftLine(collisionPoint)) {
            newV.setDx(-currentVelocity.getDx());
        } else if (currentVelocity.getDx() < 0 && rect.inRightLine(collisionPoint)) {
            newV.setDx(-currentVelocity.getDx());
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return newV;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.rectColor);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl - listener that hit
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl - remove this.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param ball - to check colors
     * @return true if the block has the same color as the ball
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.rectColor);
    }

    /**
     * @param game to remove from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
