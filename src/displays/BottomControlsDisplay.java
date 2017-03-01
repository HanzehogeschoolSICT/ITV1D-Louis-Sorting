package displays;

import algorithms.Algorithm;
import algorithms.BubbleSortAlgorithm;
import algorithms.InsertionSortAlgorithm;
import algorithms.QuickSortAlgorithm;
import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import models.DataSetModel;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

public class BottomControlsDisplay {
    @FXML
    private ComboBox<String> algorithmsComboBox;
    @FXML
    private Spinner<Integer> autoNextStepSpinner;

    private final HashMap<String, Function<DataSetModel, Algorithm>> algorithmHashMap = new HashMap<>();

    public BottomControlsDisplay () {
        algorithmHashMap.put("BubbleSort", BubbleSortAlgorithm::new);
        algorithmHashMap.put("InsertionSort", InsertionSortAlgorithm::new);
        algorithmHashMap.put("QuickSort", QuickSortAlgorithm::new);

        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        dataSetProperty.addListener(((observable, oldValue, newValue) -> applyAlgorithm(newValue)));
    }

    @FXML
    public void initialize() {
        Set<String> algorithmNames = algorithmHashMap.keySet();
        ObservableList<String> algorithmNameList = FXCollections.observableArrayList(algorithmNames);
        algorithmsComboBox.setItems(algorithmNameList);

        // Make sure an algorithm is selected.
        SelectionModel<String> selectionModel = algorithmsComboBox.getSelectionModel();
        selectionModel.selectFirst();

        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Settings.AUTO_NEXT_MINIMUM_MS,
                        Settings.AUTO_NEXT_MAXIMUM_MS, Settings.AUTO_NEXT_MS, Settings.AUTO_NEXT_STEP_SIZE);

        autoNextStepSpinner.setValueFactory(spinnerValueFactory);
    }

    @FXML
    private void onAlgorithmsComboBoxAction(ActionEvent actionEvent) {
        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        DataSetModel dataSet = dataSetProperty.getValue();

        applyAlgorithm(dataSet);
    }

    @FXML
    private void onNextStepButtonAction (ActionEvent actionEvent) {

    }

    @FXML
    private void onAutoNextStepButtonAction(ActionEvent actionEvent) {

    }

    private void applyAlgorithm(DataSetModel dataSet) {
        String algorithmName = algorithmsComboBox.getValue();
        Function<DataSetModel, Algorithm> algorithmFactory = algorithmHashMap.get(algorithmName);
        Algorithm algorithm = algorithmFactory.apply(dataSet);

        DataManager.setAlgorithm(algorithm);
    }
}
