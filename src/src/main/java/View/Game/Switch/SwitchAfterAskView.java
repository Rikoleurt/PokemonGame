package View.Game.Switch;

import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitchEvent;
import Controller.Fight.Battle.Events.UIEvents.EndTurn;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import javafx.scene.layout.HBox;

public class SwitchAfterAskView extends SwitchView {
    BattleButtons battleButtons;
    Pokemon otherNpcPokemon;
    public SwitchAfterAskView(Player player, NPC npc, TextBubble textBubble, BattleButtons battleButtons, Pokemon otherNpcPokemon, Runnable onClose) {
        super(player, npc, textBubble, onClose);
        this.battleButtons = battleButtons;
        this.otherNpcPokemon = otherNpcPokemon;
    }
    @Override
    protected void handleSwitch(Pokemon pokemon){
        executor.addEvent(new PlayerSwitchEvent(player, pokemon, executor));
        executor.addEvent(new FoeSwitchEvent(npc, otherNpcPokemon, BattleView.getTerrain(), executor, battleButtons));
        executor.executeNext(()-> {
            executor.addEvent(new EndTurn(battleButtons, executor));
            executor.executeNext(null);
        });
    }
}
