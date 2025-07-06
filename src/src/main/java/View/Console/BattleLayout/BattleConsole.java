package View.Console.BattleLayout;

import View.Game.FightView.FightButtons;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
public class BattleConsole extends TextArea {

    private static BattleConsole instance;
    private static Font font = Font.loadFont(FightButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

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

    public void log(String message) {
        appendText(message + "\n");
        positionCaret(getLength());
    }
}
