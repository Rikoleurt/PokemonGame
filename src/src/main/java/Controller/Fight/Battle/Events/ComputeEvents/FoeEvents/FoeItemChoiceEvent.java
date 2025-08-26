package Controller.Fight.Battle.Events.ComputeEvents.FoeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Inventory.Items.Item;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;

public class FoeItemChoiceEvent extends ComputeEvent<Item> {
    NPC npc;
    Pokemon pokemon;

    public FoeItemChoiceEvent(NPC npc) {
        this.npc = npc;
        this.pokemon = npc.getFrontPokemon();
    }
    @Override
    public Item compute() {
        return npc.itemChoice(pokemon);
    }
}
