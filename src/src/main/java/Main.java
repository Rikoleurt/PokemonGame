import Pokemon.Pokemon;
import Pokemon.Type;
import Pokemon.Attack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static Pokemon.Pokemon.useAttack;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
    }

    public static void main(String[] args) {
        Attack attack = new Attack("Charge", 40, Type.normal, "physic", 40);
        Pokemon pok1 = new Pokemon(40,40,20,22,17, 5, "Pikachu", Type.electric, attack);
        Pokemon pok2 = new Pokemon(50,50,20,22,17, 5, "Pikachu", Type.electric, attack);
        System.out.println("COMBAT");

        useAttack(pok2, pok1.getAttack());

    }
}

// YEAHHHH

