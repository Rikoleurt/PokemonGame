package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Person.Action;
import Model.Person.Trainer;

public class FoeChoiceEvent extends ComputeEvent<Action> {
    Trainer npc;

    public FoeChoiceEvent(Trainer npc) {
        this.npc = npc;
    }

    @Override
    public Action compute() {
        return npc.makeChoiceAction();
    }
}
