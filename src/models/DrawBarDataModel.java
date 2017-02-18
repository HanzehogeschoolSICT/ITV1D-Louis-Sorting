package models;

public class DrawBarDataModel {
    private int currentX;
    private int heightPerNumber;
    private int widthPerBar;

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
