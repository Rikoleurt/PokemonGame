package Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import View.GameView.BattleViews.BattleView;
import Utils.SceneManager;

import java.io.IOException;

import static View.GameView.BattleViews.BattleView.field;

public class PlayerSwitchEvent extends BattleEvent {

    Trainer player;
    Pokemon other;
    BattleExecutor executor;

    public PlayerSwitchEvent(Trainer player, Pokemon other, BattleExecutor executor) {
        this.player = player;
        this.other = other;
        this.executor = executor;
    }

    @Override
    public void execute() throws IOException {
        BattleView.getPlayerBar().setVisible(false);
        SceneManager.switchStageTo(SceneManager.getFightView());
        executor.addEvent(new MessageEvent(player.getFrontPokemon().getName() + " stop!"));
        player.setFront(other, field);
        BattleView.refreshSprites();
        executor.addEvent(new MessageEvent(player.getFrontPokemon().getName() + " go!"));
        BattleView.getPlayerBar().setPokemon(player.getFrontPokemon());
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
