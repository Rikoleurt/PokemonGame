package Controller.Fight.Battle.Events.ActionEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Field;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;

public class AttackEvent extends BattleEvent {

    private final Pokemon attacker;
    private final Pokemon defender;
    private final Move move;
    private final Field field;
    private final BattleExecutor executor;

    public AttackEvent(Pokemon attacker, Pokemon defender, Move move, Field field, BattleExecutor executor) {
        this.attacker = attacker;
        this.defender = defender;
        this.move = move;
        this.field = field;
        this.executor = executor;
//        System.out.println(attacker.getName() + " attacks " + defender.getName());
    }

    @Override
    public void execute() {
        if(!attacker.isKO()) attacker.attack(defender, move, field);
        executor.executeEvents(this::onFinish);
    }
}
