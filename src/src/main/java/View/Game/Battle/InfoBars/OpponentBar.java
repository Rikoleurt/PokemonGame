package View.Game.Battle.InfoBars;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import javafx.scene.control.Label;

public class OpponentBar extends Bar {

    public OpponentBar(double spacing, Pokemon p) {
        super(spacing, p);

        String pName = p.getName();
        int pLevel = p.getLevel();
        String pStatus = p.getStatus().toString();

        Label name = new Label(pName);
        Label HP = new Label("HP :");
        Label level = new Label("Lvl : " + pLevel);
        Label status = new Label(pStatus);

        name.setFont(font);
        HP.setFont(font);
        level.setFont(font);
        status.setFont(font);
    }
}

