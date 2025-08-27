package Controller.Fight.Battle.Events;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.AttackEvent;
import Controller.Fight.Battle.Events.ActionEvents.FoeSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.PlayerSwitchEvent;
import Controller.Fight.Battle.Events.ActionEvents.UseItemEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeItemChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoePokemonChoiceEvent;
import Controller.Fight.Battle.Events.ComputeEvents.Order;
import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;

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

    public StartTurn(NPC npc, Player player, Pokemon switchTarget,  BattleExecutor executor) {
        this.npc = npc;
        this.player = player;
        this.switchTarget = switchTarget;
        this.move = null;
        this.terrain = BattleView.getTerrain();
        this.executor = executor;
        this.battleButtons = BattleView.getFightButtons();
    }

    @Override
    public void execute() {
        executor.increaseTurn();
        BattleButtons.getHBox1().setVisible(false);
        BattleButtons.getHBox2().setVisible(false);

        Action npcAction = new FoeChoiceEvent(npc).compute();
        Action playerAction = player.getAction();

        Item item = new FoeItemChoiceEvent(npc).compute();

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon npcPokemon = npc.getFrontPokemon();

        System.out.println(playerPokemon.getName() + " vs " + npcPokemon.getName());

        Order order = new Order(player, npc, npcAction);
        boolean playerPriority = order.compute();

        System.out.println("Player prio : " + playerPriority);

        if (playerPriority && !playerPokemon.isKO()) {
            switch (playerAction) {
                case Attack -> executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, executor));
                case Item   -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                case Switch ->  {
                    executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
                    playerPokemon = switchTarget;
                }
            }
            if (!npcPokemon.isKO()) {
                switch (npcAction) {
                    case Attack ->
                            executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), terrain, executor));
                    case Item -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                    case Switch -> {
                        Pokemon npcSwitchTarget = new FoePokemonChoiceEvent(npc).compute();
                        executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, terrain, executor, battleButtons));
                        npcPokemon = npcSwitchTarget;
                    }
                }
            }
        } else if (!npcPokemon.isKO()) {
            switch (npcAction) {
                case Attack ->
                        executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcPokemon.chooseMove(), terrain, executor));
                case Item -> executor.addEvent(new UseItemEvent(npc, item, npcPokemon, executor));
                case Switch -> {
                    Pokemon npcSwitchTarget = new FoePokemonChoiceEvent(npc).compute();
                    executor.addEvent(new FoeSwitchEvent(npc, npcSwitchTarget, terrain, executor, battleButtons));
                    npcPokemon = npcSwitchTarget;
                }
            }
            if (!playerPokemon.isKO()) {
                switch (playerAction) {
                    case Attack -> executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, executor));
                    case Item -> executor.addEvent(new UseItemEvent(player, playerItem, playerPokemon, executor));
                    case Switch -> {
                        executor.addEvent(new PlayerSwitchEvent(player, switchTarget, executor));
                        playerPokemon = switchTarget;
                    }
                }
            }
        }

        System.out.println(playerPokemon.getName() + " vs " + npcPokemon.getName());

        executor.executeNext(this::onFinish);
        if(npcPokemon.isKO()){
            System.out.println(npcPokemon.getName() + " is KO");
            executor.addEvent(new FaintEvent(player, npc, npcPokemon, battleButtons));
            executor.executeNext(null);
        }
        if(playerPokemon.isKO()){
            executor.addEvent(new FaintEvent(player, npc, playerPokemon, battleButtons));
            executor.executeNext(null);
        }
        if(playerPokemon.getStatus() != Status.normal) executor.addEvent(new StatusEvent(playerPokemon, npcPokemon));
    }
}
