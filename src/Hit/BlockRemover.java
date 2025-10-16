// 319126991 Tomer Grady
package Hit;

import Game.Game;
import Collide.Counter;
import Sprites.Block;
import Sprites.Ball;

/**
 * The BlockRemover class is responsible for removing blocks from the game.
 * It is a listener that gets notified when a block is hit and removes the block from the game.
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object.
     *
     * @param game           the game instance.
     * @param remainingBlocks the counter for the remaining blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit.
     * @param hitter   the ball that is hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // Remove the block from the game
        beingHit.removeFromGame(game);

        // Remove this listener from the block
        beingHit.removeHitListener(this);

        // Decrease the counter of remaining blocks
        remainingBlocks.decrease(1);
    }
}
