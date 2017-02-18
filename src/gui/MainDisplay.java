package gui;

import models.DataBundleModel;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JFrame {
    public MainDisplay(DataBundleModel dataBundle) {
        setTitle("Sorting");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        DataSetDisplay dataSetDisplay = new DataSetDisplay(dataBundle);
        dataBundle.setDataSetDisplay(dataSetDisplay);
        dataSetDisplay.displayDataSet();
        add(dataSetDisplay);

        ControlsDisplay controlsDisplay = new ControlsDisplay(dataBundle);
        add(controlsDisplay);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception exception) {
            System.out.println("Failed to apply system look and feel.");
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
