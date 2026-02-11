import Model.GameState;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Server.SocketServer;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;

import View.Training.Console.View.BattleConsole;
import View.Training.Console.View.ConsoleView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import static View.Game.Battle.BattleView.npc;
import static View.Game.Battle.BattleView.player;
import static Model.StaticObjects.PokemonSample.*;
import static Model.StaticObjects.MovesSample.*;

public class Main extends Application {

    BattleConsole console = BattleConsole.getInstance();

    @Override
    public void start(Stage primaryStage) {

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double consoleWidth = screenWidth * 0.25;
        double gameWidth = screenWidth - consoleWidth;

        SceneManager.setStage(primaryStage);

//        BattleView battleView = new BattleView();
//        SceneManager.setFightView(battleView);
//
//        Font globalFont = Font.loadFont(Objects.requireNonNull(getClass().getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);
//        Scene scene = new Scene(battleView, gameWidth, screenHeight);
//        scene.getRoot().setStyle("-fx-font-family: '" + globalFont.getName() + "'; -fx-font-size: 18px;");
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/style.css")).toExternalForm());
//        SceneManager.getStage().setScene(scene);
//        SceneManager.getStage().setX(consoleWidth);
//        SceneManager.getStage().setY(0);
//        SceneManager.getStage().setWidth(gameWidth);
//        SceneManager.getStage().setHeight(screenHeight);
//        SceneManager.getStage().setTitle("Pokémon Game");
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
        consoleStage.show();

        TextBubble textBubble = BattleView.getTextBubble();
//        scene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));

//        SocketServer server = SocketServer.getInstance();
//        new Thread(() -> {
//            try {
//                server.start(5001);
//            } catch (IOException e) {
//                System.out.println("Client connect failed " + e.getMessage());
//            }
//        }).start();

        Pokemon pikachu = initiatePikachu();
        Pokemon salameche = initiateSalameche();

        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(pikachu);

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(salameche);
        Trainer player = new Trainer("player", playerTeam);
        Trainer opponent = new Trainer("opponent", opponentTeam);
        GameState gs = new GameState(player, opponent, 0);
        System.out.println(player.getFrontPokemon().getStatus());
        while(opponent.getHealthyPokemon() > 0 && player.getHealthyPokemon() > 0) {
            System.out.println("opponent : " + opponent.getHealthyPokemon());
            System.out.println("player : " + player.getHealthyPokemon());
            opponent.setAction(Action.Attack);
            player.setAction(Action.Attack);

            handle_attack(gs);
            console.log(gs.state());
        }
    }

    static void main(String[] args) {
    }

    private void handle_attack(GameState gs) {
        Trainer player = gs.getPlayer();
        Trainer opponent = gs.getOpponent();

        Pokemon player_pokemon = player.getFrontPokemon();
        Pokemon opponent_pokemon = opponent.getFrontPokemon();

        if(gs.is_player_first()){
            Move m1 = player.getFrontPokemon().chooseMove();
            player_pokemon.attack(opponent_pokemon, m1);

            Move m2 = opponent.getFrontPokemon().chooseMove();
            opponent_pokemon.attack(player_pokemon, m2);
        } else {
            Move m2 = opponent.getFrontPokemon().chooseMove();
            opponent_pokemon.attack(player_pokemon, m2);

            Move m1 = player.getFrontPokemon().chooseMove();
            player_pokemon.attack(opponent_pokemon, m1);
        }
    }
}
