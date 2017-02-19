package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public abstract class Algorithm {
    boolean isSorted = false;
    final DataSetModel dataSet;
    final Object workerLock = new Object();

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();
}
