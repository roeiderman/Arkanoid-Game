/**
 * @author roei derman 322467184
 */
package Listeners;
/**
 * HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - listener that hit
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - remove this.
     */
    void removeHitListener(HitListener hl);

}
