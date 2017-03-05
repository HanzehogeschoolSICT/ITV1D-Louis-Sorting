package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

/**
 * Implementation of the insertion sort algorithm.
 */
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
     * This works in the following way:
     * - Loop from the second number to the last number.
     * - For each number in the loop, compare the current number to the previous number.
     * - If the previous number is greater than the current number, swap the numbers and continue comparing.
     * - Else go on to the next number.
     *
     * @param numbers Numbers to insertion sort.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    @Override
    void startAlgorithm(LinkedList<Integer> numbers) throws InterruptedException {
        for (int i = 1; i < numbers.size(); i++) {
            int j = i;
            while (j > 0) {
                try (NextStepWaiter ignored = new NextStepWaiter()) {
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
