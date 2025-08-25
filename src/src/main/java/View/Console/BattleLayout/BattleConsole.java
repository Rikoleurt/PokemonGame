package View.Console.BattleLayout;

import View.Game.Battle.BattleButtons;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.util.Objects;

/**
 * This console allows the user to see what data the AI will use to make decisions
 */
public class BattleConsole extends TextArea {

    private static BattleConsole instance;
    private static final Font font = Font.loadFont(Objects.requireNonNull(BattleButtons.class.getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);

    private BattleConsole() {
        setEditable(false);
        setWrapText(true);
        setPrefRowCount(6);
        setFocusTraversable(false);
        setFont(font);
        //setStyle("-fx-font-family: 'monospace'; -fx-font-size: 12;");
    }

    public static BattleConsole getInstance() {
        if (instance == null) {
            instance = new BattleConsole();
        }
        return instance;
    }

    /**
     * Prints onto the console the chosen message
     * @param message The message to print
     */
    public void log(String message) {
        appendText(message + "\n");
        positionCaret(getLength());
    }
}
