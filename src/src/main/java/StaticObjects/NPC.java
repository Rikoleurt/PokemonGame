package StaticObjects;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.Attacks.Attack;
import Pokemon.Pokemon;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static StaticObjects.Moves.initiateAttacks;
import static StaticObjects.Pokemons.initiateBulbizarre;
import static StaticObjects.Pokemons.initiateCarapuce;

public class NPC {

    public static Person.NPC initiateEnemy()
    {
        LinkedList<Pokemon> team = new LinkedList<>();
        Pokemon Bulbizarre = initiateBulbizarre();
        Pokemon Carapuce = initiateCarapuce();
        team.add(Bulbizarre);
        team.add(Carapuce);
        return new Person.NPC("npc", team);
    }
}
