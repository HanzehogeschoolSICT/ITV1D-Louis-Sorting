package displays;

import controllers.DataSetController;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JFrame {
    /**
     * Construct the main display.
     *
     * @param dataSetController Data set controller to use.
     */
    public MainDisplay(DataSetController dataSetController) {
        setTitle("Sorting");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        initializeViews(dataSetController);
        setSystemLookAndFeel();

        pack();
        // Show frame in the center of the screen.
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initialize the views.
     *
     * @param dataSetController Data set controller to use.
     */
    private void initializeViews(DataSetController dataSetController) {
        TopControlsDisplay topControlsDisplay = new TopControlsDisplay(dataSetController);
        add(topControlsDisplay);

        DataSetDisplay dataSetDisplay = new DataSetDisplay(dataSetController);
        dataSetController.setUpdateDataSetDisplay(dataSetDisplay::displayDataSet);
        add(dataSetDisplay);

        BottomControlsDisplay bottomControlsDisplay = new BottomControlsDisplay(dataSetController);
        add(bottomControlsDisplay);
    }

    /**
     * Set the system look and feel (instead of default Java look and feel).
     */
    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception exception) {
            System.out.println("Failed to apply system look and feel.");
        }
    }
}
