import data.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;

public class Bootstrap extends Application {
    /**
     * Start the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the application.
     *
     * @param primaryStage Primary GUI container.
     */
    @Override
    public void start(Stage primaryStage) {
        showGui(primaryStage);
    }

    /**
     * Show the GUI for the application.
     *
     * @param stage GUI container to use.
     */
    private void showGui(Stage stage) {
        URL mainDisplayUrl = Bootstrap.class.getResource("/MainDisplay.fxml");

        try {
            Parent mainDisplay = FXMLLoader.load(mainDisplayUrl);
            stage.setTitle("Sorting");

            stage.setScene(new Scene(mainDisplay));
            stage.setResizable(false);
            stage.sizeToScene();

            stage.show();
        } catch (IOException exception) {
            System.out.println("Failed to load GUI");
        }
    }

    /**
     * Handle application exit to destroy resources.
     */
    @Override
    public void stop() {
        // Stop the timer if it's still running.
        Timer timer = DataManager.getTimer();
        timer.cancel();

        // Kill the current algorithm to allow the program to exit.
        DataManager.setAlgorithm(null);
    }
}
