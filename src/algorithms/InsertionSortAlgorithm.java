package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public class InsertionSortAlgorithm extends Algorithm {
    public InsertionSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);

        AlgorithmWorker algorithmWorker = new AlgorithmWorker();
        workerThread = new Thread(algorithmWorker);
        workerThread.start();
    }

    @Override
    public boolean nextStep() {
        synchronized (workerLock) {
            workerLock.notify();
            return !isSorted;
        }
    }

    private class AlgorithmWorker implements Runnable {
        @Override
        public void run() {
            LinkedList<Integer> data = dataSet.getData();

            try {
                insertionSort(data);

                synchronized (workerLock) {
                    workerLock.wait();
                    dataSet.setIsSorted();
                    isSorted = true;
                }
            } catch (InterruptedException e) {
                System.out.println("InsertionSort has been destroyed.");
            }
        }

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
                            break;
                        }
                    }
                }
            }
        }
    }
}
