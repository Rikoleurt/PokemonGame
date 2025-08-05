package Model.Inventory;

import Model.Inventory.Items.Item;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Bag {

    Map<Item, Integer> inventory;
    long money;

    public Bag(Map<Item, Integer> inventory, long money) {
        this.inventory = inventory;
        this.money = money;
    }

    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Item, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setItem(Item item, int amount) {
        this.inventory.put(item, amount);
    }

    public void removeItem(Item item) {
        this.inventory.remove(item);
    }

    public Item getItems(){
        return inventory.keySet().iterator().next();
    }

    public void removeMultipleItems(int amount){
        Set<Item> itemSet = inventory.keySet();
        Iterator<Item> itr = itemSet.iterator();
        if(amount <= itemSet.size()) {
            for (int i = 0; i < amount; i++) {
                itemSet.remove(itr.next());
            }
        }
    }

    public long getMoney() {
        return money;
    }
}
