package Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch;

import Controller.Fight.Battle.BattleExecutor;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import Utils.SceneManager;

import java.io.IOException;

import static View.Game.Battle.BattleView.field;

public class SwitchFaintedEvent extends PlayerSwitchEvent {

    public SwitchFaintedEvent(Trainer player, Pokemon other, BattleExecutor executor) {
        super(player, other, executor);
    }
    @Override
    public void execute() throws IOException {
        BattleView.getPlayerBar().setVisible(false);
        SceneManager.switchStageTo(SceneManager.getFightView());
        player.setFront(other, field);
        BattleView.refreshSprites();
        BattleView.getPlayerBar().setPokemon(other);
        BattleView.getPlayerBar().setVisible(true);
        executor.executeEvents(()-> {
            try {
                onFinish();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
