package Controller.Fight.Battle.Events.ActionEvents.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;

import static View.Game.Battle.BattleView.terrain;

public class PlayerSwitchEvent extends BattleEvent {

    Player player;
    Pokemon other;
    BattleExecutor executor;

    public PlayerSwitchEvent(Player player, Pokemon other, BattleExecutor executor) {
        this.player = player;
        this.other = other;
        this.executor = executor;
    }

    @Override
    public void execute() {
        BattleView.getPlayerBar().setVisible(false);
        SceneManager.switchStageTo(SceneManager.getFightView());
        executor.addEvent(new MessageEvent(player.getFrontPokemon().getName() + " stop!"));
        player.setFront(other, terrain);
        BattleView.refreshSprites();
        executor.addEvent(new MessageEvent(player.getFrontPokemon().getName() + " go!"));
        BattleView.getPlayerBar().setPokemon(player.getFrontPokemon());
        BattleView.getPlayerBar().setVisible(true);
        executor.executeEvents(this::onFinish);
    }
}
