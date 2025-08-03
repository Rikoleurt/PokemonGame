import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.FightController;
import View.Console.BattleLayout.BattleView;
import View.Game.FightView.FightView;
import View.Game.FightView.Text.TextBubble;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.scene.input.KeyCode.SPACE;

public class Main extends Application {

    FightView fightView = new FightView();
    TextBubble textBubble = fightView.getTextBubble();
    BattleView battleView = new BattleView();

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double consoleWidth = screenWidth * 0.25;
        double gameWidth = screenWidth - consoleWidth;

        Scene gameScene = new Scene(fightView, gameWidth, screenHeight);
        Scene consoleScene = new Scene(battleView, consoleWidth, screenHeight);

        fightView.setStyle("-fx-alignment: center;");
        primaryStage.setTitle("Pokémon Game");

        primaryStage.setScene(gameScene);
        primaryStage.setX(consoleWidth);
        primaryStage.setY(0);
        primaryStage.setWidth(gameWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.show();

        Stage consoleStage = new Stage();
        consoleStage.setTitle("Battle Console");
        consoleStage.setScene(consoleScene);
        consoleStage.setX(0);
        consoleStage.setY(0);
        consoleStage.setWidth(consoleWidth);
        consoleStage.setHeight(screenHeight);
        consoleStage.show();

        gameScene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
