package displays;

import controllers.DataSetController;
import data.Settings;
import models.DataSetModel;
import models.DrawBarDataModel;

import javax.swing.*;
import java.awt.*;

class DataSetDisplay extends JPanel {
    private final DataSetController dataSetController;

    DataSetDisplay(DataSetController dataSetController) {
        this.dataSetController = dataSetController;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Settings.DATA_SET_DISPLAY_WIDTH, Settings.DATA_SET_DISPLAY_HEIGHT);
    }

    void displayDataSet() {
        repaint();
    }

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
            drawBar(graphics, number, drawBarData, dataSet.getColor(number));
    }

    private void drawBar(Graphics graphics, Integer number, DrawBarDataModel drawBarData, Color color) {
        int barHeight = drawBarData.getHeightPerNumber() * number;
        int x = drawBarData.getCurrentX();
        int y = getHeight() - barHeight;

        Rectangle barRectangle = new Rectangle(x, y, drawBarData.getWidthPerBar(), barHeight);

        graphics.setColor(color);
        graphics.fillRect(barRectangle.x, barRectangle.y, barRectangle.width, barRectangle.height);

        drawNumberInBar(graphics, number, barRectangle);
        drawBarData.increaseCurrentX();
    }

    private void drawNumberInBar(Graphics graphics, Integer number, Rectangle barRectangle) {
        String numberText = number.toString();
        FontMetrics fontMetrics = graphics.getFontMetrics();

        int x = barRectangle.x + ((barRectangle.width - fontMetrics.stringWidth(numberText)) / 2);
        int y = barRectangle.y + fontMetrics.getHeight();

        graphics.setColor(Color.WHITE);
        graphics.drawString(numberText, x, y);
    }
}
