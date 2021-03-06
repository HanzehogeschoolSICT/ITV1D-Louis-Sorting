package controllers;

import algorithms.Algorithm;
import algorithms.QuickSortAlgorithm;
import data.DataManager;
import data.Settings;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.TextAlignment;
import models.DataSetModel;
import models.DrawBarDataModel;
import models.PivotModel;

/**
 * Controller for the data set.
 */
public class DataSetController {
    @FXML
    private Canvas dataSetCanvas;

    private DataSetModel dataSet;

    private GraphicsContext graphics;

    /**
     * Initialize the data set controller.
     */
    @FXML
    public void initialize() {
        graphics = dataSetCanvas.getGraphicsContext2D();

        Property<DataSetModel> dataSetProperty = DataManager.getDataSetProperty();
        dataSetProperty.addListener(((observable, oldValue, newValue) -> updateDataSet(newValue)));

        Property<Integer> currentStepProperty = DataManager.getCurrentStepProperty();
        currentStepProperty.addListener(((observable, oldValue, newValue) -> drawDataSet(false)));

        // Initial data set draw.
        DataSetModel dataSet = DataManager.getDataSet();
        updateDataSet(dataSet);
    }

    /**
     * Update the data set and display the updated data set.
     *
     * @param dataSet Data set to use.
     */
    private void updateDataSet(DataSetModel dataSet) {
        this.dataSet = dataSet;

        drawDataSet(true);
    }

    /**
     * Draw the current data set on the canvas.
     *
     * @param initialDataSet True if the data set is new, false otherwise.
     */
    private void drawDataSet(boolean initialDataSet) {
        clear();

        double width = dataSetCanvas.getWidth();
        double height = dataSetCanvas.getHeight();

        double heightPerNumber = height / dataSet.getHighestNumber();
        double widthPerBar = width / dataSet.getHighestNumber();

        DrawBarDataModel drawBarData = new DrawBarDataModel(heightPerNumber, widthPerBar);
        for (Integer number : dataSet)
            drawBar(number, drawBarData, getBarColor(dataSet, number));

        if (!initialDataSet)
            drawPivot(height, heightPerNumber, widthPerBar);
    }

    /**
     * Clear the canvas.
     */
    private void clear() {
        double width = dataSetCanvas.getWidth();
        double height = dataSetCanvas.getHeight();

        graphics.setFill(Settings.BACKGROUND_COLOR);
        graphics.fillRect(0, 0, width, height);
    }

    /**
     * Draw a single bar on the canvas.
     *
     * @param number      Number of the data set to draw.
     * @param drawBarData Data for determining the size and position of the bar.
     * @param color       Color of the bar.
     */
    private void drawBar(int number, DrawBarDataModel drawBarData, Color color) {
        double barHeight = drawBarData.getHeightForBar(number);
        double x = drawBarData.getCurrentX();
        double y = dataSetCanvas.getHeight() - barHeight;

        Rectangle2D barRectangle = new Rectangle2D(x, y, drawBarData.getWidthPerBar(), barHeight);

        graphics.setFill(color);
        graphics.fillRect(barRectangle.getMinX(), barRectangle.getMinY(),
                barRectangle.getWidth(), barRectangle.getHeight());

        graphics.setFill(Color.WHITE);
        drawNumberInBar(number, barRectangle);
        drawBarData.increaseCurrentX();
    }

    /**
     * Get the color to use to draw the specified number.
     *
     * @param number Number to get the color for.
     * @return Color to use to draw the specified number.
     */
    private Color getBarColor(DataSetModel dataSet, int number) {
        if (dataSet.getIsSorted())
            return Settings.BAR_SORTED_COLOR;

        if (dataSet.isNumberCompared(number))
            return Settings.BAR_COMPARED_COLOR;

        return Settings.BAR_COLOR;
    }

    /**
     * Draw the given number within the given bar rectangle.
     *
     * @param number       Number to draw.
     * @param barRectangle Bar rectangle to use.
     */
    private void drawNumberInBar(Integer number, Rectangle2D barRectangle) {
        graphics.setFontSmoothingType(FontSmoothingType.LCD);

        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.TOP);

        double centerX = barRectangle.getMinX() + barRectangle.getWidth() / 2;
        double topY = barRectangle.getMinY();

        String numberText = number.toString();
        graphics.fillText(numberText, centerX, topY);
    }

    /**
     * Draw the pivot (if any) on the canvas.
     *
     * @param height          Total height of the canvas.
     * @param heightPerNumber Height per number.
     * @param widthPerBar     Width per bar.
     */
    private void drawPivot(double height, double heightPerNumber, double widthPerBar) {
        Algorithm algorithm = DataManager.getAlgorithm();
        // Only QuickSort has a pivot, and there's no point in showing the pivot if the data set is sorted already.
        if (!(algorithm instanceof QuickSortAlgorithm) || dataSet.getIsSorted())
            return;

        PivotModel pivot = ((QuickSortAlgorithm) algorithm).getCurrentPivot();

        double startX = widthPerBar * pivot.getLowIndex();
        // Increase the high index by one, because this bar should be included as well.
        double endX = widthPerBar * (pivot.getHighIndex() + 1);

        double y = height - (heightPerNumber * pivot.getPivot());

        graphics.setStroke(Settings.PIVOT_COLOR);
        graphics.strokeLine(startX, y, endX, y);
    }
}
