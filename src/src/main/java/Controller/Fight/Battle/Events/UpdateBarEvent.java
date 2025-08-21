package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;

public class UpdateBarEvent extends BattleEvent implements Event {
    Pokemon pokemon;

    public UpdateBarEvent(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public void execute() {
        Bar bar = resolveBar(pokemon);
        bar.updateHPBars(this::onFinish);
    }

    private Bar resolveBar(Pokemon p){
        if (p == BattleView.getPlayer().getFrontPokemon()) return BattleView.getPlayerBar();
        return BattleView.getOpponentBar();
    }
}
