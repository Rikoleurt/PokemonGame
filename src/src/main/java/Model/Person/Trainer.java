package Model.Person;

import Model.Inventory.Bag;
import Model.Inventory.Items.Consumable;
import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Field;
import Model.Pokemon.TerrainEnum.Debris;
import Utils.SeedManager;

import java.util.LinkedList;
import java.util.Random;

public class Trainer implements Fighter {
    String name;
    LinkedList<Pokemon> team;
    Bag bag;
    Action action;

    public Trainer(String name, Bag bag, LinkedList<Pokemon> team) {
        this.name = name;
        this.bag = bag;
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
    public Action getAction() {
        return action;
    }

    @Override
    public void use(Item item, Pokemon target) {
        if (item instanceof Consumable && bag.getInventory().containsKey(item) && bag.getQuantity(item) > 0) {
            ((Consumable) item).consume(target);
            bag.setQuantity(item, bag.getInventory().get(item) - 1);
        }
    }

    public void setAction(Action action) { this.action = action; }
    public boolean isFront(Pokemon pokemon) {
        return getFrontPokemon() == pokemon;
    }
    public int getIndexOf(Pokemon pokemon) {
        return team.indexOf(pokemon);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFront(Pokemon pokemon, Field field) {
        exchangePokemonToFront(getFrontPokemon(), pokemon);
        if(field.getDebris() != Debris.normal){
            field.debrisEffect(this, field);
        }
    }
    public void setFront(Pokemon pokemon){
        exchangePokemonToFront(getFrontPokemon(), pokemon);
    }
    public Action makeChoiceAction(){
        Random rand = new Random(SeedManager.getSeed());
        int randInt = rand.nextInt(20,100);
        int healthyPokemon = getHealthyPokemon();
        if (randInt < 5 && healthyPokemon >= 0) {
            action = Action.Switch;
        } else if (randInt < 5 && team.size() == 1) {
            makeChoiceAction();
        } else if (randInt < 20 && randInt >= 10 && getFrontPokemon().getHP() != getFrontPokemon().getMaxHP()){
            action = Action.Item;
        } else {
            action = Action.Attack;
        }
        return action;
    }
    public Bag getBag() {
        return bag;
    }
    public int getHealthyPokemon() {
        int healthyPokemon = 0;
        for (Pokemon p : team) {
            if(p.getStatus() != Status.KO) healthyPokemon++;
        }
        return healthyPokemon;
    }
    public Pokemon chooseSwitchTarget() {
        for (Pokemon p : team) {
            if (p != getFrontPokemon() && p.getStatus() != Status.KO) {
                return p;
            }
        }
        return null;
    }
    public Item itemChoice(Pokemon target) {
        Item itemChoice = null;
        if(target.getHP() < target.getMaxHP()/2) itemChoice = getBag().getFirstHeal();
        return itemChoice;
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
