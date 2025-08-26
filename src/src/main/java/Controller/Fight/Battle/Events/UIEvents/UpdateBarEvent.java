package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import javafx.application.Platform;

public class UpdateBarEvent extends BattleEvent {
    Pokemon pokemon;

    public UpdateBarEvent(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            Bar bar = resolveBar(pokemon);
            bar.updateHPBars(this::onFinish);
        });
    }

    private Bar resolveBar(Pokemon p){
        if (p == BattleView.getPlayer().getFrontPokemon()) return BattleView.getPlayerBar();
        System.out.println(p.getName() + ", hp = " + p.getHP());
        return BattleView.getOpponentBar();
    }
}
