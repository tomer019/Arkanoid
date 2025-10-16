//319126991 Tomer Grady
package Sprites;

import Game.Game;
import Hit.HitListener;
import Hit.HitNotifier;
import Shape.Point;
import Shape.Rectangle;
import Shape.Velocity;
import Collide.Collidable;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.Block class represents a rectangular block in the game.
 * It implements both the Collide.Collidable and Sprites.Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private static final double EPSILON = 0.00000001; // A small constant epsilon for precision
    private final List<HitListener> hitListeners = new ArrayList<>();
    private boolean isBorder;


    /**
     * Constructs a Sprites.Block with the specified rectangle and color.
     *
     * @param rectangle the rectangle representing the block's shape
     * @param color the color of the block
     * @param isBorder the border of the block
     */
    public Block(Rectangle rectangle, Color color, boolean isBorder) {
        this.rectangle = rectangle;
        this.color = color;
        this.isBorder = isBorder;
    }

    /**
     * Returns the collision rectangle representing the shape of the block.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Handles the collision with the block.
     * Modifies the velocity of the colliding object based on the collision location.
     *
     * @param collisionPoint the point of collision with the block
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity after collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!this.isBorder) {
            if (!ballColorMatch(hitter)) {
                notifyHit(hitter);
                hitter.setColor(this.color);
            }
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Get the boundaries of the rectangle
        double left = rectangle.getUpperLeft().getX();
        double right = rectangle.getUpperLeft().getX() + rectangle.getWidth();
        double top = rectangle.getUpperLeft().getY();
        double bottom = rectangle.getUpperLeft().getY() + rectangle.getHeight();

        // Check for collision with vertical edges (left and right)
        if (Math.abs(collisionPoint.getX() - left) < EPSILON || Math.abs(collisionPoint.getX() - right) < EPSILON) {
            dx = -dx;
        }

        // Check for collision with horizontal edges (top and bottom)
        if (Math.abs(collisionPoint.getY() - top) < EPSILON || Math.abs(collisionPoint.getY() - bottom) < EPSILON) {
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }

    /**
     * Draws the block on the given DrawSurface.
     *
     * @param surface the surface on which to draw the block
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();

        // Fill the block with its color
        surface.setColor(color);
        surface.fillRectangle(x, y, width, height);

        // Draw the border of the block in gray
        surface.setColor(Color.GRAY);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Empty method stub for the Sprites.Sprite interface.
     * Blocks do not have any animations or time-based behaviors in this context.
     */
    @Override
    public void timePassed() {
        // Empty method stub for the Sprites.Sprite interface
    }

    /**
     * Adds the block to the game.
     * Registers the block as both a collidable and a sprite in the game environment.
     *
     * @param g the game to which the block is added
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * Removes the ball from the game by removing it from the collidables and sprites collections.
     *
     * @param game the game instance from which to remove the ball
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Checks if the color of this ball matches the color of another ball.
     *
     * @param ball the other ball to compare colors with
     * @return true if colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }
    /**
     * Adds a hit listener to the ball.
     *
     * @param hl the hit listener to add
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the ball.
     *
     * @param hl the hit listener to remove
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball object that hit this ball
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}