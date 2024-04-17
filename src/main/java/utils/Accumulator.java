package utils;

public class Accumulator {
    private double sum;
    private int count;

    public Accumulator(double sum, int count) {
        this.sum = sum;
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    public void add(double value) {
        sum += value;
        count++;
    }

    public void reset() {
        sum = 0;
        count = 0;
    }

    /**
     * Returns the average of the values added to the accumulator.
     * If no values have been added, the method returns 0.
     */
    public double getAverage() {
        if (this.count == 0) {

            return 0;
        } else {
            return this.sum / this.count;
        }
    }

}
