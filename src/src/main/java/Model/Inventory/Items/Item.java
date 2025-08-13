package Model.Inventory.Items;

import Model.Inventory.Category;

public abstract class Item {
    String name;
    String description;
    Category category;

    public Item(Category category, String name, String description) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public abstract boolean isUsable();
}
