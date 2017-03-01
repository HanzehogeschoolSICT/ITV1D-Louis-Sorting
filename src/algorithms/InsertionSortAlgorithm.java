package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public class InsertionSortAlgorithm extends Algorithm {
    /**
     * Construct the insertion sort algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the insertion sort algorithm.
     */
    public InsertionSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    /**
     * Perform the next step of the insertion sort algorithm.
     *
     * @return True if the data set has changed, false otherwise.
     */
    @Override
    public boolean nextStep() {
        if (dataSet.getIsSorted())
            return false;

        synchronized (workerLock) {
            workerLock.notify();

            try {
                workerLock.wait();
            } catch (InterruptedException e) {
                System.out.println("InsertionSort has been destroyed.");
            }
        }

        return true;
    }

    private class AlgorithmWorker implements Runnable {
        /**
         * Run the insertion sort algorithm.
         */
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                insertionSort(data);

                synchronized (workerLock) {
                    workerLock.wait();
                    dataSet.setIsSorted();
                    workerLock.notify();
                }
            } catch (InterruptedException e) {
                System.out.println("InsertionSort has been destroyed.");
            }
        }

        /**
         * Insertion sort the given numbers.
         *
         * @param numbers Numbers to sort.
         * @throws InterruptedException When the algorithm is being destroyed.
         */
        private void insertionSort(LinkedList<Integer> numbers) throws InterruptedException {
            for (int i = 1; i < numbers.size(); i++) {
                int j = i;
                while (j > 0) {
                    synchronized (workerLock) {
                        workerLock.wait();
                        dataSet.markComparedNumbers(j, j - 1);

                        if (numbers.get(j - 1) > numbers.get(j)) {
                            dataSet.swap(j, j - 1);
                            j--;
                        } else {
                            workerLock.notify();
                            break;
                        }

                        workerLock.notify();
                    }
                }
            }
        }
    }
}
