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
        super("QuickSort", dataSet);
    }

    /**
     * Start the quick sort using the given numbers.
     *
     * @param numbers Numbers to quick sort.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    @Override
    void startAlgorithm(LinkedList<Integer> numbers) throws InterruptedException {
        quickSort(numbers, 0, numbers.size() - 1);
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
            try (AutoLocker ignored = new AutoLocker()) {
                dataSet.markComparedNumbers(j, high);

                if (numbers.get(j) <= pivot) {
                    i++;
                    dataSet.swap(i, j);
                }
            }
        }

        try (AutoLocker ignored = new AutoLocker()) {
            dataSet.markComparedNumbers(i + 1, high);
            dataSet.swap(i + 1, high);
        }

        return i + 1;
    }
}
