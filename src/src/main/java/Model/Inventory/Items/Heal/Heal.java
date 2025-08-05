package Model.Inventory.Items.Heal;

import Model.Inventory.Items.Item;
import Model.Inventory.Items.Usable;
import Model.Pokemon.Pokemon;

public class Heal extends Item implements Usable {

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

    @Override
    public void use(Pokemon target) {

        if (target.getHP() == target.getMaxHP()) {
            System.out.println(target.getName() + " is already in top form!");
            return;
        }

        int newHP = Math.min(target.getHP() + HP, target.getMaxHP());
        target.setHP(newHP);
        System.out.println(target.getName() + " regained some health!");
    }
}
