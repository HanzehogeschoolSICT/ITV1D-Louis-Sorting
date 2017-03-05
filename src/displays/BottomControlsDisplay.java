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
import javafx.scene.control.*;
import models.DataSetModel;

import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 * Controller for the bottom controls.
 */
public class BottomControlsDisplay {
    private final HashMap<String, Function<DataSetModel, Algorithm>> algorithmHashMap = new HashMap<>();

    @FXML
    private ChoiceBox<String> algorithmsChoiceBox;

    @FXML
    private Button nextStepButton;

    @FXML
    private Spinner<Integer> autoNextStepSpinner;
    @FXML
    private Button autoNextStepButton;

    private TimerTask autoNextStepTimerTask = null;

    /**
     * Initialize the data for the bottom controls display.
     */
    public BottomControlsDisplay() {
        algorithmHashMap.put("BubbleSort", BubbleSortAlgorithm::new);
        algorithmHashMap.put("InsertionSort", InsertionSortAlgorithm::new);
        algorithmHashMap.put("QuickSort", QuickSortAlgorithm::new);

        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        dataSetProperty.addListener(((observable, oldValue, newValue) -> handleNewDataSet(newValue)));
    }

    /**
     * Initialize the bottom controls display.
     */
    @FXML
    public void initialize() {
        Set<String> algorithmNames = algorithmHashMap.keySet();
        ObservableList<String> algorithmNameList = FXCollections.observableArrayList(algorithmNames);
        algorithmsChoiceBox.setItems(algorithmNameList);

        // Make sure an algorithm is selected.
        SelectionModel<String> selectionModel = algorithmsChoiceBox.getSelectionModel();
        selectionModel.selectFirst();

        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(Settings.AUTO_NEXT_MINIMUM_MS,
                        Settings.AUTO_NEXT_MAXIMUM_MS, Settings.AUTO_NEXT_MS, Settings.AUTO_NEXT_STEP_SIZE);

        autoNextStepSpinner.setValueFactory(spinnerValueFactory);
    }

    /**
     * Handle an algorithms combo box action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onAlgorithmsChoiceBoxAction(ActionEvent actionEvent) {
        DataSetModel dataSet = DataManager.getDataSet();
        applyAlgorithm(dataSet);
    }

    /**
     * Handle a next step button action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onNextStepButtonAction(ActionEvent actionEvent) {
        executeNextStep();
    }

    /**
     * Handle an auto next step button action.
     *
     * @param actionEvent Event for the action.
     */
    @FXML
    private void onAutoNextStepButtonAction(ActionEvent actionEvent) {
        if (autoNextStepTimerTask == null) {
            autoNextStepSpinner.setDisable(true);
            autoNextStepButton.setText("Stop Auto Next Step");

            int interval = autoNextStepSpinner.getValue();
            autoExecuteNextStep(interval);
        } else {
            autoNextStepSpinner.setDisable(false);
            autoNextStepButton.setText("Start Auto Next Step");

            autoNextStepTimerTask.cancel();
            autoNextStepTimerTask = null;
        }
    }

    /**
     * Handle a newly created data set.
     *
     * @param dataSet Newly created data set.
     */
    private void handleNewDataSet(DataSetModel dataSet) {
        nextStepButton.setDisable(false);
        applyAlgorithm(dataSet);
    }

    /**
     * Apply the currently selected algorithm to the given data set.
     *
     * @param dataSet Data set to use with the algorithm.
     */
    private void applyAlgorithm(DataSetModel dataSet) {
        String algorithmName = algorithmsChoiceBox.getValue();
        Function<DataSetModel, Algorithm> algorithmFactory = algorithmHashMap.get(algorithmName);
        Algorithm algorithm = algorithmFactory.apply(dataSet);

        DataManager.setAlgorithm(algorithm);
    }

    /**
     * Execute the next step of the algorithm at the given interval.
     *
     * @param interval Interval between steps.
     */
    private void autoExecuteNextStep(int interval) {
        autoNextStepTimerTask = new TimerTask() {
            @Override
            public void run() {
                executeNextStep();
            }
        };

        Timer timer = DataManager.getTimer();
        timer.schedule(autoNextStepTimerTask, 0, interval);
    }

    /**
     * Execute the next step of the algorithm.
     */
    private void executeNextStep() {
        Algorithm algorithm = DataManager.getAlgorithm();

        if (algorithm == null || !algorithm.nextStep())
            return;

        DataSetModel dataSet = DataManager.getDataSet();

        if (dataSet.getIsSorted())
            nextStepButton.setDisable(true);

        DataManager.increaseCurrentStep();
    }
}
