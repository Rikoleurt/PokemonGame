package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.Switch.SwitchEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;
import Server.SocketServer;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import View.Game.SceneManager;
import View.Game.Switch.SwitchFaintedView;

import java.io.IOException;
import java.net.ServerSocket;

import static View.Game.Battle.BattleView.*;

public class EndTurn extends BattleEvent {
    BattleButtons battleButtons;
    BattleExecutor executor;

    public EndTurn(BattleExecutor executor) {
        this.battleButtons = getFightButtons();
        this.executor = executor;
    }

    @Override
    public void execute() {
        Pokemon npcPokemon = npc.getFrontPokemon();
        Pokemon playerPokemon = player.getFrontPokemon();

        Terrain terrain = BattleView.getTerrain();

        if(playerPokemon.getStatus() != Status.normal) executor.addEvent(new StatusEvent(playerPokemon));
        if(npcPokemon.getStatus() != Status.normal) executor.addEvent(new StatusEvent(npcPokemon));

        if (npcPokemon.isKO() && npc.getHealthyPokemon() > 0) {
            executor.addEvent(new MessageEvent(npcPokemon.getName() + " fainted."));
            Pokemon next = npc.chooseSwitchTarget();
            if(next != null){
                executor.addEvent(new MessageEvent(npc.getName() + " sends " + next.getName() + "!"));
                executor.addEvent(new SwitchEvent(npc, next, terrain, executor));
            }
        }
        if(playerPokemon.isKO() && npc.getHealthyPokemon() > 0) {
            executor.addEvent(new MessageEvent(playerPokemon.getName() + " fainted."));
            executor.addEvent(new SwitchEvent(player, playerPokemon, terrain, executor));
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
        BattleView.refreshSprites();
        getPlayerBar().refreshBar();
        getOpponentBar().refreshBar();

        try {
            Server.SocketServer.getInstance().sendState(Server.SocketServer.getInstance().refreshState());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }
}
