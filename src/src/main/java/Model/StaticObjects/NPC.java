package Model.StaticObjects;

import Model.Pokemon.Pokemon;

import java.util.LinkedList;

import static Model.StaticObjects.Pokemons.initiateBulbizarre;
import static Model.StaticObjects.Pokemons.initiateCarapuce;

public class NPC {

    public static Model.Person.NPC initiateEnemy()
    {
        LinkedList<Pokemon> team = new LinkedList<>();
        Pokemon Bulbizarre = initiateBulbizarre();
        Pokemon Carapuce = initiateCarapuce();
        team.add(Bulbizarre);
        team.add(Carapuce);
        return new Model.Person.NPC("npc", team);
    }
}
