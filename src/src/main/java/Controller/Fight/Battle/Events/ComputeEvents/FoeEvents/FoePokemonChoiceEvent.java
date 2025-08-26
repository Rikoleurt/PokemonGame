package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;

public class FoePokemonChoiceEvent extends ComputeEvent<Pokemon> {
    NPC npc;

    public FoePokemonChoiceEvent(NPC npc) {
        this.npc = npc;
    }
    @Override
    public Pokemon compute() {
        return npc.chooseSwitchTarget();
    }
}
