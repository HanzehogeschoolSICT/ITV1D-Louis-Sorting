package displays;

import controllers.DataSetController;
import data.Settings;
import models.DataSetModel;
import models.DrawBarDataModel;

import javax.swing.*;
import java.awt.*;

class DataSetDisplay extends JPanel {
    private final DataSetController dataSetController;

    /**
     * Construct the data set display.
     *
     * @param dataSetController Data set controller to use.
     */
    DataSetDisplay(DataSetController dataSetController) {
        this.dataSetController = dataSetController;
    }

    /**
     * Get the preferred size of the data set display.
     *
     * @return Preferred size of the data set display.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Settings.DATA_SET_DISPLAY_WIDTH, Settings.DATA_SET_DISPLAY_HEIGHT);
    }

    /**
     * Display the current data set.
     */
    void displayDataSet() {
        repaint();
    }

    /**
     * Draw the current data set.
     *
     * @param graphics Graphics to draw the current data set on.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        DataSetModel dataSet = dataSetController.getDataSet();

        int heightPerNumber = getHeight() / dataSet.getHighestNumber();
        int widthPerBar = getWidth() / dataSet.getHighestNumber();

        graphics.setColor(Settings.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        DrawBarDataModel drawBarData = new DrawBarDataModel(heightPerNumber, widthPerBar);
        for (Integer number : dataSet)
            drawBar(graphics, number, drawBarData, getBarColor(dataSet, number));
    }

    /**
     * Draw a single bar on using the specified graphics.
     *
     * @param graphics Graphics to draw the bar on.
     * @param number Number that should be represented by the bar.
     * @param drawBarData General data to compute the size of the bar.
     * @param color Color of the bar.
     */
    private void drawBar(Graphics graphics, Integer number, DrawBarDataModel drawBarData, Color color) {
        int barHeight = drawBarData.getHeightForBar(number);
        int x = drawBarData.getCurrentX();
        int y = getHeight() - barHeight;

        Rectangle barRectangle = new Rectangle(x, y, drawBarData.getWidthPerBar(), barHeight);

        graphics.setColor(color);
        graphics.fillRect(barRectangle.x, barRectangle.y, barRectangle.width, barRectangle.height);

        drawNumberInBar(graphics, number, barRectangle);
        drawBarData.increaseCurrentX();
    }

    /**
     * Draw a number in a single bar using the specified graphics.
     *
     * @param graphics Graphics to draw the number on.
     * @param number Number that should be drawn in the bar.
     * @param barRectangle Area of the bar in the specified graphics.
     */
    private void drawNumberInBar(Graphics graphics, Integer number, Rectangle barRectangle) {
        String numberText = number.toString();
        FontMetrics fontMetrics = graphics.getFontMetrics();

        int x = barRectangle.x + ((barRectangle.width - fontMetrics.stringWidth(numberText)) / 2);
        int y = barRectangle.y + fontMetrics.getHeight();

        graphics.setColor(Color.WHITE);
        graphics.drawString(numberText, x, y);
    }

    /**
     * Get the color to use to draw the specified number.
     *
     * @param number Number to get the color for.
     * @return Color to use to draw the specified number.
     */
    private Color getBarColor(DataSetModel dataSet, int number) {
        if (dataSet.getIsSorted())
            return Settings.BAR_SORTED_COLOR;

        if (dataSet.isNumberCompared(number))
            return Settings.BAR_COMPARED_COLOR;

        return Settings.BAR_COLOR;
    }
}
