package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;

public class FoeAttackChoiceEvent extends ComputeEvent<Move> {
    Pokemon npc;

    public FoeAttackChoiceEvent(Pokemon npc) {
        this.npc = npc;
    }
    @Override
    public Move compute() {
        return npc.chooseMove();
    }
}
