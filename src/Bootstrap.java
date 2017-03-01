import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Bootstrap extends Application {
    /**
     * Start the application.
     *
     * @param args Any arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        showGui(primaryStage);
    }

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
}
