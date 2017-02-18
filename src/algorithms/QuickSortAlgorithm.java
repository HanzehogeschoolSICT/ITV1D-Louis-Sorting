package algorithms;

import models.DataSetModel;

import java.util.Collections;
import java.util.LinkedList;

public class QuickSortAlgorithm extends Algorithm {
    public QuickSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);
    }

    @Override
    public boolean nextStep() {
        quickSort(data, 0, data.size() -1);
        return true;
    }

    private void quickSort(LinkedList<Integer> numbers, int low, int high) {
        if (low < high) {
            int p = partition(numbers, low, high);
            quickSort(numbers, low, p - 1);
            quickSort(numbers, p + 1, high);
        }
    }

    private int partition(LinkedList<Integer> numbers, int low, int high) {
        int pivot = numbers.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++)  {
            if (numbers.get(j) <= pivot) {
                i++;
                Collections.swap(numbers, i, j);
            }
        }
        Collections.swap(numbers, i+ 1, high);
        return i + 1;
    }
}
