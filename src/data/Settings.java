package data;

import javafx.scene.paint.Color;

/**
 * Class to store application wide settings.
 */
public class Settings {
    public static final int DATA_SET_SIZE = 6;
    public static final int DATA_SET_MINIMUM_SIZE = 2;
    public static final int DATA_SET_MAXIMUM_SIZE = 20;
    public static final int DATA_SET_STEP_SIZE = 1;

    public static final int AUTO_NEXT_MS = 100;
    public static final int AUTO_NEXT_MINIMUM_MS = 50;
    public static final int AUTO_NEXT_MAXIMUM_MS = 2000;
    public static final int AUTO_NEXT_STEP_SIZE = 10;

    public static final Color PIVOT_COLOR = Color.web("#FF0000");

    public static final Color BACKGROUND_COLOR = Color.web("#CFD8DC");
    public static final Color BAR_COLOR = Color.web("#1E88E5");
    public static final Color BAR_SORTED_COLOR = Color.web("#43A047");
    public static final Color BAR_COMPARED_COLOR = Color.web("#0D47A1");
}
