package gui;

import data.Settings;
import models.DataBundleModel;
import models.DataSetModel;

import javax.swing.*;
import java.awt.*;

public class DataSetDisplay extends JPanel {
    private DataBundleModel dataBundle;

    public DataSetDisplay(DataBundleModel dataBundle) {
        this.dataBundle = dataBundle;
    }

    public Dimension getPreferredSize() {
        return new Dimension(Settings.DATA_SET_DISPLAY_WIDTH, Settings.DATA_SET_DISPLAY_HEIGHT);
    }

    public void displayDataSet() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        DataSetModel dataSet = dataBundle.getDataSetModel();

        int heightPerBar = getHeight() / dataSet.getHighestNumber();
        int widthPerBar = getWidth() / dataSet.getHighestNumber();

        int positionX = 0;
        for (Integer number: dataSet)
            positionX = drawBar(g, number, positionX, heightPerBar, widthPerBar);
    }

    private int drawBar(Graphics graphics, Integer number, int initialX, int barHeight, int barWidth) {
        barHeight = barHeight * number;
        int positionY = getHeight() - barHeight;
        Rectangle barRectangle = new Rectangle(initialX, positionY, barWidth, barHeight);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(barRectangle.x, barRectangle.y, barRectangle.width, barRectangle.height);

        drawNumberInBar(graphics, number, barRectangle);

        return initialX + barWidth;
    }

    private void drawNumberInBar(Graphics graphics, Integer number, Rectangle barRectangle) {
        String numberText = number.toString();
        FontMetrics fontMetrics = graphics.getFontMetrics();

        int x = ((barRectangle.width - fontMetrics.stringWidth(numberText)) / 2) + barRectangle.x;
        int y = barRectangle.y + fontMetrics.getHeight();

        graphics.setColor(Color.WHITE);
        graphics.drawString(numberText, x, y);
    }
}
