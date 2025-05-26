package View.FightView.InfoBars;

import View.FightView.Text.TextBubble;
import Model.Pokemon.Pokemon;
import javafx.scene.control.Label;

public class OpponentBar extends Bar {

    public OpponentBar(double spacing, TextBubble bubble, Pokemon p) {
        super(spacing, bubble, p);

        String pName = p.getName();
        int pLevel = p.getLevel();
        int pHP = p.getHP();
        int pMaxHP = p.getMaxHP();
        String pStatus = p.getStatus().toString();

        Label name = new Label(p.getName());
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

