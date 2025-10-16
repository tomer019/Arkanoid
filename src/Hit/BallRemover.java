//319126991 Tomer Grady
package Hit;

import Game.Game;
import Collide.Counter;
import Sprites.Block;
import Sprites.Ball;
/**
 * The BallRemover class is responsible for removing balls from the game.
 * It is a listener that gets notified when a ball hits a specific block (e.g., the death region).
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game           a Game.Game.
     * @param remainingBalls Collide.Counter of the remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);

        remainingBalls.decrease(1);
    }
}