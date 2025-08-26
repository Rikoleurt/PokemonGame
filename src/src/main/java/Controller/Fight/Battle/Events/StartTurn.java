package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.AttackEvent;
import Controller.Fight.Battle.Events.ActionEvents.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.PlayerSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.UseItemEvent;
import Controller.Fight.Battle.Events.ComputeEvents.StatusEvent;
import Controller.Fight.Battle.Events.UIEvents.EndTurn;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeItemChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoePokemonChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.OrderEvent;
import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.Battle.BattleButtons;

public class StartTurn extends BattleEvent {

    private final BattleExecutor executor;
    private final NPC npc;
    private final Player player;
    private final Move move;
    private final Terrain terrain;
    private Item playerItem;
    private Pokemon switchTarget;

    BattleButtons battleButtons;

    public StartTurn(NPC npc, Player player, Move move, Terrain terrain, BattleExecutor executor, BattleButtons battleButtons) {
        this.npc = npc;
        this.player = player;
        this.move = move;
        this.terrain = terrain;
        this.executor = executor;
        this.battleButtons = battleButtons;
    }

    public StartTurn(NPC npc, Player player, Move move, Terrain terrain, Item playerItem, BattleExecutor executor, BattleButtons battleButtons) {
        this.npc = npc;
        this.player = player;
        this.move = move;
        this.terrain = terrain;
        this.playerItem = playerItem;
        this.executor = executor;
        this.battleButtons = battleButtons;

    }
    public StartTurn(NPC npc, Player player, Move move, Terrain terrain, Pokemon switchTarget, BattleExecutor executor, BattleButtons battleButtons) {
        this.npc = npc;
        this.player = player;
        this.move = move;
        this.terrain = terrain;
        this.switchTarget = switchTarget;
        this.executor = executor;
        this.battleButtons = battleButtons;
    }

    @Override
    public void execute() {
        executor.increaseTurn();

        Action npcAction = new FoeChoiceEvent(npc).compute();
        Action playerAction = player.getAction();

        Item item = new FoeItemChoiceEvent(npc).compute();

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon npcPokemon = npc.getFrontPokemon();

        Pokemon npcSwitchTarget = new FoePokemonChoiceEvent(npc).compute();

        OrderEvent orderEvent = new OrderEvent(player, npc);
        boolean playerPriority = orderEvent.compute();

        if (playerPriority) {
            switch (playerAction) {
                case Attack -> executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, executor));
                case Item   -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                case Switch -> executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
            }
            switch (npcAction) {
                case Attack -> executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), terrain, executor));
                case Item   -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                case Switch -> executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, terrain, executor));
            }
        } else {
            switch (npcAction) {
                case Attack -> executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), terrain, executor));
                case Item   -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                case Switch -> executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, terrain, executor));
            }
            switch (playerAction) {
                case Attack -> executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, executor));
                case Item   -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                case Switch -> executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
            }
        }
        executor.executeNext(this::onFinish);
        executor.addEvent(new StatusEvent(playerPokemon, npcPokemon));

    }
}
