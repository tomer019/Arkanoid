//319126991 Tomer Grady
package Hit;

import Collide.Counter;
import Sprites.Block;
import Sprites.Ball;
/**
 * A class that tracks and manages scores based on hit events, implementing the HitListener interface.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}