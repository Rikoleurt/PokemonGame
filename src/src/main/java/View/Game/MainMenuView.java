package View.Game;

import Utils.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class MainMenuView extends BorderPane {

    public MainMenuView() {
        init();
    }

    public void init() {
        VBox verticalMenuContainer = new VBox();
        setCenter(verticalMenuContainer);

        HBox playRow = new HBox();
        HBox settingsRow = new HBox();
        HBox exitRow = new HBox();

        Button playButton = new Button("Play");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        playButton.setPrefWidth(600);
        playButton.setPrefHeight(100);
        playButton.setStyle("-fx-font-size: 30");
        playButton.setOnAction(e -> {
            onPlayPressed();
        });
        settingsButton.setPrefWidth(600);
        settingsButton.setPrefHeight(100);
        settingsButton.setStyle("-fx-font-size: 30");

        exitButton.setPrefWidth(600);
        exitButton.setPrefHeight(100);
        exitButton.setStyle("-fx-font-size: 30");

        playRow.getChildren().addAll(playButton);
        settingsRow.getChildren().addAll(settingsButton);
        exitRow.getChildren().addAll(exitButton);

        verticalMenuContainer.getChildren().addAll(playRow, settingsRow, exitRow);
        verticalMenuContainer.setSpacing(10);
        verticalMenuContainer.setPadding(new Insets(300,450,450,450));
        setCenter(verticalMenuContainer);
    }

    public void onPlayPressed() {
        SceneManager.switchStageTo(new SelectFightView());
    }
}
