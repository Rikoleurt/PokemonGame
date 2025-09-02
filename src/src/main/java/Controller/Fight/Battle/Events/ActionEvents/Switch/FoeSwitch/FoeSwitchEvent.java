package Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.Battle.BattleView;

public class FoeSwitchEvent extends BattleEvent {

    private final NPC npc;
    private final Pokemon other;
    private final Terrain terrain;

    public FoeSwitchEvent(NPC npc, Pokemon other, Terrain terrain) {
        this.npc = npc;
        this.other = other;
        this.terrain = terrain;
    }

    @Override
    public void execute() {
        npc.setFront(other, terrain);
        BattleView.refreshSprites();
        BattleView.getOpponentBar().setPokemon(npc.getFrontPokemon());
        BattleView.getFightButtons().resetFightButtons(getClass().getSimpleName());
        BattleView.getOpponentBar().refreshBar();
    }
}
