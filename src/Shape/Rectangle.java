//319126991 Tomer Grady
package Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * The Shape.Rectangle class represents a rectangle in a 2D space.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a Shape.Rectangle with the specified upper left corner, width, and height.
     *
     * @param upperLeft the upper left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the upper left corner of the rectangle.
     *
     * @return the upper left corner of the rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Returns a list of intersection points between the given line and the edges of the rectangle.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points between the line and the rectangle's edges
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();

        // Define the four corners of the rectangle
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);

        // Define the four edges of the rectangle
        Line[] edges = {
                new Line(upperLeft, upperRight), // top edge
                new Line(lowerLeft, lowerRight), // bottom edge
                new Line(upperLeft, lowerLeft), // left edge
                new Line(upperRight, lowerRight) // right edge
        };

        // Check for intersections between the line and each edge of the rectangle
        for (Line edge : edges) {
            Point intersection = line.intersectionWith(edge);
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }

        return intersectionPoints;
    }
}
