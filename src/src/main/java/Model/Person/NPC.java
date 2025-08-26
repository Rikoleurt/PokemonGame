package Model.Person;

import Model.Inventory.Bag;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Consumable;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;

import View.Game.Battle.Text.TextBubble;

import java.util.LinkedList;
import java.util.Random;

public class NPC implements Fighter {

    String name;
    LinkedList<Pokemon> team;
    Bag bag;
    String lastChoice;
    Action action = null;

    public NPC(String name, LinkedList<Pokemon> team, Bag bag) {
        this.name = name;
        this.team = team;
        this.bag = bag;
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
    public Bag getBag() {
        return bag;
    }
    public String makeChoice() {
        Random rand = new Random();
        int randInt = rand.nextInt(0,100);
        int healthyPokemon = getHealthyPokemon();
        if (randInt < 10 && healthyPokemon > 1) {
            lastChoice = "Switch";
        } else if (randInt < 5 && team.size() == 1) {
            makeChoice();
        } else if (randInt < 20 && randInt >= 10) {
            lastChoice = "Item";
        } else {
            lastChoice = "Attack";
        }
        return lastChoice;
    }

    public Action makeChoiceAction(){
        Random rand = new Random();
        int randInt = rand.nextInt(0,100);
        int healthyPokemon = getHealthyPokemon();
        if (randInt < 10 && healthyPokemon > 1) {
            action = Action.Switch;
        } else if (randInt < 5 && team.size() == 1) {
            makeChoice();
        } else if (randInt < 20 && randInt >= 10) {
            action = Action.Item;
        } else {
            action = Action.Attack;
        }
        return action;
    }

    @Override
    public void use(Item item, Pokemon target) {
        if (item instanceof Consumable && bag.getInventory().containsKey(item) && bag.getQuantity(item) > 0) {
            ((Consumable) item).consume(target);
            bag.setQuantity(item, bag.getInventory().get(item) - 1);
        }
    }

//    @Override
//    public void use(Item item, Pokemon target) {
//        if (!"Item".equals(lastChoice)) return;
//        if (item instanceof Consumable && bag.getInventory().containsKey(item) && bag.getQuantity(item) > 0) {
//            ((Consumable) item).consume(target);
//            bag.setQuantity(item, bag.getInventory().get(item) - 1);
//        }
//    }

    public Item itemChoice(Pokemon target) {
        Item itemChoice = null;
        if(target.getHP() < target.getMaxHP()/2) itemChoice = getBag().getFirstHeal();
        return itemChoice;
    }

    private void exchangePokemonToFront(Pokemon pokemon, Pokemon otherPokemon) {
        if (isFront(pokemon)) {
            int temp = team.indexOf(otherPokemon);
            Pokemon tempPokemon = team.getFirst();
            team.set(0, otherPokemon);
            team.set(temp, tempPokemon);
        } else {
            System.out.println("Not possible because " + otherPokemon.getName() + " is not at the front");
        }
    }

    public boolean isFront(Pokemon pokemon) {
        return getFrontPokemon() == pokemon;
    }

    public void setFront(Pokemon pokemon, Terrain terrain) {
        exchangePokemonToFront(getFrontPokemon(), pokemon);
        if (terrain.getDebris() != Debris.normal) {
            terrain.debrisEffect(this, terrain);
        }
    }

    public Pokemon chooseSwitchTarget() {
        for (Pokemon p : team) {
            if (p != getFrontPokemon() && p.getStatus() != Status.KO) {
                return p;
            }
        }
        return null;
    }

    public int getHealthyPokemon() {
        int healthyPokemon = 0;
        for (Pokemon p : team) {
            if(p.getStatus() != Status.KO) healthyPokemon++;
        }
        return healthyPokemon;
    }
}
