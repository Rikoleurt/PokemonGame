package Person;

import Pokemon.Pokemon;

import java.util.LinkedList;

public class NPC{

    String name;
    LinkedList<Pokemon> team;

    public NPC(String name, LinkedList<Pokemon> team) {
        this.name = name;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Pokemon> getTeam() {
        return team;
    }
    public Pokemon getFrontPokemon() {
        return team.getFirst();
    }
}
