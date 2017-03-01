package data;

import algorithms.Algorithm;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import models.DataSetModel;

import java.util.Timer;

public class DataManager {
    private static final Property<DataSetModel> dataSetProperty =
            new SimpleObjectProperty<>(new DataSetModel(Settings.DATA_SET_SIZE));
    private static final Property<Integer> currentStepProperty = new SimpleObjectProperty<>(0);

    private static final Timer timer = new Timer();

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
     * Get the timer.
     *
     * @return Timer.
     */
    public static Timer getTimer() {
        return timer;
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
