package models;

public class DrawBarDataModel {
    private final int heightPerNumber;
    private final int widthPerBar;
    private int currentX;

    public DrawBarDataModel(int heightPerNumber, int widthPerBar) {
        this.heightPerNumber = heightPerNumber;
        this.widthPerBar = widthPerBar;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void increaseCurrentX() {
        currentX += this.widthPerBar;
    }

    public int getHeightPerNumber() {
        return heightPerNumber;
    }

    public int getWidthPerBar() {
        return widthPerBar;
    }
}
