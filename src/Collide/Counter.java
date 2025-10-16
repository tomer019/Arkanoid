// 319126991 Tomer Grady
package Collide;

/**
 * The Counter class is used to keep track of a count, which can be increased or decreased.
 */
public class Counter {
    private int count;

    /**
     * Constructs a Counter initialized to zero.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Increases the counter by a specified number.
     *
     * @param number the amount to increase the counter by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decreases the counter by a specified number.
     *
     * @param number the amount to decrease the counter by.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Returns the current count value.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.count;
    }
}
