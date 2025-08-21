package Model.Inventory.Items.Fight;

import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Consumable;
import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;

public class StatBooster extends Item implements Consumable {

    String stat;
    int raiseLevel;

    public StatBooster(Category category, String name, String description, String stat, int raiseLevel) {
        super(category, name, description);
        this.stat = stat;
        this.raiseLevel = raiseLevel;
    }

    public String getStat() {
        return stat;
    }

    public int getRaiseLevel() {
        return raiseLevel;
    }

    @Override
    public void consume(Pokemon target) {
    }

    @Override
    public boolean isUsable() {
        return true;
    }
}
