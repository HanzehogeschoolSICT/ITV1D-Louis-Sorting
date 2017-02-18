package gui;

import data.Settings;
import models.DataBundleModel;
import models.DataSetModel;

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

        int currentX = 0;
        for (Integer number : dataSet)
            currentX = drawBar(graphics, number, currentX, heightPerNumber, widthPerBar);
    }

    private int drawBar(Graphics graphics, Integer number, int currentX, int numberHeight, int barWidth) {
        int barHeight = numberHeight * number;
        int y = getHeight() - barHeight;
        Rectangle barRectangle = new Rectangle(currentX, y, barWidth, barHeight);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(barRectangle.x, barRectangle.y, barRectangle.width, barRectangle.height);

        drawNumberInBar(graphics, number, barRectangle);

        return currentX + barWidth;
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
