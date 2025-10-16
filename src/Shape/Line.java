//319126991 Tomer Grady
package Shape;

import java.util.List;

/**
 * Class <b>Shape.Line</b>: representing a line (between 2 points).
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructor.
     *
     * @param start a point.
     * @param end   a point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor that receives the coordinates.
     *
     * @param x1 start's x coordinate.
     * @param y1 start's y coordinate.
     * @param x2 end's x coordinate.
     * @param y2 end's y coordinate.
     */

    /**
     * @return the line's length.
     */
    public double length() {
        return start.distance(end);
    }


    /**
     * @return the start point.
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point.
     */
    public Point end() {
        return end;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other another point.
     * @return the intersection point between this and other, if exists.
     */
    public Point intersectionWith(Line other) {
        // Check if the lines are equal
        if (equals(other)) {
            return null;
        }

        // Check if the start point of this line coincides with the other line
        if ((start.equals(other.start) || start.equals(other.end)) && !(other.isOnLine(end))) {
            return start;
        }

        // Check if the end point of this line coincides with the other line
        if ((end.equals(other.start) || end.equals(other.end)) && !(other.isOnLine(start))) {
            return end;
        }

        // Shape.Line equation coefficients for this line
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());

        // Shape.Line equation coefficients for the other line
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        // Determinant of the line equations
        double determinant = a1 * b2 - a2 * b1;

        // If determinant is zero, the lines are parallel
        if (determinant == 0) {
            return null;
        } else {
            // Calculate the intersection point
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;

            Point intersection = new Point(x, y);

            // Check if the intersection point lies on both lines
            if (this.isOnLine(intersection) && other.isOnLine(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * @param point a point.
     * @return if the point is on the line.
     */
    public boolean isOnLine(Point point) {
        return Math.abs(point.distance(start) + point.distance(end) - length()) < Math.pow(10, -5);
    }

    /**
     * @param other another line (can be null).
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return (start.equals(other.start)) && (end.equals(other.end));
    }

    /**
     * @param rect a rectangle.
     * @return the closest intersection with {@code rect} to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        Point closest = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        for (Point point : intersectionPoints) {
            if (point.distance(start) < closest.distance(start)) {
                closest = point;
            }
        }

        return closest;
    }
}