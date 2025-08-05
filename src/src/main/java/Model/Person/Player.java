package Model.Person;

import Model.Inventory.Bag;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Usable;
import Model.Pokemon.Pokemon;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.Terrain;
import View.Game.FightView.Text.TextBubble;

import java.util.LinkedList;

public class Player {

    String name;
    Bag bag;
    LinkedList<Pokemon> team;

    public Player(String name, Bag bag, LinkedList<Pokemon> team) {
        this.name = name;
        this.bag = bag;
        this.team = team;
    }

    public Bag getInventory() {
        return bag;
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

    public void exchangePokemonToFront(Pokemon pokemon, Pokemon otherPokemon) {
        if(this.isFront(pokemon)) {
            int temp = team.indexOf(otherPokemon); // bulbizarre at ?
            Pokemon tempPokemon = team.getFirst(); // temp Model.Pokemon is pikachu
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

    public int getIndexOf(Pokemon pokemon) {
        return team.indexOf(pokemon);
    }

    public void setInventory(Bag bag) {
        this.bag = bag;
    }

    public void setName(String name) {
        this.name = name;
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
        if(terrain.getDebris() != Debris.normal){
            terrain.debrisEffect(this, terrain);
        }
    }

    public void use(Item item, Pokemon target, TextBubble textBubble) {
        if (item instanceof Usable) {
            System.out.println(name + " uses a " + item.getName() + "!");
            ((Usable) item).use(target, textBubble);
        } else { // Security
            System.out.println("This item cannot be used.");
        }
    }
    public void flee(){

    }
}
