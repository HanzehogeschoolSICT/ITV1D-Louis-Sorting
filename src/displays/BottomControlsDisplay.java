package displays;

import algorithms.Algorithm;
import algorithms.BubbleSortAlgorithm;
import algorithms.InsertionSortAlgorithm;
import algorithms.QuickSortAlgorithm;
import controllers.DataSetController;
import data.Settings;
import models.DataSetModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

class BottomControlsDisplay extends JPanel {
    private final DataSetController dataSetController;
    private final HashMap<String, Function<DataSetModel, Algorithm>> algorithmHashMap = new HashMap<>();
    private Timer autoNextStepTimer;
    private JButton autoNextStepButton;
    private JSpinner autoNextStepSpinner;

    /**
     * Construct the bottom controls display.
     *
     * @param dataSetController Data set controller to use.
     */
    BottomControlsDisplay(DataSetController dataSetController) {
        this.dataSetController = dataSetController;
        setLayout(new FlowLayout(FlowLayout.CENTER, Settings.COMPONENT_SPACING, Settings.COMPONENT_SPACING));

        initializeAlgorithms();
        initializeNextStep();
        initializeAutoNextStep();
    }

    /**
     * Initialize the algorithm selector.
     */
    private void initializeAlgorithms() {
        algorithmHashMap.put("BubbleSort", BubbleSortAlgorithm::new);
        algorithmHashMap.put("InsertionSort", InsertionSortAlgorithm::new);
        algorithmHashMap.put("QuickSort", QuickSortAlgorithm::new);

        Set<String> algorithmNames = algorithmHashMap.keySet();
        String[] algorithmNamesArray = algorithmNames.toArray(new String[algorithmNames.size()]);
        JComboBox algorithmComboBox = new JComboBox<>(algorithmNamesArray);
        algorithmComboBox.addActionListener(this::algorithmSelected);

        // Trigger algorithmSelected to make sure an algorithm exists.
        algorithmComboBox.setSelectedIndex(0);

        add(algorithmComboBox);
    }

    /**
     * Handle an algorithm change.
     *
     * @param actionEvent Action event for the change.
     */
    private void algorithmSelected(ActionEvent actionEvent) {
        JComboBox algorithmComboBox = (JComboBox) actionEvent.getSource();
        String algorithmName = (String) algorithmComboBox.getSelectedItem();

        try {
            Function<DataSetModel, Algorithm> algorithmFactory = algorithmHashMap.get(algorithmName);
            dataSetController.changeAlgorithm(algorithmFactory);
        } catch (Exception exception) {
            System.out.println("Failed to create algorithm.");
        }
    }

    /**
     * Initialize the next step button.
     */
    private void initializeNextStep() {
        JButton nextStepButton = new JButton("Next Step");
        nextStepButton.addActionListener(this::nextStep);
        add(nextStepButton);
    }

    /**
     * Handle a next step request.
     *
     * @param actionEvent Action event for the change.
     */
    private void nextStep(ActionEvent actionEvent) {
        dataSetController.nextAlgorithmStep();
    }

    /**
     * Initialize the auto next step controls.
     */
    private void initializeAutoNextStep() {
        autoNextStepButton = new JButton("Start Auto Next Step");
        autoNextStepButton.addActionListener(this::autoNextStepToggle);
        add(autoNextStepButton);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(Settings.AUTO_NEXT_MS,
                Settings.AUTO_NEXT_MINIMUM_MS, Settings.AUTO_NEXT_MAXIMUM_MS, Settings.AUTO_NEXT_STEP_SIZE);
        autoNextStepSpinner = new JSpinner(spinnerNumberModel);
        add(autoNextStepSpinner);

        JLabel autoNextStepLabel = new JLabel("ms");
        add(autoNextStepLabel);

        autoNextStepTimer = new Timer(0, this::nextStep);
    }

    /**
     * Handle the auto next step toggle request.
     *
     * @param actionEvent Action event for the request.
     */
    private void autoNextStepToggle(ActionEvent actionEvent) {
        if (autoNextStepTimer.isRunning()) {
            autoNextStepTimer.stop();

            autoNextStepSpinner.setEnabled(true);
            autoNextStepButton.setText("Start Auto Next Step");
        } else {
            int autoNextStepMs = (int) autoNextStepSpinner.getValue();
            autoNextStepTimer.setDelay(autoNextStepMs);
            autoNextStepTimer.start();

            autoNextStepSpinner.setEnabled(false);
            autoNextStepButton.setText("Stop Auto Next Step");
        }
    }
}
