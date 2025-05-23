package Model.Inventory.Items.Heal;

import Model.Inventory.Items.Items;

public class Heal extends Items {

    String name;
    String description;
    int HP;

    public Heal(String name, String description, int HP) {
        this.name = name;
        this.description = description;
        this.HP = HP;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }
}
