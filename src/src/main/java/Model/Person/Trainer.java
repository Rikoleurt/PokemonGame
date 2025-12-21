package Model.Person;

import Model.Inventory.Bag;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;

import java.util.LinkedList;

public class Trainer {
    String name;
    LinkedList<Pokemon> team;

    public Trainer(String name, LinkedList<Pokemon> team) {
        this.name = name;
        this.team = team;
    }

    public LinkedList<Pokemon> getTeam() {
        return team;
    }
    public String getName() {
        return name;
    }
    public Pokemon getPokemon(Pokemon pokemon) {
        return team.get(getTeam().indexOf(pokemon));
    }
    public Pokemon getFrontPokemon() {
        return team.getFirst();
    }
    public boolean isFront(Pokemon pokemon) {
        return getFrontPokemon() == pokemon;
    }
    public int getIndexOf(Pokemon pokemon) {
        return team.indexOf(pokemon);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFront(Pokemon pokemon){
        exchangePokemonToFront(getFrontPokemon(), pokemon);
    }
    private void exchangePokemonToFront(Pokemon pokemon, Pokemon otherPokemon) {
        if(this.isFront(pokemon)) {
            int temp = team.indexOf(otherPokemon); // bulbizarre at ?
            Pokemon tempPokemon = team.getFirst(); // temp Model.Pokemon is pikachu
            team.set(0, otherPokemon); // set to 0 bulbizarre
            team.set(temp, tempPokemon); // set to ? pikachu
        } else {
            System.out.println("Not possible because " + otherPokemon.getName() + " is not at the front");
        }
    }
    private void exchangePositionOf(Pokemon pokemon, Pokemon otherPokemon) {
        int temp = team.indexOf(otherPokemon); // Bulbizarre at position 1
        Pokemon tempPokemon = getPokemon(pokemon); // temp is pikachu
        int index = team.indexOf(tempPokemon); // index of pikachu is 0
        team.set(index, otherPokemon);
        team.set(temp, tempPokemon);
    }

}
