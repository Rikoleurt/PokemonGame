package Model.Inventory;

import Model.Inventory.Items.Item;

import java.util.*;

public class Bag {

    Map<Item, Integer> inventory;

    public Bag(Map<Item, Integer>inventory) {
        this.inventory = inventory;
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setItem(Item item, int quantity) {
        inventory.put(item, quantity);
    }

    public int getQuantity(Item item) {
        return inventory.get(item);
    }

    public Item getItem(Item key) {
        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
