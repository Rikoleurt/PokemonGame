package View.FightView;

import com.sun.tools.javac.Main;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class FightButtons extends HBox {

    static Font font = Font.loadFont(FightButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Button runButton = createButton("Run");
    Button fightButton = createButton("Fight");
    Button bagButton = createButton("Bag");
    Button pokemonButton = createButton("Pokemon");

    public FightButtons() {
        ObservableList<Node> buttons = this.getChildren();

        VBox buttonBox = new VBox();

        HBox HBox1 = new HBox(fightButton, bagButton);
        HBox HBox2 = new HBox(runButton, pokemonButton);

        buttonBox.getChildren().addAll(HBox1, HBox2);
        buttons.add(buttonBox);

        buttonBox.setAlignment(Pos.CENTER);

        HBox1.setSpacing(5);
        HBox2.setSpacing(5);
        buttonBox.setSpacing(5);

        setPadding(new Insets(20));
        setAlignment(Pos.BOTTOM_RIGHT);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(font);
        button.setPrefSize(175, 75);
        button.setStyle("-fx-background-color: white; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 0px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: black; ");
        return button;
    }

    private void fightButtonAction(Button button) {
        button.setOnAction(e -> {
        });
    }
}
