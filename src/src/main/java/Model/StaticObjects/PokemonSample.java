package Model.StaticObjects;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import static Model.StaticObjects.MovesExample.initiateAttacks;

public class PokemonSample {

    static HashMap<Integer, Move> attacks = initiateAttacks();

    static ArrayList<Move> bulbizarreMoves = new ArrayList<>();
    static ArrayList<Move> pikachuMoves = new ArrayList<>();
    static ArrayList<Move> carapuceMoves = new ArrayList<>();
    static ArrayList<Move> salamecheMoves = new ArrayList<>();
    static ArrayList<Move> ekansMoves = new ArrayList<>();
    static ArrayList<Move> pidgeyMoves = new ArrayList<>();
    static ArrayList<Move> butterfreeMoves = new ArrayList<>();

    public static Pokemon initiateBulbizarre() {
        if (!bulbizarreMoves.contains(attacks.get(4))) {
            bulbizarreMoves.add(attacks.get(4)); // Vine Whip
        }
        return new Pokemon("bulbasaur", 31, 31, 18, 18, 23, 23, 17, Type.grass, bulbizarreMoves, Status.normal);
    }

    public static Pokemon initiatePikachu() {
        if (!pikachuMoves.contains(attacks.get(0))) pikachuMoves.add(attacks.get(0)); // Tackle
        if (!pikachuMoves.contains(attacks.get(1))) pikachuMoves.add(attacks.get(1)); // Thunder Bolt
        if (!pikachuMoves.contains(attacks.get(2))) pikachuMoves.add(attacks.get(2)); // Thunder Punch

        return new Pokemon("pikachu", 29, 29, 20, 16, 19, 19, 28, Type.electric, pikachuMoves, Status.normal);
    }

    public static Pokemon initiateCarapuce() {
        if (!carapuceMoves.contains(attacks.get(5))) carapuceMoves.add(attacks.get(5)); // Water Gun

        return new Pokemon("squirtle", 31, 31, 17, 23, 19, 22, 17, Type.water, carapuceMoves, Status.normal);
    }

    public static Pokemon initiateSalameche() {
        if (!salamecheMoves.contains(attacks.get(0))) salamecheMoves.add(attacks.get(0)); // Tackle
        if (!salamecheMoves.contains(attacks.get(3))) salamecheMoves.add(attacks.get(3)); // Ember

        return new Pokemon("charmander", 30, 30, 19, 17, 22, 19, 21, Type.fire, salamecheMoves, Status.normal);
    }

    public static Pokemon initiatePidgey() {
        if (!pidgeyMoves.contains(attacks.get(12))) pidgeyMoves.add(attacks.get(6)); // Gust
        if (!pidgeyMoves.contains(attacks.get(0)))  pidgeyMoves.add(attacks.get(0));  // Tackle

        return new Pokemon("pidgey", 30, 30, 21, 19, 16, 16, 26, Type.flying, pidgeyMoves, Status.normal);
    }

    public static Pokemon initiateButterfree() {
        if (!butterfreeMoves.contains(attacks.get(0)))  butterfreeMoves.add(attacks.get(0));  // Tackle
        if (!butterfreeMoves.contains(attacks.get(13))) butterfreeMoves.add(attacks.get(7)); // Confusion
        if (!butterfreeMoves.contains(attacks.get(14))) butterfreeMoves.add(attacks.get(8)); // Bug Bite

        return new Pokemon("butterfree", 35, 35, 16, 17, 25, 23, 21, Type.bug, butterfreeMoves, Status.normal);
    }

    public static Pokemon initiateEkans() {
        if (!ekansMoves.contains(attacks.get(15))) ekansMoves.add(attacks.get(9)); // Bite
        if (!ekansMoves.contains(attacks.get(16))) ekansMoves.add(attacks.get(10)); // Poison Sting

        return new Pokemon("ekans", 34, 34, 21, 17, 16, 20, 24, Type.poison, ekansMoves, Status.normal);
    }
}
