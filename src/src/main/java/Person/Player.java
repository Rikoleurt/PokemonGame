package Person;

import Inventory.Inventory;
import Pokemon.Pokemon;
import Pokemon.TerrainEnum.Debris;
import Pokemon.Terrain;

import java.util.LinkedList;

public class Player {

    String nickname;
    Inventory inventory;
    LinkedList<Pokemon> team;

    public Player(String nickname, Inventory inventory, LinkedList<Pokemon> team) {
        this.nickname = nickname;
        this.inventory = inventory;
        this.team = team;
    }
    public String getNickname() {
        return nickname;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public LinkedList<Pokemon> getTeam() {
        return team;
    }
    public Pokemon getPokemon(Pokemon pokemon) {
        return team.get(getTeam().indexOf(pokemon));
    }
    public Pokemon getFrontPokemon(Pokemon pokemon) {
        return team.get(0);
    }

    public void exchangePokemonToFront(Pokemon pokemon, Pokemon otherPokemon) {

        if(this.isFront(pokemon)) {
            int temp = team.indexOf(otherPokemon); // bulbizarre at ?
            Pokemon tempPokemon = team.get(0); // temp Pokemon is pikachu
            team.set(0, otherPokemon); // set to 0 bulbizarre
            team.set(temp, tempPokemon); // set to ? pikachu
        } else {
            System.out.println("Not possible because " + otherPokemon.getName() + " is not at the front");
        }


    }

    public void exchangePositionOf(Pokemon pokemon, Pokemon otherPokemon) {

        int temp = team.indexOf(otherPokemon); // Bulbizarre at position 1
        Pokemon tempPokemon = getPokemon(pokemon); // temp is pikachu
        int index = team.indexOf(tempPokemon); // index of pikachu is 0
        team.set(index, otherPokemon);
        team.set(temp, tempPokemon);

    }

    public boolean isFront(Pokemon pokemon) {
        return getFrontPokemon(pokemon) == pokemon;
    }
    public int getTeamSize() {
        return team.size();
    }

    public int getIndexOf(Pokemon pokemon) {
        return team.indexOf(pokemon);
    }

    // I stopped working here, goal : compare the index of pokemons to know if they changed their position 

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean hasChanged(Pokemon pokemon, Pokemon otherPokemon) {
        int first = getIndexOf(pokemon);
        exchangePokemonToFront(pokemon, otherPokemon);
        int second = getIndexOf(otherPokemon);
        return second == first;
    }
    public void sendPokemon(Pokemon pokemon, Terrain terrain, NPC npc){
        if(terrain.getDebris() != Debris.normal){
            terrain.updateDebris(this.getFrontPokemon(pokemon), terrain);
        }
        terrain.addPokemon(this,npc);
    }
}
