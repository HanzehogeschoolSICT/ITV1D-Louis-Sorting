package models;

/**
 * Model containing information to draw the pivot on the canvas.
 */
public class PivotModel {
    private final int lowIndex;
    private final int highIndex;
    private final int pivot;

    /**
     * Initialize the pivot model.
     *
     * @param lowIndex  Low index of the current partition.
     * @param highIndex High index of the current partition.
     * @param pivot     Current pivot.
     */
    public PivotModel(int lowIndex, int highIndex, int pivot) {
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
        this.pivot = pivot;
    }

    /**
     * Get the low index of the current partition.
     *
     * @return Low index of the current partition.
     */
    public int getLowIndex() {
        return lowIndex;
    }

    /**
     * Get the high index of the current partition.
     *
     * @return High index of the current partition.
     */
    public int getHighIndex() {
        return highIndex;
    }

    /**
     * Get the current pivot.
     *
     * @return Current pivot.
     */
    public int getPivot() {
        return pivot;
    }
}
