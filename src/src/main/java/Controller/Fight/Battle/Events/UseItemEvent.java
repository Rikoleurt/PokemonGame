package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Model.Inventory.Items.Item;
import Model.Person.Fighter;
import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;

public class UseItemEvent extends BattleEvent {
    private final Fighter fighter;
    private final Item item;
    private final Pokemon target;
    private final TextBubble bubble;
    private final BattleExecutor executor;

    public UseItemEvent(Fighter fighter, Item item, Pokemon target, TextBubble bubble, BattleExecutor executor) {
        this.fighter = fighter;
        this.item = item;
        this.target = target;
        this.bubble = bubble;
        this.executor = executor;
    }

    @Override
    public void execute() {
        fighter.use(item, target, bubble);
        executor.executeNext(this::onFinish);
    }
}

