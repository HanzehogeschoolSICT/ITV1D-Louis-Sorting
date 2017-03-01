package data;

import algorithms.Algorithm;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import models.DataSetModel;

public class DataManager {
    private static final Property<DataSetModel> dataSetProperty = new SimpleObjectProperty<>();

    private static Algorithm algorithm;

    public static Property<DataSetModel> getDataSetProperty() {
        return dataSetProperty;
    }

    public static void setAlgorithm(Algorithm algorithm) {
        if (DataManager.algorithm != null)
            DataManager.algorithm.destroy();

        DataManager.algorithm = algorithm;
    }

    public static Algorithm getAlgorithm() {
        return algorithm;
    }
}
