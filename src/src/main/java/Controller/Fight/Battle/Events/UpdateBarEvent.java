package Controller.Fight.Battle.Events;

import View.Game.Battle.InfoBars.Bar;

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
