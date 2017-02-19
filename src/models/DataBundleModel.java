package models;

import displays.DataSetDisplay;

public class DataBundleModel {
    private DataSetModel dataSetModel;
    private DataSetDisplay dataSetDisplay;

    public DataSetModel getDataSetModel() {
        return dataSetModel;
    }

    public void setDataSetModel(DataSetModel dataSetModel) {
        this.dataSetModel = dataSetModel;
    }

    public DataSetDisplay getDataSetDisplay() {
        return dataSetDisplay;
    }

    public void setDataSetDisplay(DataSetDisplay dataSetDisplay) {
        this.dataSetDisplay = dataSetDisplay;
    }
}
