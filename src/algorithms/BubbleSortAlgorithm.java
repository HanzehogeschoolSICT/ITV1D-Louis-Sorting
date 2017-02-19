package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public class BubbleSortAlgorithm extends Algorithm {
    /**
     * Construct the bubble sort algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the bubble sort algorithm.
     */
    public BubbleSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    /**
     * Perform the next step of the bubble sort algorithm.
     *
     * @return True if the data set has changed, false otherwise.
     */
    @Override
    public boolean nextStep() {
        synchronized (workerLock) {
            workerLock.notify();
            return !dataSet.getIsSorted();
        }
    }

    private class AlgorithmWorker implements Runnable {
        /**
         * Run the bubble sort algorithm.
         */
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                bubbleSort(data);

                synchronized (workerLock) {
                    workerLock.wait();

                    dataSet.setIsSorted();
                }
            } catch (InterruptedException exception) {
                System.out.println("BubbleSort has been destroyed.");
            }
        }

        /**
         * Bubble sort the given numbers.
         *
         * @param numbers Numbers to bubble sort.
         * @throws InterruptedException When the algorithm is being destroyed.
         */
        private void bubbleSort(LinkedList<Integer> numbers) throws InterruptedException {
            boolean swapped;
            int size = numbers.size();

            do {
                swapped = false;
                for (int i = 1; i < size; i++) {
                    synchronized (workerLock) {
                        workerLock.wait();
                        dataSet.markComparedNumbers(i - 1, i);

                        if (numbers.get(i - 1) > numbers.get(i)) {
                            dataSet.swap(i - 1, i);
                            swapped = true;
                        }
                    }
                }
            } while (swapped);
        }
    }
}
