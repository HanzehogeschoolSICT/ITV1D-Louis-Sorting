package algorithms;

import models.DataSetModel;

public interface Algorithm {
    boolean nextStep(DataSetModel dataSet);
}
