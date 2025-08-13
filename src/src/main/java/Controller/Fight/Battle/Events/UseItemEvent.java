package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Model.Inventory.Items.Item;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.FightView.Text.TextBubble;

public class UseItemEvent extends BattleEvent {
    private final Player player;
    private final Item item;
    private final Pokemon target;
    private final TextBubble bubble;
    private final BattleExecutor executor;

    public UseItemEvent(Player player, Item item, Pokemon target, TextBubble bubble, BattleExecutor executor) {
        this.player = player;
        this.item = item;
        this.target = target;
        this.bubble = bubble;
        this.executor = executor;
    }

    @Override
    public void execute() {
        player.use(item, target, bubble);
        executor.executeNext(this::onFinish);
    }
}

