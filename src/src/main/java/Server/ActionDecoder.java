package Server;

import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;

import java.io.IOException;

public class ActionDecoder {

    private final Trainer agent;

    public ActionDecoder(Trainer agent) {
        this.agent = agent;
    }

    public Action getActionFromMessage(int actionIndex) throws IOException {
        if (actionIndex >= 0 && actionIndex <= 3) return Action.Attack;
        else if (actionIndex >= 4 && actionIndex <= 8) return Action.Switch;
        else if (actionIndex == 9) return Action.Item;
        else {
            System.out.println("Unknown Action " + actionIndex);
            return null;
        }
    }

    public Move resolveMoveByActionIndex(int actionIndex) {
        if (actionIndex < 0 || actionIndex > 3) return null;
        if (agent == null || agent.getFrontPokemon() == null) return null;
        if (agent.getFrontPokemon().getAttacks() == null
                || actionIndex >= agent.getFrontPokemon().getAttacks().size()) {
            return null;
        }
        return agent.getFrontPokemon().getAttacks().get(actionIndex);
    }

    public Pokemon resolveSwitchTargetByActionIndex(int actionIndex) {
        if (agent == null || actionIndex < 4 || actionIndex > 8) return null;
        int teamIndex = actionIndex - 3;
        if (teamIndex >= agent.getTeam().size()) return null;

        return agent.getTeam().get(teamIndex);
    }

    public Item resolveItemByActionIndex(int actionIndex) {
        if (actionIndex == 9 && agent != null && agent.getBag() != null) {
            return agent.getBag().getFirstHeal();
        }
        return null;
    }
}