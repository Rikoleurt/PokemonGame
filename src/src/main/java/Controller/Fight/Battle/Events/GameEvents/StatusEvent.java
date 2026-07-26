package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Pokemon;

import java.io.IOException;

public class StatusEvent extends BattleEvent {
    private final Pokemon pokemon;

    public StatusEvent(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void execute() throws IOException {
        pokemon.statusEffectAtEnd();
        onFinish();
    }
}
