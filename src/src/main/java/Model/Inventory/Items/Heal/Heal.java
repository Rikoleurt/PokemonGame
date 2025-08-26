package Model.Inventory.Items.Heal;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Controller.Fight.Battle.Events.UIEvents.UpdateBarEvent;
import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Consumable;
import Model.Pokemon.Pokemon;

public class Heal extends Item implements Consumable {

    int HP;
    BattleExecutor executor = BattleExecutor.getInstance();

    public Heal(Category category, String name, String description, int HP) {
        super(category, name, description);
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    @Override
    public void consume(Pokemon target) {
        if (target.getHP() == target.getMaxHP()) {
            executor.addEvent(new MessageEvent(target.getName() + " is already in top form!"));
            System.out.println(target.getName() + " is already in top form!");
            return;
        }
        int newHP = Math.min(target.getHP() + HP, target.getMaxHP());
        target.setHP(newHP);
        executor.addEvent(new UpdateBarEvent(target));
        executor.addEvent(new MessageEvent(target.getName() + " regained some health!"));
    }


    @Override
    public boolean isUsable() {
        return true;
    }
}
