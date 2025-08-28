package Controller.Fight.Battle.Events.ActionEvents.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;

import static View.Game.Battle.BattleView.terrain;

public class ForcePlayerSwitchEvent extends PlayerSwitchEvent {

    public ForcePlayerSwitchEvent(Player player, Pokemon other, BattleExecutor executor) {
        super(player, other, executor);
    }
    @Override
    public void execute(){
        BattleView.getPlayerBar().setVisible(false);
        SceneManager.switchStageTo(SceneManager.getFightView());
        player.setFront(other, terrain);
        BattleView.refreshSprites();
        executor.addEvent(new MessageEvent(player.getFrontPokemon().getName() + " go!"));
        BattleView.getPlayerBar().setPokemon(player.getFrontPokemon());
        BattleView.getPlayerBar().setVisible(true);
        executor.executeNext(this::onFinish);
    }
}
