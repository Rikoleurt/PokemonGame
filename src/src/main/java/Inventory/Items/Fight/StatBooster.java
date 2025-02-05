package Inventory.Items.Fight;

import Inventory.Items.Items;

public class StatBooster extends Items {

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
}
