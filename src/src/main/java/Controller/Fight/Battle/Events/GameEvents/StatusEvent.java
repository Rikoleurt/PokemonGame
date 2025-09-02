package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Pokemon;

public class StatusEvent extends BattleEvent {
    private final Pokemon pokemon;

    public StatusEvent(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void execute() {
        pokemon.registerStatusEffect();
        onFinish();
    }
}
