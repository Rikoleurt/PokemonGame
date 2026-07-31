package Model.Person;

import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;

public interface Fighter {
    void use(Item item, Pokemon target);
    void setAction(Action action);
}

