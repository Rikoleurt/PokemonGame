import Controller.Fight.FightController;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Meteo;
import Model.StaticObjects.Player;
import View.FightView.FightView;

import View.FightView.Text.TextBubble;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import static Model.StaticObjects.NPC.initiateEnemy;
import static Model.StaticObjects.Player.initiatePlayer;
import static Model.StaticObjects.Pokemons.initiatePikachu;
import static Model.StaticObjects.Pokemons.initiateSalameche;

public class Main extends Application {

    FightView fightView = new FightView();
    TextBubble textBubble = fightView.getTextBubble();
    Scene scene = new Scene(fightView, 1980, 1080);
    FightController controller = new FightController(textBubble);


    @Override
    public void start(Stage stage){

        fightView.setStyle("-fx-alignment: center;");
        stage.setTitle("Model.Pokemon Fight");
        controller.attachKeyHandlers(scene);
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();

        scene.setOnKeyPressed(event -> {
            textBubble.handleKeyPress(event.getCode());
        });

    }

    public static void main(String[] args) {
        launch(args);

    }
}
