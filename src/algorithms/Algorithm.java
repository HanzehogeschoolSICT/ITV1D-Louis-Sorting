package algorithms;

import models.DataSetModel;

public abstract class Algorithm {
    Thread workerThread;
    boolean isSorted = false;
    final DataSetModel dataSet;
    final Object workerLock = new Object();

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();

    public void destroy() {
        if (workerThread != null)
            workerThread.interrupt();
    }
}
