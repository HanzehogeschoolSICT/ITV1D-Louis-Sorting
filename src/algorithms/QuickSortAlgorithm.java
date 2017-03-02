package algorithms;

import models.DataSetModel;
import models.PivotModel;

import java.util.LinkedList;

public class QuickSortAlgorithm extends Algorithm {
    private PivotModel currentPivot;

    /**
     * Construct the quick sort algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the quick sort algorithm.
     */
    public QuickSortAlgorithm(DataSetModel dataSet) {
        super("QuickSort", dataSet);
    }

    /**
     * Get the current pivot for the algorithm.
     *
     * @return Current pivot for the algorithm.
     */
    public PivotModel getCurrentPivot() {
        return currentPivot;
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
            try (NextStepWaiter ignored = new NextStepWaiter()) {
                // The pivot doesn't change within this loop, but is set
                // within the NextStepWaiter to prevent a race condition.
                currentPivot = new PivotModel(low, high, pivot);
                dataSet.markComparedNumber(j);

                if (numbers.get(j) <= pivot)
                    dataSet.swap(++i, j);
            }
        }

        try (NextStepWaiter ignored = new NextStepWaiter()) {
            dataSet.markComparedNumber(high);
            dataSet.swap(i + 1, high);
        }

        return i + 1;
    }
}
