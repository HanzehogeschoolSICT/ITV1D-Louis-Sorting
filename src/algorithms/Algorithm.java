package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public abstract class Algorithm {
    Thread workerThread;
    boolean isSorted = false;
    final DataSetModel dataSet;
    final Object workerLock = new Object();

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();

    public abstract void destroy();
}
