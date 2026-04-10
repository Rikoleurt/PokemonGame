package Controller.Fight.Battle.Events.ComputeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import Utils.SeedManager;

import java.util.Random;

/**
 * Computes the order of the next turn and returns true if the player is the first to play
 */
public class Order extends ComputeEvent<Boolean> {

    private final Trainer player;
    private final Trainer npc;
    Action npcAction;

    public Order(Trainer player, Trainer npc, Action action) {
        this.player = player;
        this.npc = npc;
        this.npcAction = action;
    }

    @Override
    public Boolean compute() {
        Action playerAction = player.getAction();

        Pokemon playerPkmn = player.getFrontPokemon();
        Pokemon foe = npc.getFrontPokemon();

        int playerPriority = priorityOf(playerAction);
        int npcPriority = priorityOf(npcAction);

//        System.out.println();
//        System.out.println("Class : " + getClass().getSimpleName() + " : ");
//        System.out.println("playerAction : " + playerAction + ", playerPriority: " + playerPriority);
//        System.out.println("npcAction : " +  npcAction + ", npcPriority: " + npcPriority);
//        System.out.println();

        if (playerPriority > npcPriority) return true;
        if (playerPriority < npcPriority) return false;

        int playerSpeed = playerPkmn.getEffectiveSpeed();
        int npcSpeed = foe.getEffectiveSpeed();

        if (playerSpeed > npcSpeed) return true;
        if (playerSpeed < npcSpeed) return false;

        return new Random(SeedManager.getSeed()).nextBoolean();
    }

    private int priorityOf(Action action) {
        if (action == Action.Switch) return 6;
        if (action == Action.Item) return 6;
        if (action == Action.Run) return 6;
        if (action == Action.Attack) return 0;
        return 0;
    }
}
