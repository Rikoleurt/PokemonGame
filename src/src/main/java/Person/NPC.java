package Person;

import Pokemon.Pokemon;

import java.util.LinkedList;

public class NPC {

    String name;
    LinkedList<Pokemon> pokemons;

    public NPC(String name, LinkedList<Pokemon> pokemons) {
        this.name = name;
        this.pokemons = pokemons;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Pokemon> getPokemons() {
        return pokemons;
    }
    public Pokemon getFrontPokemon() {
        return pokemons.get(0);
    }
}
