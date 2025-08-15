package Model.Person;

import Model.Inventory.Bag;
import Model.Inventory.Items.Item;
import Model.Inventory.Items.Usable;
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
        int randInt = rand.nextInt(1,100);
        if (randInt < 10 && team.size() >= 2) {
            lastChoice = "Switch";
        } else if (randInt < 5 && team.size() == 1) {
            makeChoice();
        } else if (randInt < 20 && randInt > 10) {
            lastChoice = "Item";
        } else {
            lastChoice = "Attack";
        }
        System.out.println(lastChoice);
        return lastChoice;
    }
    public void use(Item item, Pokemon target, TextBubble textBubble) {
        if (!"Item".equals(lastChoice)) return;
        if (item instanceof Usable) ((Usable) item).use(target, textBubble);
    }

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
        System.out.println(this.getFrontPokemon().getName() + " stop! ");
        exchangePokemonToFront(getFrontPokemon(), pokemon);
        System.out.println(pokemon.getName() + "! Go!");
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
}
