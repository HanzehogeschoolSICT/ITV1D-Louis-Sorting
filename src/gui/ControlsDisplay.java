package gui;

import algorithms.Algorithm;
import algorithms.BubbleSortAlgorithm;
import algorithms.InsertionSortAlgorithm;
import algorithms.QuickSortAlgorithm;
import data.Settings;
import models.DataBundleModel;
import models.DataSetModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;

class ControlsDisplay extends JPanel {
    private DataBundleModel dataBundle;

    private Algorithm algorithm;
    private HashMap<String, Callable<Algorithm>> algorithmHashMap = new HashMap<>();

    private Timer autoNextStepTimer;
    private JButton autoNextStepButton;
    private JSpinner autoNextStepSpinner;

    ControlsDisplay(DataBundleModel dataBundle) {
        this.dataBundle = dataBundle;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        initializeAlgorithms();
        initializeNextStep();
        initializeAutoNextStep();
    }

    private void initializeAlgorithms() {
        algorithmHashMap.put("BubbleSort", BubbleSortAlgorithm::new);
        algorithmHashMap.put("InsertionSort", InsertionSortAlgorithm::new);
        algorithmHashMap.put("QuickSort", QuickSortAlgorithm::new);

        Set<String> algorithmNames = algorithmHashMap.keySet();
        JComboBox algorithmComboBox = new JComboBox<>(algorithmNames.toArray(new String[algorithmNames.size()]));
        algorithmComboBox.addActionListener(this::algorithmSelected);

        // Trigger algorithmSelected to make sure an algorithm exists.
        algorithmComboBox.setSelectedIndex(0);

        add(algorithmComboBox);
    }

    private void algorithmSelected(ActionEvent actionEvent) {
        JComboBox algorithmComboBox = (JComboBox)actionEvent.getSource();
        String algorithmName = (String)algorithmComboBox.getSelectedItem();

        try {
            algorithm = algorithmHashMap.get(algorithmName).call();
        } catch (Exception exception) {
            System.out.println("Failed to create algorithm.");
        }
    }

    private void initializeNextStep() {
        JButton nextStepButton = new JButton("Next Step");
        nextStepButton.addActionListener(this::nextStep);
        add(nextStepButton);
    }

    private void nextStep(ActionEvent actionEvent) {
        DataSetModel dataSet = dataBundle.getDataSetModel();
        algorithm.nextStep(dataSet);

        DataSetDisplay dataSetDisplay = dataBundle.getDataSetDisplay();
        dataSetDisplay.displayDataSet();
    }

    private void initializeAutoNextStep() {
        autoNextStepButton = new JButton("Start Auto Next Step");
        autoNextStepButton.addActionListener(this::autoNextStepToggle);
        add(autoNextStepButton);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(
                Settings.AUTO_NEXT_STEP_VALUE, Settings.AUTO_NEXT_STEP_MINIMUM,
                Settings.AUTO_NEXT_STEP_MAXIMUM, Settings.AUTO_NEXT_STEP_STEP_SIZE);
        autoNextStepSpinner = new JSpinner(spinnerNumberModel);
        add(autoNextStepSpinner);

        JLabel autoNextStepLabel = new JLabel("ms");
        add(autoNextStepLabel);

        autoNextStepTimer = new Timer(0, this::nextStep);
    }

    private void autoNextStepToggle(ActionEvent actionEvent) {
        if (autoNextStepTimer.isRunning()) {
            autoNextStepTimer.stop();

            autoNextStepSpinner.setEnabled(true);
            autoNextStepButton.setText("Start Auto Next Step");
        } else {
            int autoNextStepMs = (int)autoNextStepSpinner.getValue();
            autoNextStepTimer.setDelay(autoNextStepMs);
            autoNextStepTimer.start();

            autoNextStepSpinner.setEnabled(false);
            autoNextStepButton.setText("Stop Auto Next Step");
        }
    }
}
