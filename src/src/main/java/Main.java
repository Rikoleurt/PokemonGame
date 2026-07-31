import Model.GameState;
import Model.StaticObjects.TrainingVersion.Matchup;
import Server.SocketServer;
import Utils.MatchupRandomizer;
import Utils.SongManager;
import View.GameView.BattleViews.BattleView;
import View.GameView.BattleViews.Text.TextBubble;
import Utils.SceneManager;
import View.GameView.MainMenuView;
import View.Training.Console.View.BattleConsole;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application { // extends Application

    BattleConsole console = BattleConsole.getInstance();
    static GameState gs;
    SongManager songManager = SongManager.getInstance();
    static MatchupRandomizer matchupRandomizer;
    @Override
    public void start(Stage primaryStage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double consoleWidth = screenWidth * 0.25;
        double gameWidth = screenWidth - consoleWidth;

        SceneManager.setStage(primaryStage);
        MainMenuView mainMenuView = new MainMenuView();
        SceneManager.setMainMenuView(mainMenuView);

        Font globalFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);

        Scene scene = new Scene(mainMenuView, screenWidth, screenHeight);
        scene.getRoot().setStyle("-fx-font-family: '" + globalFont.getName() + "'; -fx-font-size: 18px;");
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm());

        SceneManager.getStage().setScene(scene);
        SceneManager.getStage().setX(screenHeight);
        SceneManager.getStage().setY(0);
        SceneManager.getStage().setWidth(screenWidth);
        SceneManager.getStage().setHeight(screenHeight);
        SceneManager.getStage().setTitle("Pokémon Game");
        SceneManager.getStage().show();


//        ConsoleView consoleView = new ConsoleView();
//        Scene consoleScene = new Scene(consoleView, consoleWidth, screenHeight);
//
//        Stage consoleStage = new Stage();
//        consoleStage.setTitle("Battle Console");
//        consoleStage.setScene(consoleScene);
//        consoleStage.setX(0);
//        consoleStage.setY(0);
//        consoleStage.setWidth(consoleWidth);
//        consoleStage.setHeight(screenHeight);
//        consoleStage.show();
//
        TextBubble textBubble = BattleView.getTextBubble();
        scene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));

        scene.setOnKeyPressed(event -> {
            System.out.println("[Main] key=" + event.getCode());
            textBubble.handleKeyPress(event.getCode());
        });
//        songManager.playSong("/music/champion_steven.mp3");
        gs = Matchup.createGameState(BattleView.getPlayer(), BattleView.getAgent());
//        train();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void train(){
        gs = Matchup.salamecheVsBulbizarre().createGameState();
        System.out.println(gs.pretty_state());
        SocketServer server = SocketServer.getInstance();
        new Thread(() -> {
            try {
                server.startAndTrain(5001, gs);
            } catch (IOException e) {
                System.out.println("Client connect failed " + e.getMessage());
            }
        }).start();
    }
}
