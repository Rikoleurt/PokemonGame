package Controller.Fight.Battle.Events.ActionEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;

public class AttackEvent extends BattleEvent {

    private final Pokemon attacker;
    private final Pokemon defender;
    private final Move move;
    private final Terrain terrain;
    private final BattleExecutor executor;

    public AttackEvent(Pokemon attacker, Pokemon defender, Move move, Terrain terrain, BattleExecutor executor) {
        this.attacker = attacker;
        this.defender = defender;
        this.move = move;
        this.terrain = terrain;
        this.executor = executor;
        System.out.println(attacker.getName() + " attacks " + defender.getName());
    }

    @Override
    public void execute() {
        attacker.attack(defender, move, terrain);
        executor.executeNext(this::onFinish);
    }
}
