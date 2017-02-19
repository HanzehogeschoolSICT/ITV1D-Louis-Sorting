package controllers;

import algorithms.Algorithm;
import models.DataSetModel;

import java.util.function.Function;

public class DataSetController {
    private Algorithm algorithm;
    private Function<DataSetModel, Algorithm> algorithmFactory;

    private DataSetModel dataSet;
    private Runnable updateDataSetDisplay;

    /**
     * Change the algorithm that's currently in use.
     *
     * @param algorithmFactory Algorithm factory to use.
     */
    public void changeAlgorithm(Function<DataSetModel, Algorithm> algorithmFactory) {
        if (this.algorithm != null)
            this.algorithm.destroy();

        this.algorithm = algorithmFactory.apply(dataSet);
        this.algorithmFactory = algorithmFactory;
    }

    /**
     * Perform and display the next step of the current algorithm.
     */
    public void nextAlgorithmStep() {
        if (algorithm.nextStep())
            updateDataSetDisplay();
    }

    /**
     * Get the current data set.
     *
     * @return Current data set.
     */
    public DataSetModel getDataSet() {
        return dataSet;
    }

    /**
     * Change the data set that's currently in use.
     *
     * @param dataSet New data set to use.
     */
    public void changeDataSet(DataSetModel dataSet) {
        this.dataSet = dataSet;

        if (algorithmFactory != null)
            changeAlgorithm(algorithmFactory);

        updateDataSetDisplay();
    }

    /**
     * Set the method which updates the data set display.
     *
     * @param updateDataSetDisplay Method which updates the data set display.
     */
    public void setUpdateDataSetDisplay(Runnable updateDataSetDisplay) {
        this.updateDataSetDisplay = updateDataSetDisplay;
    }

    /**
     * Update the data set display.
     */
    private void updateDataSetDisplay() {
        if (updateDataSetDisplay != null)
            updateDataSetDisplay.run();
    }
}
