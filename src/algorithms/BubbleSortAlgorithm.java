package algorithms;

import models.DataSetModel;

import java.util.Collections;
import java.util.LinkedList;

public class BubbleSortAlgorithm extends Algorithm {
    private int currentIndex;
    private boolean hasSwapped = false;

    public BubbleSortAlgorithm(DataSetModel dataSet) {
        super(dataSet);
    }

    @Override
    public boolean nextStep() {
        if (currentIndex + 1 > maxIndex) {
            if (hasSwapped) {
                currentIndex = 0;
                hasSwapped = false;
            } else {
                dataSet.setIsSorted();
                return false;
            }
        }

        dataSet.setLatestChanges(data.get(currentIndex), data.get(currentIndex + 1));

        if (data.get(currentIndex) > data.get(currentIndex + 1)) {
            Collections.swap(data, currentIndex, currentIndex + 1);
            hasSwapped = true;
        }

        currentIndex++;
        return true;
    }
}
