package View.FightView;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class FightView extends BorderPane {
    FightButtons fightButtons = new FightButtons();
    PlayerHPBar playerBar = new PlayerHPBar(10);

    public FightView() {
        setBottom(fightButtons);
        setRight(playerBar);

        setAlignment(playerBar, Pos.BOTTOM_CENTER);
        setAlignment(fightButtons, Pos.BOTTOM_RIGHT);
    }
}
