package displays;

import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import models.DataSetModel;

public class MainDisplay {
    /**
     * Initialize the data for the main display.
     */
    public MainDisplay() {
        // Set the initial value for the current step to prevent null pointers.
        // Set it in the constructor to make sure it exists before any other controller accesses it.
        Property<Integer> currentStepProperty = DataManager.getCurrentStepProperty();
        currentStepProperty.setValue(0);
    }

    /**
     * Initialize the main display.
     */
    @FXML
    public void initialize() {
        DataSetModel initialDataSet = new DataSetModel(Settings.DATA_SET_SIZE);

        // Set the initial data set to display.
        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        dataSetProperty.setValue(initialDataSet);
    }
}
