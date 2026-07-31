package View.GameView;

import Model.Inventory.Bag;
import Model.Person.Trainer;
import Model.Pokemon.Field;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Weather;
import Model.StaticObjects.TrainingVersion.Matchup;
import Utils.SceneManager;
import View.GameView.BattleViews.BattleView;
import View.View;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class SelectFightView extends BorderPane implements View {

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
        firstScenarioButton.setOnAction(e -> {onFirstScenarioPressed();});

        secondScenarioButton.setPrefWidth(200);
        secondScenarioButton.setPrefHeight(400);
        secondScenarioButton.setStyle("-fx-font-size: 30");
        secondScenarioButton.setOnAction(e -> {onSecondScenarioPressed();});

        thirdScenarioButton.setPrefWidth(200);
        thirdScenarioButton.setPrefHeight(400);
        thirdScenarioBox.setStyle("-fx-font-size: 30");
        thirdScenarioButton.setOnAction(e -> {onThirdScenarioPressed();});

        // Center the buttons
        horizontalMainContainer.setPadding(new Insets(150, 0, 0, 400));

        firstScenarioBox.getChildren().addAll(firstScenarioButton);
        secondScenarioBox.getChildren().addAll(secondScenarioButton);
        thirdScenarioBox.getChildren().addAll(thirdScenarioButton);

        horizontalMainContainer.getChildren().addAll(firstScenarioBox, secondScenarioBox, thirdScenarioBox);

        setCenter(horizontalMainContainer);
    }

    private void onFirstScenarioPressed() {
        Matchup matchup = Matchup.juanScenario6v6();

        Trainer player = new Trainer("Player", new Bag(Map.of()), matchup.playerTeam());
        Trainer agent = new Trainer("Agent", new Bag(Map.of()), matchup.opponentTeam());

        Field field = new Field(player.getTeam(), agent.getTeam(), Debris.normal, Weather.normal);

        BattleView battleView = new BattleView(player, agent, field);
        BattleView.refreshSprites();

        SceneManager.setFightView(battleView);
        SceneManager.setRoot(battleView);
    }

    private void onSecondScenarioPressed() {
        Matchup matchup = Matchup.spectraScenario6v6();

        Trainer player = new Trainer("Player", new Bag(null), matchup.playerTeam());
        Trainer agent = new Trainer("Agent", new Bag(null), matchup.opponentTeam());

        Field field = new Field(player.getTeam(), agent.getTeam(), Debris.normal, Weather.normal);

        BattleView battleView = new BattleView(player, agent, field);
        BattleView.refreshSprites();

        SceneManager.setFightView(battleView);
        SceneManager.setRoot(battleView);
    }

    private void onThirdScenarioPressed() {
        Matchup matchup = Matchup.pierreRochardScenario6v6();

        Trainer player = new Trainer("Player", new Bag(null), matchup.playerTeam());
        Trainer agent = new Trainer("Agent", new Bag(null), matchup.opponentTeam());

        Field field = new Field(player.getTeam(), agent.getTeam(), Debris.normal, Weather.normal);

        BattleView battleView = new BattleView(player, agent, field);
        BattleView.refreshSprites();

        SceneManager.setFightView(battleView);
        SceneManager.setRoot(battleView);
    }
}
