package View.Console.BattleLayout;

import Model.Person.NPC;
import Model.Person.Player;
import javafx.scene.layout.BorderPane;

public class BattleView extends BorderPane {

    BattleConsole battleConsole = BattleConsole.getInstance();

    public BattleView() {
        setCenter(battleConsole);
    }
}
