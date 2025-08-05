package View.Game;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SceneManager {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setRoot(Parent root){
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(SceneManager.class.getResource("/style/style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void switchStageTo(Parent root){
        primaryStage.getScene().setRoot(root);
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
