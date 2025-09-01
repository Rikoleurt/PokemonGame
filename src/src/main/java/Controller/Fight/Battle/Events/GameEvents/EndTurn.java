package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.Switch.SwitchEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;

import static View.Game.Battle.BattleView.npc;
import static View.Game.Battle.BattleView.player;

public class EndTurn extends BattleEvent {
    BattleButtons battleButtons;
    BattleExecutor executor;

    public EndTurn(BattleButtons battleButtons,  BattleExecutor executor) {
        this.battleButtons = battleButtons;
        this.executor = executor;
    }

    @Override
    public void execute() {
        Pokemon npcPokemon = npc.getFrontPokemon();
        if (npcPokemon.isKO() && npc.getHealthyPokemon() > 0) {
            executor.addEvent(new MessageEvent(npcPokemon.getName() + " fainted."));
            Pokemon next = npc.chooseSwitchTarget();
            if(next != null){
                executor.addEvent(new MessageEvent(npc.getName() + " sends " + next.getName() + "!"));
                executor.addEvent(new SwitchEvent(npc, next, BattleView.getTerrain(), executor));
            }
        }
        if(npc.getHealthyPokemon() == 0){
            executor.addEvent(new MessageEvent(npc.getName() + " has been defeated."));
            executor.addEvent(new MessageEvent(npc.getName() + " gives you 1000 Poké dollars."));
            executor.executeEvents(null);
            return;

        } else if(player.getHealthyPokemon() == 0) {
            executor.addEvent(new MessageEvent(player.getName() + " is out of usable Pokémon."));
            executor.addEvent(new MessageEvent(player.getName() + " scurried to a Pokémon Center, protecting the exhausted and fainted Pokémon from further harm"));
            executor.executeEvents(null);
            return;
        }
        executor.executeEvents(this::onFinish);
    }
    @Override public void onFinish() {
        executor.clearEvents();
        battleButtons.resetFightButtons(getClass().getSimpleName());
        battleButtons.requestFocus();
    }

}
