//319126991 Tomer Grady
package Shape;

/**
 * The Shape.Velocity class represents the change in position on the x and y axes.
 * It provides methods to get and set the velocity components and to apply the velocity to a point.
 */
public class Velocity {
    // The fields
    private double dx;
    private double dy;

    /**
     * Constructor to create a Shape.Velocity with specified dx and dy.
     *
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get the change in position along the x-axis.
     *
     * @return the dx value
     */
    public double getDx() {
        return dx;
    }

    /**
     * Get the change in position along the y-axis.
     *
     * @return the dy value
     */
    public double getDy() {
        return dy;
    }

    /**
     * Set the change in position along the x-axis.
     *
     * @param dx the new dx value
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Set the change in position along the y-axis.
     *
     * @param dy the new dy value
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Apply the velocity to a point, resulting in a new point.
     *
     * @param p the point to which the velocity will be applied
     * @return a new Shape.Point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Create a Shape.Velocity from an angle and speed.
     * The angle is given in radians.
     *
     * @param angle the direction of the velocity in radians
     * @param speed the speed in the direction of the angle
     * @return a new Shape.Velocity object with the calculated dx and dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Get the speed of the velocity.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
}
