//319126991 Tomer Grady
package Hit;

import Game.Game;
import biuoop.DrawSurface;
import Collide.Counter;
import Shape.Rectangle;
import Sprites.Sprite;
import java.awt.Color;
/**
 * A class representing a score indicator that implements the Sprite interface.
 */
public class ScoreIndicator implements Sprite {
    private final Rectangle rectangle;
    private final Counter score;

    /**
     * Constructor.
     *
     * @param rectangle where to draw the indicator.
     * @param score     the score counter.
     */
    public ScoreIndicator(Rectangle rectangle, Counter score) {
        this.rectangle = rectangle;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY);
        surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2),
                (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() * 9 / 10),
                String.format("Score: %d", score.getValue()), 20);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adding the indicator to the sprites list.
     *
     * @param game a game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}