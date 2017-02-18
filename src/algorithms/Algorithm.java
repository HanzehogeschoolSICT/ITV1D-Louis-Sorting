package algorithms;

import models.DataSetModel;

import java.util.LinkedList;

public abstract class Algorithm {
    int maxIndex;
    DataSetModel dataSet;
    LinkedList<Integer> data;

    Algorithm(DataSetModel dataSet) {
        this.dataSet = dataSet;
        data = dataSet.getData();
        maxIndex = data.size() - 1;
    }

    public abstract boolean nextStep();
}
