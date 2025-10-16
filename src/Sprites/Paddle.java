//319126991 Tomer Grady
package Sprites;

import Game.Game;
import Shape.Point;
import Shape.Rectangle;
import Shape.Velocity;
import Collide.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Sprites.Paddle class represents the player-controlled paddle in the game.
 * It implements both the Sprites.Sprite and Collide.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int speed;

    /**
     * Constructs a Sprites.Paddle with the specified keyboard sensor, rectangle, color, and speed.
     *
     * @param keyboard the keyboard sensor to control the paddle
     * @param paddle   the rectangle representing the paddle's shape and position
     * @param color    the color of the paddle
     * @param speed    the speed of the paddle's movement
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddle, Color color, int speed) {
        this.keyboard = keyboard;
        this.paddle = paddle;
        this.color = color;
        this.speed = speed;
    }

    /**
     * Moves the paddle left, wrapping around to the right edge if it goes off the screen.
     */
    public void moveLeft() {
        if (this.paddle.getUpperLeft().getX() <= 0) {
            // Move to the right edge
            this.paddle = new Rectangle(
                    new Point(800 - this.paddle.getWidth(), this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(
                    new Point(this.paddle.getUpperLeft().getX() - speed, this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Moves the paddle right, wrapping around to the left edge if it goes off the screen.
     */
    public void moveRight() {
        if (this.paddle.getUpperLeft().getX() + this.paddle.getWidth() >= 800) {
            // Move to the left edge
            this.paddle = new Rectangle(
                    new Point(0, this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        } else {
            this.paddle = new Rectangle(
                    new Point(this.paddle.getUpperLeft().getX() + speed, this.paddle.getUpperLeft().getY()),
                    this.paddle.getWidth(), this.paddle.getHeight());
        }
    }

    /**
     * Handles the passing of time. Checks for left and right arrow key presses to move the paddle.
     */
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draws the paddle on the given drawing surface.
     *
     * @param d the drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Notifies the paddle of a collision with a ball and returns the new velocity of the ball after the collision.
     * The bounce angle depends on the region of the paddle that was hit.
     *
     * @param collisionPoint  the point where the collision occurred
     * @param currentVelocity the velocity of the ball before the collision
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = this.paddle.getWidth() / 5;
        double hitX = collisionPoint.getX() - this.paddle.getUpperLeft().getX();

        if (hitX <= regionWidth) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        } else if (hitX <= 2 * regionWidth) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        } else if (hitX <= 3 * regionWidth) {
            // Ensuring the ball does not get stuck by adding a small vertical velocity
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy() - 0.1);
        } else if (hitX <= 4 * regionWidth) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        } else {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
    }

    /**
     * Adds the paddle to the game as both a collidable and a sprite.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
