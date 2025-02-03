import Inventory.Inventory;
import Inventory.Items.Items;
import Person.NPC;
import Person.Player;
import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;
import Pokemon.Terrain;
import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    private int playerHP = 35;
    private static int opponentHP = 44;
    private ProgressBar playerHPBar = new ProgressBar(1);
    private static ProgressBar opponentHPBar = new ProgressBar(1);
    private Label playerHPLabel = new Label("HP: " + playerHP);
    private static Label opponentHPLabel = new Label("HP: " + opponentHP);

    VBox layout = new VBox(10);

    public static void main(String[] args) {

        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        Attack thunderWave = new Attack("ThunderWave", Status.paralyzed, 100, Type.electric, 40);
        Attack electricPunch = new Attack("Electric punch", 80,100, Type.electric, AttackMode.physical, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);
        Attack vineWhip = new Attack("Ember", 40,100,Type.grass, AttackMode.physical, 40);
        Attack toxicSpikes = new Attack("Toxic Spikes", Type.poison, 40 , Debris.poisonSpikes);
        Attack stealthRock = new Attack("Stealth Rock", Type.rock, 40, Debris.stealthRock);
        Attack spikes = new Attack("Spikes", Type.normal, 40, Debris.spikes);
        Attack swordDance = new Attack("Sword Dance", Type.normal, 20, "atk", -2);

        ArrayList<Attack> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);
        pikachuAtk.add(electricPunch);
        pikachuAtk.add(swordDance);

        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);
        carapuceAtk.add(toxicSpikes);
        carapuceAtk.add(stealthRock);
        carapuceAtk.add(spikes);

        ArrayList<Attack> salamecheAtk = new ArrayList<>();
        salamecheAtk.add(ember);
        ArrayList<Attack> bulbizarreAtk = new ArrayList<>();
        bulbizarreAtk.add(vineWhip);


        Pokemon pikachu = new Pokemon("pikachu",
                35, 35,
                55, 55,
                40, 40,
                50, 50,
                50, 50,
                90, 90,
                9,
                Type.electric,
                pikachuAtk,
                0, 0, 0, 0, 0, Status.normal, "male"
        );

        Pokemon carapuce = new Pokemon(
                44, 44, // HP et maxHP
                48, 65, // Attack et Defense
                50, 64, // Special Attack et Special Defense
                43,
                10,// Speed
                Type.water, carapuceAtk, "Carapuce", Status.normal,
                "female"
        );

        Pokemon salameche = new Pokemon(
                39, 39,
                52, 43,
                60, 50,
                65,
                11,
                Type.fire, salamecheAtk, "Salamèche", Status.normal,
                "male"
        );

        Pokemon bulbizarre = new Pokemon(
                45, 45,
                49, 49,
                65, 65,
                45,
                10,
                Type.grass, bulbizarreAtk, "Bulbizarre", Status.normal,
                "female"
        );


        LinkedList<Pokemon> team = new LinkedList<>();
        LinkedList<Pokemon> enemyTeam = new LinkedList<>();

        team.add(pikachu);
        team.add(bulbizarre);
        team.add(salameche);

        System.out.println("Team: " + team.get(0).getName() + " " + team.get(1).getName() + " " + team.get(2).getName());

        enemyTeam.add(carapuce);
        enemyTeam.add(salameche);

        NPC npc = new NPC("NPC", enemyTeam);
        Map<Items, Integer> bag = Map.of();

        Inventory inventory = new Inventory(bag, 100);

        Terrain terrain = new Terrain(team, enemyTeam, Debris.normal, Meteo.normal);

        Player player = new Player("Jason", inventory, team);

        player.sendPokemon(terrain);

        player.changePokemon(salameche,terrain);
        printHP(salameche);

        salameche.useAttack(carapuce, ember);

        printHP(carapuce);

        carapuce.useDebrisAttack(terrain, toxicSpikes, player.getFrontPokemon());
        carapuce.useDebrisAttack(terrain, toxicSpikes, player.getFrontPokemon());

        player.changePokemon(pikachu,terrain);
        printHP(pikachu);
        pikachu.useAttack(carapuce, thunder);
        printHP(pikachu);

        player.changePokemon(bulbizarre,terrain);
        printHP(bulbizarre);
        printStatus(bulbizarre);

        player.changePokemon(pikachu,terrain);

        printAtk(pikachu);
        pikachu.useStatAttack(swordDance);
        printAtk(pikachu);
        pikachu.useStatAttack(swordDance);
        printAtk(pikachu);
        pikachu.useStatAttack(swordDance);
        printAtk(pikachu);
        launch(args);

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

    @Override
    public void start(Stage stage) throws Exception {
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label playerLabel = new Label("Pikachu");
        Label opponentLabel = new Label("Carapuce");

        layout.getChildren().addAll(opponentLabel, opponentHPBar, opponentHPLabel, playerLabel, playerHPBar, playerHPLabel);
        useAttack();
        Scene scene = new Scene(layout, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Combat Pokémon");
        stage.show();
    }

    void useAttack(){
        Player player = initiatePlayer();
        NPC npc = initiateEnemy();
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
        Attack pAtk1 = p.getAttacks().get(0);
        Attack pAtk2 = p.getAttacks().get(1);
        Attack pAtk3 = p.getAttacks().get(2);
        Attack pAtk4 = p.getAttacks().get(3);

        atk1Button.setOnAction(e -> {p.useAttack(enemyP,pAtk1);});
        atk2Button.setOnAction(e ->{p.useAttack(enemyP,pAtk2);});
        atk3Button.setOnAction(e ->{p.useAttack(enemyP,pAtk3);});
        atk4Button.setOnAction(e ->{p.useAttack(enemyP,pAtk4);});
    }

    Player initiatePlayer() {

        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        Attack thunderWave = new Attack("ThunderWave", Status.paralyzed, 100, Type.electric, 40);
        Attack electricPunch = new Attack("Electric punch", 80,100, Type.electric, AttackMode.physical, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);
        Attack vineWhip = new Attack("Ember", 40,100,Type.grass, AttackMode.physical, 40);
        Attack toxicSpikes = new Attack("Toxic Spikes", Type.poison, 40 , Debris.poisonSpikes);
        Attack stealthRock = new Attack("Stealth Rock", Type.rock, 40, Debris.stealthRock);
        Attack spikes = new Attack("Spikes", Type.normal, 40, Debris.spikes);
        Attack swordDance = new Attack("Sword Dance", Type.normal, 20, "atk", -2);

        ArrayList<Attack> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);
        pikachuAtk.add(electricPunch);
        pikachuAtk.add(swordDance);

        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);
        carapuceAtk.add(toxicSpikes);
        carapuceAtk.add(stealthRock);
        carapuceAtk.add(spikes);

        ArrayList<Attack> salamecheAtk = new ArrayList<>();
        salamecheAtk.add(ember);
        ArrayList<Attack> bulbizarreAtk = new ArrayList<>();
        bulbizarreAtk.add(vineWhip);



        Pokemon pikachu = new Pokemon("pikachu",
                35, 35,
                55, 55,
                40, 40,
                50, 50,
                50, 50,
                90, 90,
                9,
                Type.electric,
                pikachuAtk,
                0, 0, 0, 0, 0,
                Status.normal, "male"
        );

        Pokemon carapuce = new Pokemon(
                44, 44, // HP et maxHP
                48, 65, // Attack et Defense
                50, 64, // Special Attack et Special Defense
                43,
                10,// Speed
                Type.water, carapuceAtk, "Carapuce", Status.normal,
                "female"
        );

        Pokemon salameche = new Pokemon(
                39, 39,
                52, 43,
                60, 50,
                65,
                11,
                Type.fire, salamecheAtk, "Salamèche", Status.normal,
                "male"
        );

        Pokemon bulbizarre = new Pokemon(
                45, 45,
                49, 49,
                65, 65,
                45,
                10,
                Type.grass, bulbizarreAtk, "Bulbizarre", Status.normal,
                "female"
        );


        Map<Items, Integer> bag = Map.of();

        Inventory inventory = new Inventory(bag, 100);

        LinkedList<Pokemon> team = new LinkedList<>();

        team.add(pikachu);
        team.add(bulbizarre);
        team.add(salameche);

        return new Player("fdp", inventory, team);
    }

    NPC initiateEnemy(){
        LinkedList<Pokemon> enemyTeam = new LinkedList<>();
        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        ArrayList<Attack> salamecheAtk = new ArrayList<>();
        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);



        Pokemon carapuce = new Pokemon(
                44, 44, // HP et maxHP
                48, 65, // Attack et Defense
                50, 64, // Special Attack et Special Defense
                43,
                10,// Speed
                Type.water, carapuceAtk, "Carapuce", Status.normal,
                "female"
        );

        Pokemon salameche = new Pokemon(
                39, 39,
                52, 43,
                60, 50,
                65,
                11,
                Type.fire, salamecheAtk, "Salamèche", Status.normal,
                "male"
        );

        enemyTeam.add(carapuce);
        enemyTeam.add(salameche);

        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);

        salamecheAtk.add(ember);
        return new NPC("fdp2", enemyTeam);
    }
}


