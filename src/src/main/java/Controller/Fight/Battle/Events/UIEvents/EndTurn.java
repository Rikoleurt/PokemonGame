package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import View.Game.Battle.BattleButtons;
import javafx.application.Platform;

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
            battleButtons.resetFightButtons();
            battleButtons.requestFocus();
        });
        executor.executeNext(this::onFinish);
    }
}
