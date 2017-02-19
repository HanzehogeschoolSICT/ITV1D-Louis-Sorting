package displays;

import controllers.DataSetController;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JFrame {
    public MainDisplay(DataSetController dataSetController) {
        setTitle("Sorting");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        initializeViews(dataSetController);
        setSystemLookAndFeel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeViews(DataSetController dataSetController) {
        TopControlsDisplay topControlsDisplay = new TopControlsDisplay(dataSetController);
        add(topControlsDisplay);

        DataSetDisplay dataSetDisplay = new DataSetDisplay(dataSetController);
        dataSetController.setUpdateDataSetDisplay(dataSetDisplay::displayDataSet);
        add(dataSetDisplay);

        BottomControlsDisplay bottomControlsDisplay = new BottomControlsDisplay(dataSetController);
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
