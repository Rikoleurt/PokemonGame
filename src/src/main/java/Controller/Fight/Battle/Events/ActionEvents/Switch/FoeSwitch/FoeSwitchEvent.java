package Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Field;
import View.GameView.BattleViews.BattleView;

import java.io.IOException;

public class FoeSwitchEvent extends BattleEvent {

    private final Trainer npc;
    private final Pokemon other;
    private final Field field;

    public FoeSwitchEvent(Trainer npc, Pokemon other, Field field) {
        this.npc = npc;
        this.other = other;
        this.field = field;
    }

    @Override
    public void execute() throws IOException {
        npc.setFront(other, field);
        BattleView.refreshSprites();
        BattleView.getOpponentBar().setPokemon(npc.getFrontPokemon());
        BattleView.getFightButtons().resetFightButtons(getClass().getSimpleName());
        BattleView.getOpponentBar().refreshBar();
        onFinish();
    }
}
