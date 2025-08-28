package View.Game.Switch;

import Controller.Fight.Battle.Events.ActionEvents.PlayerSwitchEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class SwitchKOView extends SwitchView {
    BattleButtons battleButtons;
    public SwitchKOView(Player player, NPC npc, TextBubble textBubble, BattleButtons battleButtons, Runnable onClose) {
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
            executor.addEvent(new PlayerSwitchEvent(player, pokemon, executor));
            executor.executeNext(battleButtons::resetFightButtons);
        }
    }
}
