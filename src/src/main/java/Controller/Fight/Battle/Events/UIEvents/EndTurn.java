package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import View.Game.Battle.BattleButtons;
import javafx.application.Platform;

import static View.Game.Battle.BattleView.npc;
import static View.Game.Battle.BattleView.player;

public class EndTurn extends BattleEvent {
    BattleButtons battleButtons;
    BattleExecutor executor;

    public EndTurn(BattleButtons battleButtons,  BattleExecutor executor) {
        this.battleButtons = battleButtons;
        this.executor = executor;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> {
            battleButtons.resetFightButtons(getClass().getSimpleName());
            battleButtons.requestFocus();
        });
        if(npc.getHealthyPokemon() == 0){
            executor.addEvent(new MessageEvent(npc.getName() + " has been defeated."));
            executor.executeNext(this::onFinish);
        } else if(player.getHealthyPokemon() == 0) {
            executor.addEvent(new MessageEvent(player.getName() + " is out of usable Pokémon."));
            executor.addEvent(new MessageEvent(player.getName() + " scurried to a Pokémon Center, protecting the exhausted and fainted Pokémon from further harm"));
            executor.executeNext(this::onFinish);
        }
        executor.executeNext(this::onFinish);
    }
}
