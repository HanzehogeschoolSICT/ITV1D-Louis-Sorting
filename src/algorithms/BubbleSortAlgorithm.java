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
        super("BubbleSort", dataSet);
    }

    /**
     * Bubble sort the given numbers.
     *
     * @param numbers Numbers to bubble sort.
     * @throws InterruptedException When the algorithm is being destroyed.
     */
    @Override
    void startAlgorithm(LinkedList<Integer> numbers) throws InterruptedException {
        boolean swapped;
        int size = numbers.size();

        do {
            swapped = false;
            for (int i = 1; i < size; i++) {
                try (NextStepWaiter ignored = new NextStepWaiter()) {
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
