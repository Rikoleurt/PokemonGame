package Inventory;

import Inventory.Items.Items;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Bag {

    Map<Items, Integer> inventory;
    long money;

    public Bag(Map<Items, Integer> inventory, long money) {
        this.inventory = inventory;
        this.money = money;
    }

    public Map<Items, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Items, Integer> inventory) {
        this.inventory = inventory;
    }

    public void setItem(Items item, int amount) {
        this.inventory.put(item, amount);
    }
    public void removeItem(Items item) {
        this.inventory.remove(item);
    }
    public Items getItems(){
        return inventory.keySet().iterator().next();
    }
    public void removeMultipleItems(int amount){
        Set<Items> itemsSet = inventory.keySet();
        Iterator<Items> itr = itemsSet.iterator();
        if(amount <= itemsSet.size()) {
            for (int i = 0; i < amount; i++) {
                itemsSet.remove(itr.next());
            }
        }
    }

    public long getMoney() {
        return money;
    }
}
