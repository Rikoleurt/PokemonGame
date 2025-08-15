package Model.Inventory.Items.Heal;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.MessageEvent;
import Controller.Fight.Battle.Events.UpdateBarEvent;
import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Usable;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;

public class Heal extends Item implements Usable {

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
    public void use(Pokemon target, TextBubble textBubble) {
        if (target.getHP() == target.getMaxHP()) {
            executor.addEvent(new MessageEvent(textBubble, target.getName() + " is already in top form!"));
            System.out.println(target.getName() + " is already in top form!");
            return;
        }
        int newHP = Math.min(target.getHP() + HP, target.getMaxHP());
        target.setHP(newHP);
        System.out.println(target.getName() + " regained some health!");
        if (View.Game.Battle.BattleView.getPlayer().getTeam().contains(target)) {
            executor.addEvent(new UpdateBarEvent(View.Game.Battle.BattleView.getPlayerBar()));
        } else {
            executor.addEvent(new UpdateBarEvent(View.Game.Battle.BattleView.getOpponentBar()));
        }
        executor.addEvent(new MessageEvent(textBubble, target.getName() + " regained some health!"));
    }


    @Override
    public boolean isUsable() {
        return true;
    }
}
