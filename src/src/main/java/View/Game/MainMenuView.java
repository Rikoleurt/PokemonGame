package View.Game;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuView extends BorderPane {

    public MainMenuView() {

    }

    public void init() {
        VBox verticalMenuContainer = new VBox();
        setCenter(verticalMenuContainer);

        HBox playRow = new HBox();
        HBox settingsRow = new HBox();
        HBox exitRow = new HBox();

        Label playLabel = new Label("Play");
        Label settingsLabel = new Label("Settings");
        Label exitLabel = new Label("Exit");

        playRow.getChildren().addAll(playLabel);
        settingsRow.getChildren().addAll(settingsLabel);
        exitRow.getChildren().addAll(exitLabel);

        verticalMenuContainer.getChildren().addAll(playRow, settingsRow, exitRow);
    }
}
