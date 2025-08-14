package View.Game.Battle.InfoBars;

import Model.Pokemon.Pokemon;
import javafx.geometry.Pos;

public class PlayerBar extends Bar {

    public PlayerBar(double spacing, Pokemon p) {
        super(spacing, p);
        setAlignment(Pos.BOTTOM_LEFT);
    }
}