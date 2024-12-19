package Inventory.Items.Heal;

import Inventory.Inventory;
import Inventory.Items.Items;

public class Potion extends Items {

    Inventory inventory;
    String name;
    String description;
    int HP;

    Potion(String name, String description, int HP) {
        this.name = name;
        this.description = description;
        this.HP = HP;
    }

    @Override
    protected void addItem(int quantity) {
        inventory.getInventory().put(new Potion(name,description,HP), quantity);
    }

    @Override
    protected void removeItem() {
        inventory.getInventory().remove(new Potion(name,description,HP));
    }

    @Override
    protected void buyItem() {

    }
}
