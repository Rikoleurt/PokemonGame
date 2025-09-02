package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import javafx.application.Platform;

public class UpdateBarEvent extends BattleEvent {
    Pokemon pokemon;
    int currentHP;

    public UpdateBarEvent(Pokemon pokemon, int currentHP) {
        this.pokemon = pokemon;
        this.currentHP = currentHP;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Bar bar = resolveBar(pokemon);
            bar.updateHPBars(currentHP, this::onFinish);
        });
    }

    private Bar resolveBar(Pokemon p){
        if (p == BattleView.getPlayer().getFrontPokemon()) return BattleView.getPlayerBar();
        return BattleView.getOpponentBar();
    }
}
