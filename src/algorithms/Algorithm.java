package algorithms;

import models.DataSetModel;

public abstract class Algorithm {
    final DataSetModel dataSet;
    final Object workerLock = new Object();
    Thread workerThread;

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    public abstract boolean nextStep();

    public void destroy() {
        if (workerThread != null)
            workerThread.interrupt();
    }
}
