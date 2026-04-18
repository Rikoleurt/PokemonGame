package Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch;

import Controller.Fight.Battle.BattleExecutor;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import Utils.SceneManager;

import static View.Game.Battle.BattleView.field;

public class SwitchFaintedEvent extends PlayerSwitchEvent {

    public SwitchFaintedEvent(Trainer player, Pokemon other, BattleExecutor executor) {
        super(player, other, executor);
    }
    @Override
    public void execute(){
        BattleView.getPlayerBar().setVisible(false);
        SceneManager.switchStageTo(SceneManager.getFightView());
        player.setFront(other, field);
        BattleView.refreshSprites();
        BattleView.getPlayerBar().setPokemon(other);
        BattleView.getPlayerBar().setVisible(true);
        executor.executeEvents(this::onFinish);
    }
}
