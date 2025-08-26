package Controller.Fight.Battle.Events.ActionEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.Battle.BattleView;

public class FoeSwitchEvent extends BattleEvent {

    private final NPC npc;
    private final Pokemon other;
    private final Terrain terrain;
    private final BattleExecutor executor;

    public FoeSwitchEvent(NPC npc, Pokemon other, Terrain terrain, BattleExecutor executor) {
        this.npc = npc;
        this.other = other;
        this.terrain = terrain;
        this.executor = executor;
    }

    @Override
    public void execute() {
        executor.addEvent(new MessageEvent(npc.getFrontPokemon().getName() + " stop!"));
        npc.setFront(other, terrain);
        BattleView.refreshSprites();
        executor.addEvent(new MessageEvent(npc.getFrontPokemon().getName() + " go!"));
        BattleView.getOpponentBar().setPokemon(npc.getFrontPokemon());
    }
}
