import Inventory.Bag;
import Inventory.Items.Heal.Heal;
import Inventory.Items.Items;
import Person.NPC;
import Person.Player;
import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.Attacks.UpgradeMove;
import Pokemon.Attacks.Attack;
import Pokemon.Attacks.StatusAttack;
import Pokemon.Attacks.DebrisAttack;

import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.Move;
import Pokemon.TerrainEnum.Debris;
import Pokemon.Terrain;

import java.util.*;

import Pokemon.TerrainEnum.Meteo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static StaticObjects.Moves.initiateAttacks;
import static StaticObjects.NPC.initiateEnemy;
import static StaticObjects.Player.initiatePlayer;
import static javafx.application.Application.launch;

public class Main extends Application {

    static Font customFont = Font.loadFont(Main.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 20);

    static Player player = initiatePlayer();
    static NPC npc = initiateEnemy();
    static Terrain terrain = initiateTerrain();
    static HashMap<Integer, Move> attacks = initiateAttacks();

    public static void main(String[] args) {
        System.out.println(player.getFrontPokemon().getName());
    }

        @Override
        public void start(Stage stage) throws Exception {

        }

        private Button createStyledButton(String text) {
            Button button = new Button(text);
            button.setFont(customFont);
            button.setPrefSize(150, 50);  // Taille uniforme des boutons
            button.setStyle("-fx-background-color: white; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 0px; " +
                    "-fx-background-radius: 0px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: black; ");
            return button;
        }

        static Terrain initiateTerrain() {
            return new Terrain(player.getTeam(), npc.getTeam(), Debris.normal, Meteo.normal);
        }

        static void printHP(Pokemon pokemon)
        {
            System.out.println(pokemon.getHP() + "/" + pokemon.getMaxHP());
        }

        static void printStatus(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " is " + pokemon.getStatus());
        }

        static void printAtk(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " : " + pokemon.getAtk());
        }

        static void printDef(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " : " + pokemon.getDef());
        }

        static void printSpeed(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " : " + pokemon.getSpeed());
        }

        static void printAtkSpe(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " : " + pokemon.getAtkSpe());
        }

        static void printDefSpe(Pokemon pokemon)
        {
            System.out.println(pokemon.getName() + " : " + pokemon.getDefSpe());
        }
    }
