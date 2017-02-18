package gui;

import data.Settings;
import models.DataBundleModel;
import models.DataSetModel;
import models.DrawBarDataModel;

import javax.swing.*;
import java.awt.*;

public class DataSetDisplay extends JPanel {
    private final DataBundleModel dataBundle;

    DataSetDisplay(DataBundleModel dataBundle) {
        this.dataBundle = dataBundle;
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
        DataSetModel dataSet = dataBundle.getDataSetModel();

        int heightPerNumber = getHeight() / dataSet.getHighestNumber();
        int widthPerBar = getWidth() / dataSet.getHighestNumber();

        DrawBarDataModel drawBarData = new DrawBarDataModel(heightPerNumber, widthPerBar);
        for (Integer number : dataSet)
            drawBar(graphics, number, drawBarData);
    }

    private void drawBar(Graphics graphics, Integer number, DrawBarDataModel drawBarData) {
        int barHeight = drawBarData.getHeightPerNumber() * number;
        int x = drawBarData.getCurrentX();
        int y = getHeight() - barHeight;

        Rectangle barRectangle = new Rectangle(x, y, drawBarData.getWidthPerBar(), barHeight);

        graphics.setColor(Color.BLACK);
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
