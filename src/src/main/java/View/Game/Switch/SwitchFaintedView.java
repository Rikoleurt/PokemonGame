package View.Game.Switch;

import Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch.SwitchFaintedEvent;
import Controller.Fight.Battle.Events.GameEvents.EndTurn;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import static View.Game.Battle.BattleView.terrain;

public class SwitchFaintedView extends SwitchView {
    BattleButtons battleButtons;

    public SwitchFaintedView(Player player, NPC npc, TextBubble textBubble, BattleButtons battleButtons, Runnable onClose) {
        super(player, npc, textBubble, onClose);
        this.battleButtons = battleButtons;

        cancelButton.setVisible(false);
    }

    @Override
    protected void handleSwitch(Pokemon pokemon) {
        BattleView.refreshSprites();
        if (pokemon.isKO()) {
            switchBubble.setVisible(true);
            switchBubble.showMessage(pokemon.getName() + " is not it its best shape... You can not switch.");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(ev -> switchBubble.setVisible(false));
            delay.play();
            return;
        }
        if(isTurnDisable){
            executor.addEvent(new SwitchFaintedEvent(player, pokemon, executor));
            executor.addEvent(new MessageEvent(pokemon.getName() + " go!"));
            executor.executeEvents(()->{
                new EndTurn(executor).onFinish();
            });

        }
    }
}
