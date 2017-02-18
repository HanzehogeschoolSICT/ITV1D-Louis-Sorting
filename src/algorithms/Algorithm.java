package algorithms;

import models.DataSetModel;

public abstract class Algorithm {
    protected DataSetModel dataSet;

    public Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();
}
