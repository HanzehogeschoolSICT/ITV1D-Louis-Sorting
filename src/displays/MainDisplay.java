package displays;

import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import models.DataSetModel;

public class MainDisplay {
    @FXML
    public void initialize() {
        DataSetModel initialDataSet = new DataSetModel(Settings.DATA_SET_SIZE);

        // Set the initial data set to display.
        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        dataSetProperty.setValue(initialDataSet);
    }
}
