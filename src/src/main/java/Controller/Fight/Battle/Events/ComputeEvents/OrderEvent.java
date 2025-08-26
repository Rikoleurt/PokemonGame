package Controller.Fight.Battle.Events.ComputeEvents;

import Controller.Fight.Battle.Events.ComputeEvent;
import Controller.Fight.Battle.Events.ComputeEvents.FoeEvents.FoeChoiceEvent;
import Model.Person.Action;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;

import java.util.Random;

/**
 * Computes the order of the next turn and returns true if the player is the first to play
 */
public class OrderEvent extends ComputeEvent<Boolean> {

    private final Player player;
    private final NPC npc;
    Action npcAction;

    public OrderEvent(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    @Override
    public Boolean compute() {
        Action playerAction = player.getAction();

        Pokemon playerPkmn = player.getFrontPokemon();
        Pokemon foe = npc.getFrontPokemon();

        int playerPriority = priorityOf(playerAction);
        int npcPriority = priorityOf(npcAction);

        if (playerPriority > npcPriority) return true;
        if (playerPriority < npcPriority) return false;

        int playerSpeed = playerPkmn.getEffectiveSpeed();
        int npcSpeed = foe.getEffectiveSpeed();

        if (playerSpeed > npcSpeed) return true;
        if (playerSpeed < npcSpeed) return false;

        return new Random().nextBoolean();
    }

    private int priorityOf(Action action) {
        if (action == Action.Switch) return 6;
        if (action == Action.Item) return 6;
        if (action == Action.Run) return 6;
        if (action == Action.Attack) return 0;
        return 0;
    }
}
