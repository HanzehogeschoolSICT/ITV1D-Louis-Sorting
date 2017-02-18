package algorithms;

import models.DataSetModel;

public interface Algorithm {
    void setDataSet(DataSetModel dataSet);
    boolean nextStep();
}
