package View.Game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectFightView extends BorderPane {

    public SelectFightView() {
        init();
    }

    public void init() {
        HBox horizontalMainContainer = new HBox();
        horizontalMainContainer.setSpacing(10);

        VBox firstScenarioBox = new VBox();
        VBox secondScenarioBox = new VBox();
        VBox thirdScenarioBox = new VBox();

        Button firstScenarioButton = new Button("First Battle");
        Button secondScenarioButton = new Button("Second Battle");
        Button thirdScenarioButton = new Button("Final Battle");

        firstScenarioButton.setPrefWidth(200);
        firstScenarioButton.setPrefHeight(400);
        firstScenarioButton.setStyle("-fx-font-size: 30");
        firstScenarioButton.setOnAction(e -> {});

        secondScenarioButton.setPrefWidth(200);
        secondScenarioButton.setPrefHeight(400);
        secondScenarioButton.setStyle("-fx-font-size: 30");
        secondScenarioButton.setOnAction(e -> {});

        thirdScenarioButton.setPrefWidth(200);
        thirdScenarioButton.setPrefHeight(400);
        thirdScenarioBox.setStyle("-fx-font-size: 30");
        thirdScenarioButton.setOnAction(e -> {});

        // Center the buttons
        horizontalMainContainer.setPadding(new Insets(150, 0, 0, 400));
        // Rules for the player
        /*
          - Players can launch once the battle
          - Once they finished the battles, they must hand in the results
         */

        firstScenarioBox.getChildren().addAll(firstScenarioButton);
        secondScenarioBox.getChildren().addAll(secondScenarioButton);
        thirdScenarioBox.getChildren().addAll(thirdScenarioButton);

        horizontalMainContainer.getChildren().addAll(firstScenarioBox, secondScenarioBox, thirdScenarioBox);

        setCenter(horizontalMainContainer);
    }
}
