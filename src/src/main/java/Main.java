import Model.GameState;
import Model.Inventory.Bag;
import Model.Inventory.Items.Item;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import Server.SocketServer;
import Utils.SongManager;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import Utils.SceneManager;

import View.Training.Console.View.BattleConsole;
import View.Training.Console.View.ConsoleView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static Model.StaticObjects.TrainingVersion.PokemonSample.*;

public class Main extends Application { // extends Application

    BattleConsole console = BattleConsole.getInstance();
    GameState gs;
    SongManager songManager = SongManager.getInstance();

    @Override
    public void start(Stage primaryStage) throws IOException {

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
//        SceneManager.getStage().show();


        ConsoleView consoleView = new ConsoleView();
        Scene consoleScene = new Scene(consoleView, consoleWidth, screenHeight);

        Stage consoleStage = new Stage();
        consoleStage.setTitle("Battle Console");
        consoleStage.setScene(consoleScene);
        consoleStage.setX(0);
        consoleStage.setY(0);
        consoleStage.setWidth(consoleWidth);
        consoleStage.setHeight(screenHeight);
//        consoleStage.show();

        TextBubble textBubble = BattleView.getTextBubble();
        scene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));

//        songManager.playSong("/music/champion_steven.mp3");

//        Pokemon pikachu1 = initiatePikachu();
//        Pokemon roucool = initiatePidgey();
//        Pokemon bulbizarre = initiateBulbizarre();
        Pokemon carapuce = initiateCarapuce();
//        Pokemon salameche = initiateSalameche();
//
//        Map<Item, Integer> inventory = new HashMap<>();
//        Bag bag = new Bag(inventory);
//        LinkedList<Pokemon> playerTeam = new LinkedList<>();
//        playerTeam.add(carapuce);
//        playerTeam.add(pikachu1);
//        playerTeam.add(roucool);
//
//        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
//        opponentTeam.add(bulbizarre);
//        opponentTeam.add(salameche);
//
//
//        Trainer player = new Trainer("player", bag, playerTeam);
//        Trainer opponent = new Trainer("opponent", bag, opponentTeam);
//
//        gs = new GameState(player, opponent, 0);

//        SocketServer server = SocketServer.getInstance();
//        new Thread(() -> {
//            try {
//                server.start(5001, gs);
//            } catch (IOException e) {
//                System.out.println("Client connect failed " + e.getMessage());
//            }
//        }).start();

        Pokemon jungko = initiateJungko();
        Pokemon laggron = initiateLaggron();
        Pokemon cizayox = initiateCizayox();

        jungko.attack(laggron, jungko.getAttacks().getFirst());
        jungko.attack(laggron, jungko.getAttacks().get(1));

        jungko.attack(cizayox, jungko.getAttacks().getFirst());
        jungko.attack(cizayox, jungko.getAttacks().get(1));


        jungko.attack(carapuce, jungko.getAttacks().getFirst());
        System.out.println("Jungko has two types? = " + jungko.hasTwoTypes());
        System.out.println("Carapuce has two types? = " + carapuce.hasTwoTypes());
        System.out.println("Cizayox has two types? = " + cizayox.hasTwoTypes());
        System.out.println("Laggron has two types? = " + laggron.hasTwoTypes());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
