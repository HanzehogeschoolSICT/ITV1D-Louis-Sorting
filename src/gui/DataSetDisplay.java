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
        DataSetModel dataSet = dataBundle.getDataSetModel();

        int heightPerBar = getHeight() / dataSet.getHighestNumber();
        int widthPerBar = getWidth() / dataSet.getHighestNumber();

        int positionX = 0;
        for (int number: dataSet) {
            int barHeight = heightPerBar * number;
            g.fillRect(positionX, getHeight() - barHeight, widthPerBar, barHeight);
            positionX += widthPerBar;
        }
    }
}
