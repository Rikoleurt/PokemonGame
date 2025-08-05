package Model.Inventory.Items;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.FightView.Text.TextBubble;

public interface Usable {
    void use(Pokemon target, TextBubble textBubble);
}
