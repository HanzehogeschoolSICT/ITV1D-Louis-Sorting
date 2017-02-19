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

        initializeViews(dataBundle);
        setSystemLookAndFeel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeViews(DataBundleModel dataBundle) {
        TopControlsDisplay topControlsDisplay = new TopControlsDisplay(dataBundle);
        add(topControlsDisplay);

        DataSetDisplay dataSetDisplay = new DataSetDisplay(dataBundle);
        dataBundle.setDataSetDisplay(dataSetDisplay);
        dataSetDisplay.displayDataSet();
        add(dataSetDisplay);

        BottomControlsDisplay bottomControlsDisplay = new BottomControlsDisplay(dataBundle);
        add(bottomControlsDisplay);
    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception exception) {
            System.out.println("Failed to apply system look and feel.");
        }
    }
}
