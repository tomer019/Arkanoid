//319126991 Tomer Grady
package Collections;

import biuoop.DrawSurface;

import java.util.LinkedList;
import Sprites.Sprite;
import java.util.List;
/**
 * Manages a collection of sprites in the game.
 */
public class SpriteCollection {
    private final List<Sprite> sprites = new LinkedList<>();

    /**
     * Adds a sprite to the list of sprites.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Removes a sprite from the list.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new LinkedList<>(sprites)) {
            sprite.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites.
     *
     * @param d drawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : new LinkedList<>(sprites)) {
            sprite.drawOn(d);
        }
    }
}