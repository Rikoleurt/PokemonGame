package Controller.Fight.Battle.Events;

import View.FightView.InfoBars.Bar;
import javafx.scene.control.Label;

public class UpdateBarEvent extends BattleEvent {
    private final Bar bar;
    private final Label label;

    public UpdateBarEvent(Bar bar, Label label) {
        this.bar = bar;
        this.label = label;
    }

    @Override
    public void execute() {
        bar.updateHPBars(label, this::onFinish);
    }
}
