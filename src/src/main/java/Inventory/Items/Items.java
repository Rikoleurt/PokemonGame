package Inventory.Items;

public abstract class Items {
    String name;
    String description;
    protected abstract void addItem(int quantity);
    protected abstract void removeItem();
    protected abstract void buyItem();
}
