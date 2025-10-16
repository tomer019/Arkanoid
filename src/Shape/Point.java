//319126991 Tomer Grady
package Shape;

/**
 * The Shape.Point class represents a point in a 2D space with x and y coordinates.
 * It provides methods to calculate the distance to another point and to check for equality with another point.
 */
public class Point {
    private double x; // The x coordinate of the point
    private double y; // The y coordinate of the point
    private static final double THRESHOLD = 0.000001; // Define a small threshold for equality comparison

    /**
     * Constructor to create a Shape.Point with specified x and y coordinates.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance from this point to another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx, dy;
        dx = this.x - other.getX();
        dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy); // Use the Euclidean distance formula
    }

    /**
     * Check if this point is equal to another point.
     * Two points are considered equal if the difference between their x and y coordinates
     * is less than a small threshold.
     *
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return Math.abs(this.x - other.x) < THRESHOLD && Math.abs(this.y - other.y) < THRESHOLD;
    }

    /**
     * Get the x coordinate of the point.
     *
     * @return the x coordinate
     */
    public double getX() {

        return x;
    }

    /**
     * Get the y coordinate of the point.
     *
     * @return the y coordinate
     */
    public double getY() {

        return y;
    }

}

