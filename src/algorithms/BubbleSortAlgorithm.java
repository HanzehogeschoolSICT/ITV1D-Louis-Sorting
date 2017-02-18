package algorithms;

import models.DataSetModel;

import java.util.Collections;
import java.util.LinkedList;

public class BubbleSortAlgorithm implements Algorithm {
    private DataSetModel dataSet;
    private int currentIndex;
    private boolean hasSwapped = false;

    @Override
    public void setDataSet(DataSetModel dataSet) {
        this.dataSet = dataSet;
        currentIndex = 0;
        hasSwapped = false;
    }

    @Override
    public boolean nextStep() {
        if (currentIndex + 1 > dataSet.getMaxIndex()) {
            if (hasSwapped) {
                currentIndex = 0;
                hasSwapped = false;
            } else {
                return false;
            }
        }

        LinkedList<Integer> data = dataSet.getData();
        dataSet.setLatestChanges(data.get(currentIndex), data.get(currentIndex + 1));

        if (data.get(currentIndex) > data.get(currentIndex + 1)) {
            Collections.swap(data, currentIndex, currentIndex + 1);
            hasSwapped = true;
        }

        currentIndex++;
        return true;
    }
}
