import Server.SocketServer;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static View.Game.Battle.BattleView.npc;
import static View.Game.Battle.BattleView.player;

public class Main extends Application {

    SocketServer server = new SocketServer();
    @Override
    public void start(Stage primaryStage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double consoleWidth = screenWidth * 0.25;
        double gameWidth = screenWidth - consoleWidth;

        SceneManager.setStage(primaryStage);

        BattleView battleView = new BattleView();
        SceneManager.setFightView(battleView);

        Font globalFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);
        Scene scene = new Scene(battleView, gameWidth, screenHeight);
        scene.getRoot().setStyle("-fx-font-family: '" + globalFont.getName() + "'; -fx-font-size: 18px;");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm());
        SceneManager.getStage().setScene(scene);
        SceneManager.getStage().setX(consoleWidth);
        SceneManager.getStage().setY(0);
        SceneManager.getStage().setWidth(gameWidth);
        SceneManager.getStage().setHeight(screenHeight);
        SceneManager.getStage().setTitle("Pokémon Game");
        SceneManager.getStage().show();


//        BattleView battleView = new BattleView();
//        Scene consoleScene = new Scene(battleView, consoleWidth, screenHeight);
//
//        Stage consoleStage = new Stage();
//        consoleStage.setTitle("Battle Console");
//        consoleStage.setScene(consoleScene);
//        consoleStage.setX(0);
//        consoleStage.setY(0);
//        consoleStage.setWidth(consoleWidth);
//        consoleStage.setHeight(screenHeight);
//        consoleStage.show();

        TextBubble textBubble = BattleView.getTextBubble();

        new Thread(() -> {
            try {
                server.start(5000);
                System.out.println("Client connected to server");
            } catch (IOException e) {
                System.out.println("Client connect failed " + e.getMessage());
            }
        }).start();
         scene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));

    }

    static void main(String[] args) {
        launch(args);
    }
}
