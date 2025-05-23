import Model.Person.Player;
import View.FightView.FightView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;


public class Main extends Application {
    
    static Font font = Font.loadFont(Main.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 12);
    FightView fightView = new FightView();
    Scene scene = new Scene(fightView, 1980, 1080);
    @Override
    public void start(Stage stage){

        fightView.setStyle("-fx-alignment: center;");
        stage.setTitle("Model.Pokemon Fight");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    private static int generateIV(){
        Random rand = new Random();
        return rand.nextInt(0,32);
    }
}
