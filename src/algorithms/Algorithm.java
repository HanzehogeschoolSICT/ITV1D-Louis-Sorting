package algorithms;

import models.DataSetModel;

public abstract class Algorithm {
    DataSetModel dataSet;

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();
}
