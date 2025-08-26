package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Person.Action;
import Model.Person.NPC;

public class FoeChoiceEvent extends ComputeEvent<Action> {
    NPC npc;

    public FoeChoiceEvent(NPC npc) {
        this.npc = npc;
    }

    @Override
    public Action compute() {
        return npc.makeChoiceAction();
    }
}
