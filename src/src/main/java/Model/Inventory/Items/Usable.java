package Model.Inventory.Items;

import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;

public interface Usable {
    void use(Pokemon target, TextBubble textBubble);
}
