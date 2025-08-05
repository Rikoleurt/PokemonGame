package Model.Inventory.Items.Fight;

import Model.Inventory.Items.Item;
import Model.Inventory.Items.Usable;
import Model.Pokemon.Pokemon;

public class StatBooster extends Item implements Usable {

    String name;
    String description;
    String stat;
    int raiseLevel;

    public StatBooster(String name, String description, String stat, int raiseLevel) {
        this.name = name;
        this.description = description;
        this.stat = stat;
        this.raiseLevel = raiseLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getStat() {
        return stat;
    }

    public int getRaiseLevel() {
        return raiseLevel;
    }

    @Override
    public void use(Pokemon target) {
    }
}
