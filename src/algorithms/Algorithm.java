package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public abstract class Algorithm {
    final DataSetModel dataSet;
    private final String algorithmName;
    private final Thread workerThread;
    private final Object workerLock = new Object();

    /**
     * Construct the algorithm using the specified data set.
     *
     * @param algorithmName Name of the current algorithm.
     * @param dataSet       Data set to use with the algorithm.
     */
    Algorithm(String algorithmName, DataSetModel dataSet) {
        this.algorithmName = algorithmName;
        this.dataSet = dataSet;

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    /**
     * Start the algorithm.
     *
     * @param data Data to use with the algorithm.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    abstract void startAlgorithm(LinkedList<Integer> data) throws InterruptedException;

    /**
     * Perform the next step of the algorithm.
     *
     * @return True if the data set has changed, false otherwise.
     */
    public boolean nextStep() {
        if (dataSet.getIsSorted())
            return false;

        synchronized (workerLock) {
            // Execute the next step of the algorithm.
            workerLock.notify();

            try {
                // Wait until the step has been completed before returning.
                workerLock.wait();
            } catch (InterruptedException exception) {
                System.out.println(String.format("%s has been destroyed.", algorithmName));
            }
        }

        return true;
    }

    /**
     * Destroy the algorithm.
     */
    public void destroy() {
        if (workerThread != null)
            workerThread.interrupt();
    }

    class NextStepWaiter implements AutoCloseable {
        /**
         * Wait for the worker lock to be released.
         * This happens when a request for the next step of the algorithm is being made.
         *
         * @throws InterruptedException When the algorithm is being destroyed.
         */
        NextStepWaiter() throws InterruptedException {
            synchronized (workerLock) {
                workerLock.wait();
            }
        }

        /**
         * Release the lock when the step has been completed.
         */
        @Override
        public void close() {
            synchronized (workerLock) {
                workerLock.notify();
            }
        }
    }

    private class AlgorithmWorker implements Runnable {
        /**
         * Run the algorithm itself in a separate thread.
         * The algorithm will be paused on every step using a lock.
         * This way the next step can be executed on request by releasing the lock.
         */
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                startAlgorithm(data);

                try (NextStepWaiter ignored = new NextStepWaiter()) {
                    dataSet.setIsSorted();
                }
            } catch (InterruptedException exception) {
                System.out.println(String.format("%s has been destroyed.", algorithmName));
            }
        }
    }
}
