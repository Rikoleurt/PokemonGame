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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Pokemon.TerrainEnum.Meteo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    static Player player = initiatePlayer();
    static NPC npc = initiateEnemy();
    static Terrain terrain = initiateTerrain();

    private static int playerPokemonHP = player.getFrontPokemon().getHP();
    private static int foePokemonHP = npc.getFrontPokemon().getHP();
    private static ProgressBar playerHPBar = new ProgressBar(1);
    private static ProgressBar opponentHPBar = new ProgressBar(1);
    private final Label playerHPLabel = new Label("HP: " + playerPokemonHP);
    private static Label opponentHPLabel = new Label("HP: " + foePokemonHP);

    VBox layout = new VBox(10);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label playerLabel = new Label("Pikachu");
        Label opponentLabel = new Label("Carapuce");

        layout.getChildren().addAll(opponentLabel, opponentHPBar, opponentHPLabel, playerLabel, playerHPBar, playerHPLabel);
        addAttackButtons();
        Scene scene = new Scene(layout, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Combat Pokémon");
        stage.show();
    }

    void addAttackButtons(){
        Button fightButton = new Button("Fight");
        Button bagButton = new Button("Bag");
        Button pokemonButton = new Button("Pokemon");
        Button runButton = new Button("Run");

        Pokemon p = player.getFrontPokemon();
        Pokemon enemyP = npc.getFrontPokemon();

        layout.getChildren().addAll(fightButton, bagButton, pokemonButton, runButton);

        Button atk1Button = new Button(p.getAttacks().get(0).getName());
        Button atk2Button = new Button(p.getAttacks().get(1).getName());
        Button atk3Button = new Button(p.getAttacks().get(2).getName());
        Button atk4Button = new Button(p.getAttacks().get(3).getName());

        fightButton.setOnAction(e -> {
            layout.getChildren().removeAll(fightButton, pokemonButton, runButton,bagButton);
            layout.getChildren().addAll(atk1Button, atk2Button, atk3Button, atk4Button);
        });
        Attack pAtk1 = (Attack) p.getAttacks().get(0);
        Attack pAtk2 = (Attack) p.getAttacks().get(1);
        StatusAttack pAtk3 = (StatusAttack) p.getAttacks().get(2);
        Attack pAtk4 = (Attack) p.getAttacks().get(3);

        atk1Button.setOnAction(e -> {
            p.attack(enemyP, pAtk1, terrain);
            printAttackText(pAtk1);
            updateHPBars();
        });

        atk2Button.setOnAction(e -> {
            p.attack(enemyP, pAtk2, terrain);
            printAttackText(pAtk2);
            updateHPBars();
        });

        atk3Button.setOnAction(e -> {
            p.attack(enemyP, pAtk3, terrain);
            printAttackText(pAtk3);
            updateHPBars();
        });

        atk4Button.setOnAction(e -> {
            p.attack(enemyP, pAtk4, terrain);
            printAttackText(pAtk4);
            updateHPBars();
        });


        System.out.println(npc.getFrontPokemon().getName());
        System.out.println(npc.getFrontPokemon().getHP());
        foePokemonHP = enemyP.getHP();
        opponentHPBar.setProgress((double) foePokemonHP / 44);
        opponentHPLabel.setText("HP: " + foePokemonHP);
    }

    private void printAttackText(Move move){
        Label textLabel = new Label(player.getFrontPokemon().getName() + " uses " + player.getFrontPokemon().getAttack(move).getName());
        textLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

        // Création de la bulle
        Rectangle bubble = new Rectangle(250, 50);
        bubble.setArcWidth(20);
        bubble.setArcHeight(20);
        bubble.setFill(Color.BLACK);
        bubble.setStroke(Color.WHITE);

        // Ajout du texte dans la bulle
        StackPane textBubble = new StackPane();
        textBubble.getChildren().addAll(bubble, textLabel);
    }

    private void updateHPBars() {
        foePokemonHP = npc.getFrontPokemon().getHP();  // Récupérer les HP actuels de l'adversaire
        playerPokemonHP = player.getFrontPokemon().getHP(); // Récupérer les HP du joueur

        opponentHPBar.setProgress((double) foePokemonHP / npc.getFrontPokemon().getMaxHP());
        opponentHPLabel.setText("HP: " + foePokemonHP);

        playerHPBar.setProgress((double) playerPokemonHP / player.getFrontPokemon().getMaxHP());
        playerHPLabel.setText("HP: " + playerPokemonHP);
    }

    static Terrain initiateTerrain(){
        return new Terrain(player.getTeam(), npc.getTeam(), Debris.normal, Meteo.normal);
    }
    static Player initiatePlayer() {

        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        StatusAttack thunderWave = new StatusAttack("ThunderWave", 100, Status.paralyzed, Type.electric, AttackMode.status, 20);
        Attack electricPunch = new Attack("Electric punch", 80,100, Type.electric, AttackMode.physical, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);
        Attack vineWhip = new Attack("Ember", 40,100,Type.grass, AttackMode.physical, 40);
        DebrisAttack toxicSpikes = new DebrisAttack("Toxic Spikes", Type.poison, AttackMode.status, 20, Debris.poisonSpikes );
        DebrisAttack stealthRock = new DebrisAttack("Stealth Rock", Type.rock, AttackMode.status, 40, Debris.stealthRock);
        DebrisAttack spikes = new DebrisAttack("Spikes", Type.normal, AttackMode.status,40, Debris.spikes);
        UpgradeMove swordDance = new UpgradeMove("Sword Dance", "atk",-2, Type.normal, AttackMode.status, 20);

        ArrayList<Move> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);
        pikachuAtk.add(electricPunch);
        pikachuAtk.add(swordDance);

        ArrayList<Move> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);
        carapuceAtk.add(toxicSpikes);
        carapuceAtk.add(stealthRock);
        carapuceAtk.add(spikes);

        ArrayList<Move> salamecheAtk = new ArrayList<>();
        salamecheAtk.add(ember);
        ArrayList<Move> bulbizarreAtk = new ArrayList<>();
        bulbizarreAtk.add(vineWhip);

        Pokemon pikachu = new Pokemon("pikachu", 35, 35, 55, 55, 40, 40, 50, 50, 50, 50,
                90, 90, 9, Type.electric, pikachuAtk, 0, 0, 0, 0, 0,
                Status.normal, "male");

        Pokemon carapuce = new Pokemon(44, 44, 48, 65, 50, 64, 43, 10,
                Type.water, carapuceAtk, "Carapuce", Status.normal, "female");

        Pokemon salameche = new Pokemon(39, 39, 52, 43, 60, 50, 65, 11,
                Type.fire, salamecheAtk, "Salamèche", Status.normal, "male");

        Pokemon bulbizarre = new Pokemon(45, 45, 49, 49, 65, 65, 45, 10,
                Type.grass, bulbizarreAtk, "Bulbizarre", Status.normal, "female");

        Map<Items, Integer> inventory = new HashMap<>();
        inventory.put(new Heal("Potion", "Heals 20HP", 20), 3);
        Bag bag = new Bag(inventory, 100);

        LinkedList<Pokemon> team = new LinkedList<>();

        team.add(pikachu);
        team.add(bulbizarre);
        team.add(salameche);

        return new Player("Jason", bag, team);
    }

    static NPC initiateEnemy(){

        LinkedList<Pokemon> enemyTeam = new LinkedList<>();
        ArrayList<Move> carapuceAtk = new ArrayList<>();
        ArrayList<Move> salamecheAtk = new ArrayList<>();

        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);

        Pokemon carapuce = new Pokemon(44, 44, 48, 65, 50, 64, 43, 10,
                Type.water, carapuceAtk, "Carapuce", Status.normal, "female");

        Pokemon salameche = new Pokemon(39, 39, 52, 43, 60, 50, 65, 11,
                Type.fire, salamecheAtk, "Salamèche", Status.normal, "male");

        enemyTeam.add(carapuce);
        enemyTeam.add(salameche);

        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);

        salamecheAtk.add(ember);
        return new NPC("npc", enemyTeam);
    }

    static void printHP(Pokemon pokemon) {
        System.out.println(pokemon.getHP() + "/" + pokemon.getMaxHP());
    }
    static void printStatus(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " is " + pokemon.getStatus());
    }
    static void printAtk(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " : " + pokemon.getAtk());
    }
    static void printDef(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " : " + pokemon.getDef());}
    static void printSpeed(Pokemon pokemon) {System.out.println(pokemon.getName() + " : " + pokemon.getSpeed());
    }
    static void printAtkSpe(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " : " + pokemon.getAtkSpe());
    }
    static void printDefSpe(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " : " + pokemon.getDefSpe());
    }
}


