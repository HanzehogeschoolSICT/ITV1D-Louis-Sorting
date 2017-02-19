package algorithms;

import models.DataSetModel;

public abstract class Algorithm {
    final DataSetModel dataSet;
    final Object workerLock = new Object();
    Thread workerThread;

    /**
     * Construct the algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the algorithm.
     */
    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
    }

    /**
     * Perform the next step of the algorithm.
     *
     * @return True if the data set has changed, false otherwise.
     */
    public abstract boolean nextStep();

    /**
     * Destroy the algorithm.
     */
    public void destroy() {
        if (workerThread != null)
            workerThread.interrupt();
    }
}
