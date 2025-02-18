import View.FightView.FightView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    static Font font = Font.loadFont(Main.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 12);

    @Override
    public void start(Stage stage) throws Exception {
        FightView fightView = new FightView();
        Scene scene = new Scene(fightView, 1980, 1080);
        fightView.setStyle("-fx-alignment: center;");
        stage.setTitle("Pokemon Fight");
        stage.setScene(scene);
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
