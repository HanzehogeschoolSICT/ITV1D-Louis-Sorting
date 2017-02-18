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
import java.util.HashMap;
import java.util.Set;

public class ControlsDisplay extends JPanel {
    private DataBundleModel dataBundle;

    private HashMap<String, Algorithm> algorithmHashMap = new HashMap<>();
    private JComboBox<String> algorithmComboBox;

    private Timer autoNextStepTimer;
    private JButton autoNextStepButton;
    private JSpinner autoNextStepSpinner;

    public ControlsDisplay(DataBundleModel dataBundle) {
        this.dataBundle = dataBundle;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        initializeAlgorithms();
        initializeNextStep();
        initializeAutoNextStep();
    }

    private void initializeAlgorithms() {
        algorithmHashMap.put("BubbleSort", new BubbleSortAlgorithm());
        algorithmHashMap.put("InsertionSort", new InsertionSortAlgorithm());
        algorithmHashMap.put("QuickSort", new QuickSortAlgorithm());

        Set<String> algorithmNames = algorithmHashMap.keySet();
        algorithmComboBox = new JComboBox<>(algorithmNames.toArray(new String[algorithmNames.size()]));
        add(algorithmComboBox);
    }

    private void initializeNextStep() {
        JButton nextStepButton = new JButton("Next Step");
        nextStepButton.addActionListener(e -> nextStep());
        add(nextStepButton);
    }

    private void nextStep() {
        String algorithmName = (String)algorithmComboBox.getSelectedItem();
        Algorithm algorithm = algorithmHashMap.get(algorithmName);

        DataSetModel dataSet = dataBundle.getDataSetModel();
        algorithm.nextStep(dataSet);

        DataSetDisplay dataSetDisplay = dataBundle.getDataSetDisplay();
        dataSetDisplay.displayDataSet();
    }

    private void initializeAutoNextStep() {
        autoNextStepButton = new JButton("Start Auto Next Step");
        autoNextStepButton.addActionListener(e -> autoNextStepToggle());
        add(autoNextStepButton);

        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(
                Settings.AUTO_NEXT_STEP_VALUE, Settings.AUTO_NEXT_STEP_MINIMUM,
                Settings.AUTO_NEXT_STEP_MAXIMUM, Settings.AUTO_NEXT_STEP_STEP_SIZE);
        autoNextStepSpinner = new JSpinner(spinnerNumberModel);
        add(autoNextStepSpinner);

        JLabel autoNextStepLabel = new JLabel("ms");
        add(autoNextStepLabel);

        autoNextStepTimer = new Timer(0, e -> nextStep());
    }

    private void autoNextStepToggle() {
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
