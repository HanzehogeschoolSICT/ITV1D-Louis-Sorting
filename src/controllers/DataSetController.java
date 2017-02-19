package controllers;

import algorithms.Algorithm;
import models.DataSetModel;

import java.util.function.Function;

public class DataSetController {
    private Algorithm algorithm;
    private Function<DataSetModel, Algorithm> algorithmFactory;

    private DataSetModel dataSet;
    
    public void changeAlgorithm(Function<DataSetModel, Algorithm> algorithmFactory) {
        if (this.algorithm != null)
            this.algorithm.destroy();

        this.algorithm = algorithmFactory.apply(dataSet);
        this.algorithmFactory = algorithmFactory;
    }

    public void nextAlgorithmStep() {
        algorithm.nextStep();
        updateDataSetDisplay();
    }

    public DataSetModel getDataSet() {
        return dataSet;
    }

    public void changeDataSet(DataSetModel dataSet) {
        this.dataSet = dataSet;

        if (algorithmFactory != null)
            changeAlgorithm(algorithmFactory);

        updateDataSetDisplay();
    }

    private Runnable updateDataSetDisplay;

    public void setUpdateDataSetDisplay(Runnable updateDataSetDisplay) {
        this.updateDataSetDisplay = updateDataSetDisplay;
    }

    private void updateDataSetDisplay() {
        if (updateDataSetDisplay != null)
            updateDataSetDisplay.run();
    }
}
