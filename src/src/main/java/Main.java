import View.FightView.FightView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application {

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

//        fightView.requestFocus();
//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.A) {
//                fightView.displayNextMessage();
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
