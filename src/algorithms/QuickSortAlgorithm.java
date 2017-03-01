package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public class QuickSortAlgorithm extends Algorithm {
    /**
     * Construct the quick sort algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the quick sort algorithm.
     */
    public QuickSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    /**
     * Perform the next step of the quick sort algorithm.
     *
     * @return True if the data set has changed, false otherwise.
     */
    @Override
    public boolean nextStep() {
        synchronized (workerLock) {
            workerLock.notify();

            try {
                workerLock.wait();
            } catch (InterruptedException exception) {
                System.out.println("QuickSort has been destroyed.");
            }
        }

        return !dataSet.getIsSorted();
    }

    private class AlgorithmWorker implements Runnable {
        /**
         * Run the quick sort algorithm.
         */
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                quickSort(data, 0, data.size() - 1);

                synchronized (workerLock) {
                    workerLock.wait();
                    dataSet.setIsSorted();
                    workerLock.notify();
                }
            } catch (InterruptedException e) {
                System.out.println("QuickSort has been destroyed.");
            }
        }

        /**
         * Quick sort the given numbers.
         *
         * @param numbers Numbers to quick sort.
         * @param low     Lowest index to sort.
         * @param high    Highest index to sort.
         * @throws InterruptedException When the algorithm is being destroyed.
         */
        private void quickSort(LinkedList<Integer> numbers, int low, int high) throws InterruptedException {
            if (low < high) {
                int pivot = partition(numbers, low, high);
                quickSort(numbers, low, pivot - 1);
                quickSort(numbers, pivot + 1, high);
            }
        }

        /**
         * Partition the given numbers.
         *
         * @param numbers Numbers to partition.
         * @param low     Lowest index to sort.
         * @param high    Highest index to sort.
         * @return Index of the pivot.
         * @throws InterruptedException When the algorithm is being destroyed.
         */
        private int partition(LinkedList<Integer> numbers, int low, int high) throws InterruptedException {
            int pivot = numbers.get(high);
            int i = low - 1;
            for (int j = low; j < high; j++) {
                synchronized (workerLock) {
                    workerLock.wait();
                    dataSet.markComparedNumbers(j, high);

                    if (numbers.get(j) <= pivot) {
                        i++;
                        dataSet.swap(i, j);
                    }

                    workerLock.notify();
                }
            }

            synchronized (workerLock) {
                workerLock.wait();
                dataSet.markComparedNumbers(i + 1, high);

                dataSet.swap(i + 1, high);

                workerLock.notify();
            }

            return i + 1;
        }
    }
}
