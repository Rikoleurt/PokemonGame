import Controller.Fight.FightController;
import View.Console.BattleLayout.BattleView;
import View.Game.FightView.FightView;
import View.Game.FightView.Text.TextBubble;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    FightView fightView = new FightView();
    TextBubble textBubble = fightView.getTextBubble();
    FightController controller = new FightController(textBubble);
    BattleView battleView = new BattleView();

    @Override
    public void start(Stage primaryStage) {
        // === Obtenir les dimensions de l'écran ===
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // === Dimensions proportionnelles ===
        double consoleWidth = screenWidth * 0.25;   // 25% de la largeur
        double gameWidth = screenWidth - consoleWidth;

        Scene gameScene = new Scene(fightView, gameWidth, screenHeight);
        Scene consoleScene = new Scene(battleView, consoleWidth, screenHeight);

        fightView.setStyle("-fx-alignment: center;");
        primaryStage.setTitle("Pokémon Game");
        controller.attachKeyHandlers(gameScene);
        primaryStage.setScene(gameScene);
        primaryStage.setX(consoleWidth);
        primaryStage.setY(0);
        primaryStage.setWidth(gameWidth);
        primaryStage.setHeight(screenHeight);
        primaryStage.show();

        Stage consoleStage = new Stage();
        consoleStage.setTitle("Battle Console");
        consoleStage.setScene(consoleScene);
        consoleStage.setX(0);
        consoleStage.setY(0);
        consoleStage.setWidth(consoleWidth);
        consoleStage.setHeight(screenHeight);
        consoleStage.show();

        gameScene.setOnKeyPressed(event -> textBubble.handleKeyPress(event.getCode()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
