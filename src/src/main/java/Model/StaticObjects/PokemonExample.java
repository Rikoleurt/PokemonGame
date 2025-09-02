package Model.StaticObjects;

import java.util.ArrayList;
import java.util.HashMap;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Experience;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import static Model.StaticObjects.MovesExample.initiateAttacks;

public class PokemonExample {
    static HashMap<Integer, Move> attacks = initiateAttacks();
    static ArrayList<Move> bulbizarreMoves = new ArrayList<>();
    static ArrayList<Move> pikachuMoves = new ArrayList<>();
    static ArrayList<Move> carapuceMoves = new ArrayList<>();
    static ArrayList<Move> salamecheMoves = new ArrayList<>();
    static ArrayList<Move> ekansMoves = new ArrayList<>();
    static ArrayList<Move> pidgeyMoves = new ArrayList<>();
    static ArrayList<Move> butterfreeMoves = new ArrayList<>();

    public static Pokemon initiateBulbizarre() {
        if(!bulbizarreMoves.contains(attacks.get(4)) || !bulbizarreMoves.contains(attacks.get(6))) {
            bulbizarreMoves.add(attacks.get(4));
//            bulbizarreMoves.add(attacks.get(6));
        }
        return new Pokemon("bulbasaur", 31, 31, 18, 18, 23, 23, 22,
                45, 49, 49, 65, 65, 45,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                10, Type.grass, bulbizarreMoves, "female",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiatePikachu() {
        if(!pikachuMoves.contains(attacks.get(0)) || !pikachuMoves.contains(attacks.get(1)) || !pikachuMoves.contains(attacks.get(2))) {
            pikachuMoves.add(attacks.get(0));
            pikachuMoves.add(attacks.get(1));
            pikachuMoves.add(attacks.get(2));
        }
        return new Pokemon("pikachu", 29, 29, 20, 16, 19, 19, 28,
                35, 55, 40, 50, 50, 90,
                31, 31, 31, 31, 31, 31,
                0, 0, 0, 0, 0, 252,
                0, 0, 0, 0, 0,
                9, Type.electric, pikachuMoves, "male",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateCarapuce() {
        if(!carapuceMoves.contains(attacks.get(5)) || !carapuceMoves.contains(attacks.get(7))) {
            carapuceMoves.add(attacks.get(5));
            carapuceMoves.add(attacks.get(7));
        }
        return new Pokemon("squirtle", 31, 31, 17, 23, 19, 22, 22,
                44, 48, 65, 50, 64, 43,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                10, Type.water, carapuceMoves, "female",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateSalameche(){
        if(!salamecheMoves.contains(attacks.get(0)) || !salamecheMoves.contains(attacks.get(3)) || !salamecheMoves.contains(attacks.get(6))) {
            salamecheMoves.add(attacks.get(0));
            salamecheMoves.add(attacks.get(3));
            salamecheMoves.add(attacks.get(6));
        }
        return new Pokemon("charmander", 30, 1, 19, 17, 22, 19, 21,
                39, 52, 43, 60, 50, 65,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                11, Type.fire, salamecheMoves, "male",
                1000, 0, Experience.Fast, Status.normal);
    }

    public static Pokemon initiatePidgey() {
        if(!pidgeyMoves.contains(attacks.get(12)) || !pidgeyMoves.contains(attacks.get(0))) {
            pidgeyMoves.add(attacks.get(12)); // Gust
            pidgeyMoves.add(attacks.get(0));// Tackle
        }

        return new Pokemon("pidgey", 30, 30, 21, 19, 16, 16, 26,
                33, 21, 19, 16, 16, 26,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                11, Type.flying, pidgeyMoves, "male",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateButterfree() {
        if(!butterfreeMoves.contains(attacks.get(13)) || !butterfreeMoves.contains(attacks.get(0)) || !butterfreeMoves.contains(attacks.get(14)) || !butterfreeMoves.contains(attacks.get(17))) {
            butterfreeMoves.add(attacks.get(0)); // Tackle
            butterfreeMoves.add(attacks.get(13)); // Confusion
            butterfreeMoves.add(attacks.get(14)); // Bug bite
            butterfreeMoves.add(attacks.get(17)); // Stun-spore
        }

        return new Pokemon("butterfree", 35, 35, 16, 17, 25, 23, 21,
                60, 45, 50, 90, 80, 70,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                11, Type.bug, butterfreeMoves, "female",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateEkans() {
        if (!ekansMoves.contains(attacks.get(16)) || !ekansMoves.contains(attacks.get(17))) {
            ekansMoves.add(attacks.get(16)); // Bite
            ekansMoves.add(attacks.get(17)); // Poison Sting
        }

        return new Pokemon("ekans", 34, 34, 21, 17, 16, 20, 24,
                35, 60, 44, 40, 54, 55,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                12, Type.poison, ekansMoves, "male",
                0, 100, Experience.Medium, Status.normal);
    }
}
