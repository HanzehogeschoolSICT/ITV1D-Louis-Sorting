package algorithms;

import models.DataSetModel;
import models.MergeSortModel;

import java.util.LinkedList;
import java.util.function.Consumer;

public class MergeSortAlgorithm extends Algorithm {
    private MergeSortModel mergeSort = new MergeSortModel();

    /**
     * Construct the merge sort algorithm using the specified data set.
     *
     * @param dataSet Data set to use with the merge sort algorithm.
     */
    public MergeSortAlgorithm(DataSetModel dataSet) {
        super("MergeSort", dataSet);
    }

    public MergeSortModel getMergeSort() {
        return mergeSort;
    }

    @Override
    void startAlgorithm(LinkedList<Integer> data) throws InterruptedException {
        merge_sort(data);
    }

    private LinkedList<Integer> merge_sort(LinkedList<Integer> numbers) throws InterruptedException {
        if (numbers.size() <= 1)
            return numbers;

        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (i < numbers.size() / 2) {
                left.add(numbers.get(i));
            } else {
                right.add(numbers.get(i));
            }
        }

        left = merge_sort(left);
        right = merge_sort(right);

        return merge(left, right);
    }

    private LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) throws InterruptedException {
        LinkedList<Integer> result = new LinkedList<>();
        mergeSort.clear();

        while (left.size() > 0 && right.size() > 0) {
            try (NextStepWaiter ignored = new NextStepWaiter()) {
                if (left.getFirst() <= right.getFirst()) {
                    add(result, left, mergeSort::addLeft);
                } else {
                    add(result, right, mergeSort::addRight);
                }
            }
        }

        while (left.size() > 0)
            try (NextStepWaiter ignored = new NextStepWaiter()) {
                add(result, left, mergeSort::addLeft);
            }

        while (right.size() > 0)
            try (NextStepWaiter ignored = new NextStepWaiter()) {
                add(result, right, mergeSort::addRight);
            }

        return result;
    }

    private void add(LinkedList<Integer> result, LinkedList<Integer> source, Consumer<Integer> data) {
        int number = source.getFirst();
        result.add(number);
        data.accept(number);

        // swap index 0 with indexOf(number)
    }
}