package View.Console.BattleLayout;

import Model.Pokemon.Pokemon;
import View.Game.FightView.InfoBars.Bar;
import View.Game.FightView.InfoBars.OpponentBar;
import View.Game.FightView.InfoBars.PlayerBar;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static View.Game.FightView.FightView.npc;
import static View.Game.FightView.FightView.player;

public class BattleView extends BorderPane {
    BattleConsole battleConsole = BattleConsole.getInstance();

    public BattleView() {
        setCenter(battleConsole);
    }
}
