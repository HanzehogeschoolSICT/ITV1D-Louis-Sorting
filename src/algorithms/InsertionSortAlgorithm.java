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
        super("InsertionSort", dataSet);
    }

    /**
     * Insertion sort the given numbers.
     *
     * @param numbers Numbers to insertion sort.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    @Override
    void startAlgorithm(LinkedList<Integer> numbers) throws InterruptedException {
        for (int i = 1; i < numbers.size(); i++) {
            int j = i;
            while (j > 0) {
                try (AutoLocker ignored = new AutoLocker()) {
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
