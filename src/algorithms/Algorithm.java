package algorithms;

import data.Log;
import models.DataSetModel;

import java.util.LinkedList;

/**
 * Base for algorithm implementations.
 * Contains the logic for executing and pausing algorithms,
 * while the implementations contain the algorithms themselves.
 */
public abstract class Algorithm {
    final DataSetModel dataSet;
    private final String algorithmName;
    private final Thread workerThread;
    private final Object workerLock = new Object();

    /**
     * Construct the algorithm using the specified data set.
     * The algorithm will be started, but won't alter the data set until {@link #nextStep()} is called.
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
     * This simply passes the input data to the algorithm,
     * but doesn't alter the data set until {@link #nextStep()} is called.
     *
     * @param data Data to use with the algorithm.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    abstract void startAlgorithm(LinkedList<Integer> data) throws InterruptedException;

    /**
     * Perform the next step of the algorithm, unless the data set is already sorted.
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
                Log.info("%s has been destroyed", algorithmName);
            }
        }

        return true;
    }

    /**
     * Check if the current algorithm equals the given name.
     *
     * @param algorithmName Algorithm name to check.
     * @return True if the current algorithm equals the given name, false otherwise.
     */
    public boolean isAlgorithm(String algorithmName) {
        return this.algorithmName.equals(algorithmName);
    }

    /**
     * Destroy the algorithm.
     */
    public void destroy() {
        if (workerThread != null)
            workerThread.interrupt();
    }

    /**
     * Automatically wait for and notify the worker lock using an {@link AutoCloseable} to perform a step,
     * in order to prevent boiler plate and nested code and to make sure {@link #notify()} is always called.
     */
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
         * This results in the calling thread resuming its work and returning the result.
         */
        @Override
        public void close() {
            synchronized (workerLock) {
                workerLock.notify();
            }
        }
    }

    /**
     * Worker to execute the algorithm implementation on a separate thread.
     * This thread is being paused after every step to preserve the current state of the algorithm.
     */
    private class AlgorithmWorker implements Runnable {
        /**
         * Run the algorithm itself on a separate thread.
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
                Log.info("%s has been destroyed", algorithmName);
            }
        }
    }
}
