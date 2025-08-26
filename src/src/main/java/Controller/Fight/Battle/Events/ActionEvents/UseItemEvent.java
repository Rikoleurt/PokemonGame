package Controller.Fight.Battle.Events.ActionEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Model.Inventory.Items.Item;
import Model.Person.Fighter;
import Model.Pokemon.Pokemon;

public class UseItemEvent extends BattleEvent {
    private final Fighter fighter;
    private final Item item;
    private final Pokemon target;
    private final BattleExecutor executor;

    public UseItemEvent(Fighter fighter, Item item, Pokemon target, BattleExecutor executor) {
        this.fighter = fighter;
        this.item = item;
        this.target = target;

        this.executor = executor;
    }

    @Override
    public void execute() {
        fighter.use(item, target);
        executor.executeNext(this::onFinish);
    }
}

