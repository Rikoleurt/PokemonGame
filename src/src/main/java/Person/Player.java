package Person;

import Inventory.Inventory;
import Pokemon.Pokemon;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.awt.event.KeyEvent;

public class Player {

    String nickname;
    Inventory inventory;
    LinkedList<Pokemon> pokemons;

    public Player(String nickname, Inventory inventory, LinkedList<Pokemon> pokemons) {
        this.nickname = nickname;
        this.inventory = inventory;
        this.pokemons = pokemons;
    }
    public String getNickname() {
        return nickname;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public LinkedList<Pokemon> getPokemons() {
        return pokemons;
    }
    public Pokemon getPokemon(Pokemon pokemon) {
        return pokemons.get(getPokemons().indexOf(pokemon));
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
