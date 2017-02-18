import data.Settings;
import gui.MainDisplay;
import models.DataBundleModel;
import models.DataSetModel;

public class Bootstrap {
    public static void main(String[] args) {
        DataSetModel dataSet = new DataSetModel(Settings.DATA_SET_SIZE);

        DataBundleModel dataBundle = new DataBundleModel();
        dataBundle.setDataSetModel(dataSet);

        new MainDisplay(dataBundle);
    }
}
