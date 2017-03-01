package models;

public class DrawBarDataModel {
    private final double heightPerNumber;
    private final double widthPerBar;
    private int currentX;

    /**
     * Construct the draw bar data model.
     *
     * @param heightPerNumber Height per number of a bar.
     * @param widthPerBar     Width per bar.
     */
    public DrawBarDataModel(double heightPerNumber, double widthPerBar) {
        this.heightPerNumber = heightPerNumber;
        this.widthPerBar = widthPerBar;
    }

    /**
     * Get the current X position to draw on.
     *
     * @return Current X position to draw on.
     */
    public int getCurrentX() {
        return currentX;
    }

    /**
     * Increase the current X position to draw on.
     */
    public void increaseCurrentX() {
        currentX += this.widthPerBar;
    }

    /**
     * Get the height of the bar for the specified number.
     *
     * @return Height of the bar for the specified number.
     */
    public double getHeightForBar(int number) {
        return heightPerNumber * number;
    }

    /**
     * Get the width per bar.
     *
     * @return Width per bar.
     */
    public double getWidthPerBar() {
        return widthPerBar;
    }
}
