package View.FightView.InfoBars;

import Model.Pokemon.Pokemon;
import View.FightView.Text.TextBubble;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.util.Collection;

public class PlayerBar extends Bar {

    public PlayerBar(double spacing, TextBubble bubble, Pokemon p) {
        super(spacing, bubble, p);
        setAlignment(Pos.BOTTOM_LEFT);

    }
}