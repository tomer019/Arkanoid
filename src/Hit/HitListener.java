// 319126991 Tomer Grady
package Hit;

import Sprites.Block;
import Sprites.Ball;

/**
 * The HitListener interface should be implemented by any class that wants to be notified of hit events.
 * It contains a single method, hitEvent, which is called whenever an object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that is hitting the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
