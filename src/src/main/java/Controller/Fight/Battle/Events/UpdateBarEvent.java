package Controller.Fight.Battle.Events;

import View.Game.FightView.InfoBars.Bar;
import javafx.scene.control.Label;

public class UpdateBarEvent extends BattleEvent {
    private final Bar bar;

    public UpdateBarEvent(Bar bar) {
        this.bar = bar;
    }

    @Override
    public void execute() {
        bar.updateHPBars(this::onFinish);
    }
}
