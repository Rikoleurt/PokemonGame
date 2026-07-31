package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.SwitchEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.Trainer;
import Model.Pokemon.Field;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Server.ActionDecoder;
import Server.SocketServer;
import View.GameView.BattleViews.BattleButtons;
import View.GameView.BattleViews.BattleView;

import java.io.IOException;

import static View.GameView.BattleViews.BattleView.*;

public class EndTurn extends BattleEvent {

    private final Trainer player;
    private final Trainer agent;

    private final BattleButtons battleButtons;
    private final BattleExecutor executor;
    private final SocketServer socketServer = SocketServer.getInstance();

    public EndTurn(Trainer player, Trainer agent, BattleExecutor executor) {
        this.player = player;
        this.agent = agent;
        this.battleButtons = getFightButtons();
        this.executor = executor;
    }

    @Override
    public void execute() throws IOException {
        Pokemon agentPokemon = agent.getFrontPokemon();
        Pokemon playerPokemon = player.getFrontPokemon();

        Field field = BattleView.getTerrain();

        if (playerPokemon.getStatus() != Status.normal) executor.addEvent(new StatusEvent(playerPokemon));
        if (agentPokemon.getStatus() != Status.normal) executor.addEvent(new StatusEvent(agentPokemon));

        System.out.println("WTF " + (agentPokemon.isKO() && agent.getHealthyPokemon() > 0));
        if (agentPokemon.isKO() && agent.getHealthyPokemon() > 0) {
            executor.addEvent(new MessageEvent(agentPokemon.getName() + " fainted."));
            executor.executeEvents(() -> getAgentSwitchResponse(field));
            return;
        }

        finalizeTurn(field);
    }

    private void getAgentSwitchResponse(Field field) {
        String receivedMessage = null;
        try {
            socketServer.send(socketServer.state(player, agent, executor.getTurn()));
            receivedMessage = socketServer.getActionMessage(player, agent);
            System.out.println("Received : " + receivedMessage);
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage() + " at EndTurn");
        }
        ActionDecoder decoder = new ActionDecoder(agent);

        assert receivedMessage != null;
        int actionIndex = Integer.parseInt(receivedMessage);
        System.out.println("Decoding switch target");
        Pokemon agentSwitchTarget = decoder.resolveSwitchTargetByActionIndex(actionIndex);

        if (agentSwitchTarget == null) {
            System.out.println("Invalid action index");
            return;
        }
        executor.addEvent(new FoeSwitchEvent(agent, agentSwitchTarget, field));

        try {
            System.out.println("Executing switch");
            executor.executeEvents(() -> finalizeTurn(field));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void finalizeTurn(Field field) {
        Pokemon playerPokemon = player.getFrontPokemon();

        if (playerPokemon.isKO() && player.getHealthyPokemon() > 0) {
            executor.addEvent(new MessageEvent(playerPokemon.getName() + " fainted."));
            executor.addEvent(new SwitchEvent(player, playerPokemon, field, executor));
        }

        if (agent.getHealthyPokemon() == 0) {
            executor.addEvent(new MessageEvent(agent.getName() + " has been defeated."));
            executor.addEvent(new MessageEvent(agent.getName() + " gives you 1000 Poké dollars."));

            try {
                executor.executeEvents(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sendRefreshedState();
            return;
        }

        if (player.getHealthyPokemon() == 0) {
            executor.addEvent(new MessageEvent(player.getName() + " is out of usable Pokémon."));
            executor.addEvent(new MessageEvent(player.getName() + " scurried to a Pokémon Center, protecting the exhausted and fainted Pokémon from further harm"));

            try {
                executor.executeEvents(null);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sendRefreshedState();
            return;
        }

        try {
            executor.executeEvents(this::onFinish);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRefreshedState() {
        try {
            socketServer.send(socketServer.refreshState());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }

    @Override
    public void onFinish() {
        executor.clearEvents();

        battleButtons.resetFightButtons(getClass().getSimpleName());
        battleButtons.requestFocus();

        BattleView.refreshSprites();
        getPlayerBar().refreshBar();
        getOpponentBar().refreshBar();

        sendRefreshedState();
    }
}