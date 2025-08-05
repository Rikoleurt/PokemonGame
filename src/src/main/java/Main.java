import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.FightController;
import Server.SocketServer;
import View.Console.BattleLayout.BattleView;
import View.Game.FightView.FightView;
import View.Game.FightView.Text.TextBubble;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    FightView fightView = new FightView();
    TextBubble textBubble = fightView.getTextBubble();
    BattleView battleView = new BattleView();
    SocketServer server = new SocketServer();

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

        new Thread(() -> {
            try {
                server.start(5000);
                System.out.println("Client connected to server");
            } catch (IOException e) {
                System.out.println("Client connect failed " + e.getMessage());
                e.printStackTrace();
            }
        }).start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
