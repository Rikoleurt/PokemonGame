package Inventory;

import Inventory.Items.Items;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Inventory {

    Map<Items, Integer> inventory;
    long money;

    public Inventory(Map<Items, Integer> inventory, long money) {
        this.inventory = inventory;
        this.money = money;
    }

    public Map<Items, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Items, Integer> inventory) {
        this.inventory = inventory;
    }
}
