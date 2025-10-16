// 319126991 Tomer Grady
package Hit;

/**
 * The HitNotifier interface should be implemented by any class that can notify hit listeners of hit events.
 * It contains methods to add and remove hit listeners.
 */
public interface HitNotifier {
    /**
     * Adds a hit listener to be notified of hit events.
     *
     * @param hl the hit listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener so it no longer receives hit event notifications.
     *
     * @param hl the hit listener to remove.
     */
    void removeHitListener(HitListener hl);
}
