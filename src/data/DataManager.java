package data;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import models.DataSetModel;

public class DataManager {
    private static final Property<DataSetModel> dataSetProperty = new SimpleObjectProperty<>();

    public static Property<DataSetModel> getDataSetProperty() {
        return dataSetProperty;
    }
}
