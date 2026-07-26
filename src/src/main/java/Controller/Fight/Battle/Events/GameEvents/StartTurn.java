package Controller.Fight.Battle.Events.GameEvents;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.AttackEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch.PlayerSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.UseItemEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeItemChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoePokemonChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.Order;
import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Field;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Server.ActionDecoder;
import Server.SocketServer;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;

import java.io.IOException;

public class StartTurn extends BattleEvent {

    private final BattleExecutor executor;
    private final Trainer npc;
    private final Trainer player;
    private final Move move;
    private final Field field;
    private Item playerItem;
    private Pokemon switchTarget;
    private BattleButtons battleButtons;
    private final SocketServer socketServer = SocketServer.getInstance();
    private final ActionDecoder decoder = new ActionDecoder();

    public StartTurn(Trainer npc, Trainer player, Move move, Field field, BattleExecutor executor, BattleButtons battleButtons) {
        this.npc = npc;
        this.player = player;
        this.move = move;
        this.field = field;
        this.executor = executor;
        this.battleButtons = battleButtons;
    }

    public StartTurn(Trainer npc, Trainer player, Pokemon switchTarget, BattleExecutor executor) {
        this.npc = npc;
        this.player = player;
        this.switchTarget = switchTarget;
        this.move = null;
        this.field = BattleView.getTerrain();
        this.executor = executor;
        this.battleButtons = BattleView.getFightButtons();
    }

    public StartTurn(Trainer npc, Trainer player, Item playerItem, BattleExecutor executor, BattleButtons battleButtons) {
        this.npc = npc;
        this.player = player;
        this.playerItem = playerItem;
        this.switchTarget = null;
        this.move = null;
        this.field = BattleView.getTerrain();
        this.executor = executor;
    }

    @Override
    public void execute() throws IOException {
        executor.increaseTurn();

        BattleButtons.getHBox1().setVisible(false);
        BattleButtons.getHBox2().setVisible(false);
        socketServer.send(socketServer.state(player,npc, executor.getTurn()));
        String receivedMessage = socketServer.getActionMessage(npc);

        Action npcAction = decoder.getActionFromMessage(receivedMessage);
        Action playerAction = player.getAction();

        Item item = new FoeItemChoiceEvent(npc).compute();

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon npcPokemon = npc.getFrontPokemon();

        Order order = new Order(player, npc, npcAction);
        boolean playerPriority = order.compute();

        if (playerPriority && !playerPokemon.isKO()) {
            switch (playerAction) {
                case Attack -> executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, field, executor));
                case Item -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                case Switch -> {
                    executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
                    playerPokemon = switchTarget;
                }
            }
            if (!npcPokemon.isKO()) {
                switch (npcAction) {
                    case Attack ->
                            executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), field, executor));
                    case Item -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                    case Switch -> {
                        Pokemon npcSwitchTarget = new FoePokemonChoiceEvent(npc).compute();
                        executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, field));
                        npcPokemon = npcSwitchTarget;
                    }
                }
            }
        } else if (!npcPokemon.isKO()) {
            switch (npcAction) {
                case Attack ->
                        executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), field, executor));
                case Item -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                case Switch -> {
                    Pokemon npcSwitchTarget = new FoePokemonChoiceEvent(npc).compute();
                    executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, field));
                    npcPokemon = npcSwitchTarget;
                }
            }
            if (!playerPokemon.isKO()) {
                switch (playerAction) {
                    case Attack ->
                            executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, field, executor));
                    case Item -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                    case Switch -> {
                        executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
                        playerPokemon = switchTarget;
                    }
                }
            }
        }

        onFinish();
    }

    @Override public void onFinish() throws IOException {
        executor.addEvent(new EndTurn(executor));
        executor.executeEvents(null);
    }
}
