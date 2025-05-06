import Person.NPC;
import Person.Player;
import Pokemon.Pokemon;
import View.FightView.FightView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

import static Pokemon.Pokemon.getBaseExperience;
import static StaticObjects.NPC.initiateEnemy;
import static StaticObjects.Player.initiatePlayer;


public class Main extends Application {

    static Font font = Font.loadFont(Main.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 12);
    FightView fightView = new FightView();
    Scene scene = new Scene(fightView, 1980, 1080);
    @Override
    public void start(Stage stage){

        fightView.setStyle("-fx-alignment: center;");
        stage.setTitle("Pokemon Fight");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        Player player = initiatePlayer();
        NPC npc = initiateEnemy();

        Pokemon p = player.getFrontPokemon();
        Pokemon p2 = npc.getFrontPokemon();
        Pokemon p3 = npc.getTeam().get(1);

        System.out.println(p.getName() + " gets " + p.calculateEXP(p2) + " exp from " + p2.getName());
//        System.out.println(getBaseExperience(p.getName(), "/Users/condreajason/Repositories/PokemonGame/src/src/main/resources/data/pokemon.csv"));
        System.out.println(p.getName() + " " + getBaseExperience(p.getName(), "C:\\dev\\gitproject\\PokemonGame\\src\\src\\main\\resources\\data\\pokemon.csv"));
        System.out.println(p2.getName() + " " + getBaseExperience(p2.getName(), "C:\\dev\\gitproject\\PokemonGame\\src\\src\\main\\resources\\data\\pokemon.csv"));

    }
    private static int generateIV(){
        Random rand = new Random();
        return rand.nextInt(0,32);
    }
}
