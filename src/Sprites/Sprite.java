//319126991 Tomer Grady
package Sprites;

import biuoop.DrawSurface;

/**
 * The Sprites.Sprite interface represents a drawable object in the game that can also be notified of time passing.
 */
public interface Sprite {

    /**
     * Draws the sprite to the screen.
     *
     * @param d the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     */
    void timePassed();
}
