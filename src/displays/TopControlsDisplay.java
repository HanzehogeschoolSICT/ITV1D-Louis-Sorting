package displays;

import controllers.DataSetController;
import data.Settings;
import models.DataSetModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class TopControlsDisplay extends JPanel {
    private final DataSetController dataSetController;
    private JSpinner dataSetItems;

    /**
     * Construct the top controls display.
     *
     * @param dataSetController Data set controller to use.
     */
    TopControlsDisplay(DataSetController dataSetController) {
        this.dataSetController = dataSetController;
        setLayout(new FlowLayout(FlowLayout.CENTER, Settings.COMPONENT_SPACING, Settings.COMPONENT_SPACING));

        initializeDataSet();
    }

    /**
     * Initialize the data set controls.
     */
    private void initializeDataSet() {
        JLabel dataSetLabel = new JLabel("Data set:");
        add(dataSetLabel);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Settings.DATA_SET_SIZE,
                Settings.DATA_SET_MINIMUM_SIZE, Settings.DATA_SET_MAXIMUM_SIZE, Settings.DATA_SET_STEP_SIZE);
        dataSetItems = new JSpinner(spinnerNumberModel);
        add(dataSetItems);

        JLabel dataSetItemsLabel = new JLabel("items");
        add(dataSetItemsLabel);

        JButton newDataSetButton = new JButton("New Data Set");
        newDataSetButton.addActionListener(this::newDataSet);
        add(newDataSetButton);
    }

    /**
     * Handle a new data set request.
     *
     * @param actionEvent Action event for the request.
     */
    private void newDataSet(ActionEvent actionEvent) {
        int items = (int) dataSetItems.getValue();
        DataSetModel dataSet = new DataSetModel(items);
        dataSetController.changeDataSet(dataSet);
    }
}
