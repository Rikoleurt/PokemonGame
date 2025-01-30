package Person;

import Inventory.Inventory;
import Pokemon.Pokemon;
import Pokemon.TerrainEnum.Debris;
import Pokemon.Terrain;
import Pokemon.TerrainEnum.Meteo;

import java.util.LinkedList;

public class Player {

    String nickname;
    Inventory inventory;
    LinkedList<Pokemon> team;

    int turn;

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
    public Pokemon getFrontPokemon() {
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
        return getFrontPokemon() == pokemon;
    }
    public int getTeamSize() {
        return team.size();
    }

    public int getIndexOf(Pokemon pokemon) {
        return team.indexOf(pokemon);
    }

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

    /// ------------------------------------------------------------------------------------------------------------------
    // Player's choices in fights
    /// ------------------------------------------------------------------------------------------------------------------

    // sendPokemon() should be called automatically at the beginning of the fights
    public void sendPokemon(Terrain terrain){
        System.out.println(getFrontPokemon().getName() + "! Go!");
        terrain.getTeam().add(getFrontPokemon());
    }

    // After sending a first pokemon, the player has multiple choices in fights

    public void changePokemon(Pokemon pokemon, Terrain terrain){
        System.out.println(this.getFrontPokemon().getName() + " stop! ");

        exchangePokemonToFront(getFrontPokemon(), pokemon);

        System.out.println(pokemon.getName() + "! Go!");

        System.out.println(terrain.getDebris());
        if(terrain.getDebris() != Debris.normal){
            terrain.debrisEffect(this, pokemon, terrain);
        }
    }

    public void useItem(Pokemon pokemon){

    }
    public void flee(){

    }
}
