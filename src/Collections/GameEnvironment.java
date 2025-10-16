//319126991 Tomer Grady
package Collections;

import Shape.Line;
import Shape.Point;
import Collide.Collidable;
import Collide.CollisionInfo;
import java.util.LinkedList;
import java.util.List;

/**
 * The Collections.GameEnvironment class manages the collection of collidable objects in the game.
 * It provides methods to add collidables and find the closest collision point with a given trajectory.
 */
public class GameEnvironment {
    private final List<Collidable> collidables = new LinkedList<>();

    /**
     * Adds the given collidable to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * Determines the closest collision point of an object moving along the given trajectory
     * with any of the collidables in this collection. If no collision occurs, returns null.
     *
     * @param trajectory the trajectory line of the moving object
     * @return the Collide.CollisionInfo of the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minDistance = Double.MAX_VALUE;
        Point closestPoint = null;
        Collidable closestObject = null;

        // Iterate through all collidables to find the closest collision point
        for (Collidable collidable : collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                double distance = collisionPoint.distance(trajectory.start());
                if (distance < minDistance) {
                    minDistance = distance;
                    closestPoint = collisionPoint;
                    closestObject = collidable;
                }
            }
        }

        // Return the collision info if a collision point is found, otherwise return null
        if (closestPoint != null) {
            return new CollisionInfo(closestPoint, closestObject);
        }
        return null;
    }
}
