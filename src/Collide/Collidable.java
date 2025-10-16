//319126991 Tomer Grady
package Collide;

import Shape.Point;
import Shape.Rectangle;
import Shape.Velocity;
import Sprites.Ball;

/**
 * The Collide.Collidable interface represents objects that can be collided with in the game.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision rectangle representing the shape and position of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the given collision point with a specified velocity.
     * The method calculates the new velocity expected after the hit based on the force the object inflicted.
     *
     * @param hitter the ball that is hitting the object.
     * @param collisionPoint the point where the collision occurred on the collision rectangle.
     * @param currentVelocity the velocity of the object before the collision.
     * @return the new velocity after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
