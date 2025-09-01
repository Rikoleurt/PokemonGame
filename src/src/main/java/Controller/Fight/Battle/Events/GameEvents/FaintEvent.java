package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;
import View.Game.Switch.SwitchKOView;

import static View.Game.Battle.BattleView.*;

public class FaintEvent extends BattleEvent {

    Player player;
    NPC npc;
    Pokemon pokemon;
    BattleExecutor executor;
    BattleButtons battleButtons;

    public FaintEvent(Player player, NPC npc, Pokemon pokemon, BattleExecutor executor, BattleButtons battleButtons) {
        this.player = player;
        this.npc = npc;
        this.pokemon = pokemon;
        this.executor = executor;
        this.battleButtons = battleButtons;
    }

    @Override
    public void execute(){
        executor.addEvent(new MessageEvent(pokemon.getName() + " fainted."));
        Pokemon next = npc.chooseSwitchTarget();
        if(next != null){
            executor.addEvent(new MessageEvent(npc.getName() + " sends " + next.getName() + "!"));
            executor.addEvent(new FoeSwitchEvent(npc, next, terrain));
        }
        if(player.getTeam().contains(pokemon) && player.getHealthyPokemon() > 0){
            executor.executeEvents(()->{
                SwitchKOView switchView = new SwitchKOView(player, npc, BattleView.getTextBubble(), battleButtons, () -> SceneManager.switchStageTo(SceneManager.getFightView()));
                SceneManager.switchStageTo(switchView);
                switchView.setTurnDisable(true);
                refreshSprites();
            });
        }
        System.out.println("FaintEvent : executing...");
        executor.executeEvents(null);
    }
}
