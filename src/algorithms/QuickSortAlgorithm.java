package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public class QuickSortAlgorithm extends Algorithm {
    public QuickSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    @Override
    public boolean nextStep() {
        synchronized (workerLock) {
            workerLock.notify();
            return !dataSet.getIsSorted();
        }
    }

    private class AlgorithmWorker implements Runnable {
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                quickSort(data, 0, data.size() - 1);

                synchronized (workerLock) {
                    workerLock.wait();

                    dataSet.setIsSorted();
                }
            } catch (InterruptedException e) {
                System.out.println("QuickSort has been destroyed.");
            }
        }

        private void quickSort(LinkedList<Integer> numbers, int low, int high) throws InterruptedException {
            if (low < high) {
                int pivot = partition(numbers, low, high);
                quickSort(numbers, low, pivot - 1);
                quickSort(numbers, pivot + 1, high);
            }
        }

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
                }
            }

            synchronized (workerLock) {
                workerLock.wait();
                dataSet.markComparedNumbers(i + 1, high);

                dataSet.swap(i + 1, high);
            }

            return i + 1;
        }
    }
}
