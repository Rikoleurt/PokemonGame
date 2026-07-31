package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.AttackEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch.PlayerSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.UseItemEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.ComputeEvents.Order;
import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Field;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Server.ActionDecoder;
import Server.SocketServer;
import View.GameView.BattleViews.BattleButtons;
import View.GameView.BattleViews.BattleView;

import java.io.IOException;

public class StartTurn extends BattleEvent {

    private final BattleExecutor executor;

    private final Trainer agent;
    private final Trainer player;

    private final Move move;

    private final Field field;

    private Item playerItem;
    private Pokemon playerSwitchTarget;

    private Move agentMove;

    private BattleButtons battleButtons;
    private final SocketServer socketServer = SocketServer.getInstance();

    public StartTurn(Trainer agent, Trainer player, Move move, Field field, BattleExecutor executor, BattleButtons battleButtons) {
        this.agent = agent;
        this.player = player;
        this.move = move;
        this.field = BattleView.getTerrain();
        this.executor = executor;
        this.battleButtons = battleButtons;
    }

    public StartTurn(Trainer agent, Trainer player, Pokemon playerSwitchTarget, BattleExecutor executor) {
        this.agent = agent;
        this.player = player;
        this.playerSwitchTarget = playerSwitchTarget;
        this.move = null;
        this.field = BattleView.getTerrain();
        this.executor = executor;
        this.battleButtons = BattleView.getFightButtons();
    }

    public StartTurn(Trainer agent, Trainer player, Item playerItem, BattleExecutor executor, BattleButtons battleButtons) {
        this.agent = agent;
        this.player = player;
        this.playerItem = playerItem;
        this.playerSwitchTarget = null;
        this.move = null;
        this.field = BattleView.getTerrain();
        this.executor = executor;
    }

    @Override
    public void execute() throws IOException {
        executor.increaseTurn();

        BattleButtons.getHBox1().setVisible(false);
        BattleButtons.getHBox2().setVisible(false);
        String receivedMessage = null;

        try {
            System.out.println("Sending state at " + getClass().getSimpleName());
            socketServer.send(socketServer.state(player, agent, executor.getTurn()));
            receivedMessage = socketServer.getActionMessage(player, agent);
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage() + " at StartTurn");
        }
        ActionDecoder decoder = new ActionDecoder(agent);

        assert receivedMessage != null;
        int actionIndex = Integer.parseInt(receivedMessage);

        Action agentAction = decoder.getActionFromMessage(actionIndex);
        Action playerAction = player.getAction();


        Item agentItem;
        Pokemon agentSwitchTarget;

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon agentPokemon = agent.getFrontPokemon();

        Order order = new Order(player, agent, agentAction);
        boolean playerPriority = order.compute();

        if (playerPriority && !playerPokemon.isKO()) {
            switch (playerAction) {
                case Attack -> {
                    executor.addEvent(new AttackEvent(playerPokemon, agentPokemon, move, field, executor));
                }
                case Item -> {
                    executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                }
                case Switch -> {
                    executor.addEvent(new PlayerSwitchEvent(player, playerSwitchTarget, executor));
                    playerPokemon = playerSwitchTarget;
                }
            }
            if (!agentPokemon.isKO()) {
                switch (agentAction) {
                    case Attack -> {
                        agentMove = decoder.resolveMoveByActionIndex(actionIndex);
                        executor.addEvent(new AttackEvent(agentPokemon, playerPokemon, agentMove, field, executor));
                    }
                    case Item -> {
                        agentItem = decoder.resolveItemByActionIndex(actionIndex);
                        executor.addEvent(new UseItemEvent(agent, agentItem, agentPokemon, executor));
                    }
                    case Switch -> {
                        agentSwitchTarget = decoder.resolveSwitchTargetByActionIndex(actionIndex);
                        executor.addEvent(new FoeSwitchEvent(agent, agentSwitchTarget, field));
                        agentPokemon = agentSwitchTarget;
                    }
                }
            }
        } else if (!agentPokemon.isKO()) {
            switch (agentAction) {
                case Attack -> {
                    agentMove = decoder.resolveMoveByActionIndex(actionIndex);
                    executor.addEvent(new AttackEvent(agentPokemon, playerPokemon, agentMove, field, executor));
                }
                case Item -> {
                    agentItem = decoder.resolveItemByActionIndex(actionIndex);
                    executor.addEvent(new UseItemEvent(agent, agentItem, agentPokemon, executor));
                }
                case Switch -> {
                    agentSwitchTarget = decoder.resolveSwitchTargetByActionIndex(actionIndex);
                    executor.addEvent(new FoeSwitchEvent(agent, agentSwitchTarget, field));
                    agentPokemon = agentSwitchTarget;
                }
            }
            if (!playerPokemon.isKO()) {
                switch (playerAction) {
                    case Attack -> {
                        executor.addEvent(new AttackEvent(playerPokemon, agentPokemon, move, field, executor));
                    }
                    case Item -> {
                        executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                    }
                    case Switch -> {
                        executor.addEvent(new PlayerSwitchEvent(player, playerSwitchTarget, executor));
                        playerPokemon = playerSwitchTarget;
                    }
                }
            }
        }

        onFinish();
    }

    @Override
    public void onFinish() throws IOException {
        executor.addEvent(new EndTurn(player, agent, executor));
        executor.executeEvents(null);
    }
}
