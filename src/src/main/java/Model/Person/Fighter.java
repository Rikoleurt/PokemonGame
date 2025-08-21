package Model.Person;

import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;

public interface Fighter {
    void use(Item item, Pokemon target);
}

