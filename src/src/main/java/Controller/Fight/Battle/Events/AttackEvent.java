package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.Text.TextBubble;

public class AttackEvent extends BattleEvent{

    private final Pokemon attacker;
    private final Pokemon defender;
    private final Move move;
    private final Terrain terrain;
    private final TextBubble bubble;
    private final BattleExecutor executor;

    public AttackEvent(Pokemon attacker, Pokemon defender, Move move, Terrain terrain, TextBubble bubble, BattleExecutor executor) {
        this.attacker = attacker;
        this.defender = defender;
        this.move = move;
        this.terrain = terrain;
        this.bubble = bubble;
        this.executor = executor;
    }

    @Override
    public void execute() {
        attacker.attack(defender, move, terrain, bubble);
        executor.executeNext(this::onFinish);
    }
}
