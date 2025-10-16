//319126991 Tomer Grady
package Sprites;

import Collections.GameEnvironment;
import Game.Game;
import Shape.Line;
import Shape.Point;
import Shape.Velocity;
import Collide.CollisionInfo;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Sprites.Ball class represents a ball in a 2D space that can move and bounce off boundaries.
 */
public class Ball implements Sprite {
    // The fields
    private Point center;
    private double radius;
    private Color color;
    private Velocity velocity;
    private static final double THRESHOLD = 0.000001; // Define a small threshold for floating-point comparison
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Sprites.Ball with the specified center point, radius, and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     * @param gameEnvironment  the gameEnvironment of the ball
     */
    public Ball(Point center, double radius, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Gets the size of the ball.
     *
     * @return the radius of the ball
     */
    public double getSize() {
        return radius;
    }

    /**
     * Draws the ball on the given drawing surface.
     *
     * @param surface the drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), (int) radius);
    }

    /**
     * Moves the ball one step, updating its position based on its velocity,
     * and handles collisions with collidable objects in the game environment.
     */
    public void moveOneStep() {
        // Compute the trajectory of the ball
        Line trajectory = new Line(center, velocity.applyToPoint(center));
        double newLength = radius + trajectory.length();
        double x = ((trajectory.end().getX() * newLength) - radius * trajectory.start().getX()) / trajectory.length();
        double y = ((trajectory.end().getY() * newLength) - radius * trajectory.start().getY()) / trajectory.length();

        Line extendedLine = new Line(trajectory.start(), new Point(x, y));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(extendedLine);

        if (collisionInfo == null) {
            // No collision, move the ball to the end of the trajectory
            this.center = trajectory.end();
        } else {
            // Collision detected, update velocity and handle collision
            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
        }
    }

    /**
     * Adds the ball to the game, registering it as a sprite.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * Notifies the ball that time has passed, moving it one step.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Sets the color of the ball.
     *
     * @param color the new color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Removes the ball to the sprites collection in the game.Adds the ball to the sprites collection in the game.
     *
     * @param game a Game.Game.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
