package Model.Inventory.Items;

import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;

public interface Consumable {
    void consume(Pokemon target, TextBubble textBubble);
}
