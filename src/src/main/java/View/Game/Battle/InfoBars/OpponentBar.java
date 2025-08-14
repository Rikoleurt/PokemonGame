package View.Game.Battle.InfoBars;

import Model.Pokemon.Pokemon;
import javafx.scene.control.Label;

public class OpponentBar extends Bar {

    public OpponentBar(double spacing, Pokemon p) {
        super(spacing, p);

        String pName = p.getName();
        int pLevel = p.getLevel();
        int pHP = p.getHP();
        int pMaxHP = p.getMaxHP();
        String pStatus = p.getStatus().toString();

        Label name = new Label(pName);
        Label HP = new Label("HP :");
        Label level = new Label("Lvl : " + pLevel);
        Label HPs = new Label(pHP + "/" + pMaxHP);
        Label status = new Label(pStatus);

        name.setFont(font);
        HP.setFont(font);
        level.setFont(font);
        HPs.setFont(font);
        status.setFont(font);
    }
}

