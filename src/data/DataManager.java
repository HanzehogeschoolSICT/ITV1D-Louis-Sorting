package data;

import algorithms.Algorithm;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import models.DataSetModel;

public class DataManager {
    private static final Property<DataSetModel> dataSetProperty = new SimpleObjectProperty<>();
    private static final Property<Integer> currentStepProperty = new SimpleObjectProperty<>();

    private static Algorithm algorithm;

    /**
     * Get the data set property.
     *
     * @return Data set property.
     */
    public static Property<DataSetModel> getDataSetProperty() {
        return dataSetProperty;
    }

    /**
     * Get the current step property.
     *
     * @return Current step property.
     */
    public static Property<Integer> getCurrentStepProperty() {
        return currentStepProperty;
    }

    /**
     * Get the current algorithm.
     *
     * @return Current algorithm.
     */
    public static Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Set the current algorithm.
     *
     * @param algorithm New algorithm.
     */
    public static void setAlgorithm(Algorithm algorithm) {
        if (DataManager.algorithm != null)
            DataManager.algorithm.destroy();

        DataManager.algorithm = algorithm;
    }
}
