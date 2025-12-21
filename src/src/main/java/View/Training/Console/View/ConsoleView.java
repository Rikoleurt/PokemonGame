package View.Training.Console.View;

import javafx.scene.layout.BorderPane;

public class ConsoleView extends BorderPane {

    BattleConsole battleConsole = BattleConsole.getInstance();

    public ConsoleView() {
        setCenter(battleConsole);
    }
}
