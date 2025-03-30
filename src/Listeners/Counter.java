/**
 * @author roei derman 322467184
 */
package Listeners;
/**
 * Counter class.
 */
public class Counter {
    private int num;

    /**
     * Constructor.
     * @param number - number to constructor.
     */
    public Counter(int number) {
        this.num = number;
    }

    /**
     * @param number - to increase.
     */
    public void increase(int number) {
        this.num = this.num + number;
    }

    /**
     * @param number - to decrease.
     */
    public void decrease(int number) {
        this.num = this.num - number;
    }

    /**
     * @return the current count.
     */
    public int getValue() {
        return this.num;
    }
}
