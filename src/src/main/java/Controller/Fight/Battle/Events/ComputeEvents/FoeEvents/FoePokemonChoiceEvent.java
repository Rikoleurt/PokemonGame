package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;

public class FoePokemonChoiceEvent extends ComputeEvent<Pokemon> {
    Trainer npc;

    public FoePokemonChoiceEvent(Trainer npc) {
        this.npc = npc;
    }
    @Override
    public Pokemon compute() {
        return npc.chooseSwitchTarget();
    }
}
