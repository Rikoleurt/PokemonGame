package View.Game.Battle.InfoBars;

import Model.Pokemon.Pokemon;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

import static View.Game.Battle.BattleView.player;

public class PlayerBar extends Bar {

    public PlayerBar(double spacing, Pokemon p) {
        super(spacing, p);
        setAlignment(Pos.BOTTOM_LEFT);
    }
}