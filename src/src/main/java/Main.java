import Controller.Fight.FightController;
import View.FightView.FightView;

import View.FightView.Text.TextBubble;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

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
