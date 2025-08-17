package Model.StaticObjects;

import java.util.ArrayList;
import java.util.HashMap;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Experience;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import static Model.StaticObjects.MovesExample.initiateAttacks;

public class PokemonsExample {
    static HashMap<Integer, Move> attacks = initiateAttacks();
    static ArrayList<Move> bulbizarreMoves = new ArrayList<>();
    static ArrayList<Move> pikachuMoves = new ArrayList<>();
    static ArrayList<Move> carapuceMoves = new ArrayList<>();
    static ArrayList<Move> salamecheMoves = new ArrayList<>();

    public static Pokemon initiateBulbizarre() {
        if(!bulbizarreMoves.contains(attacks.get(4)) || !bulbizarreMoves.contains(attacks.get(6))) {
            bulbizarreMoves.add(attacks.get(4));
            bulbizarreMoves.add(attacks.get(6));
        }
        return new Pokemon("bulbasaur", 31, 31, 18, 18, 23, 23, 17,
                45, 49, 49, 65, 65, 45,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                10, Type.grass, bulbizarreMoves, "female",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiatePikachu() {
        pikachuMoves.add(attacks.get(0));
        pikachuMoves.add(attacks.get(1));
        pikachuMoves.add(attacks.get(2));
        return new Pokemon("pikachu", 29, 29, 20, 16, 19, 19, 28,
                35, 55, 40, 50, 50, 90,
                31, 31, 31, 31, 31, 31,
                0, 0, 0, 0, 0, 252,
                0, 0, 0, 0, 0,
                9, Type.electric, pikachuMoves, "male",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateCarapuce() {
        carapuceMoves.add(attacks.get(5));
        carapuceMoves.add(attacks.get(7));
        return new Pokemon("squirtle", 31, 31, 17, 23, 19, 22, 17,
                44, 48, 65, 50, 64, 43,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                10, Type.water, carapuceMoves, "female",
                0, 100, Experience.Fast, Status.normal);
    }

    public static Pokemon initiateSalameche(){
        salamecheMoves.add(attacks.get(0));
        salamecheMoves.add(attacks.get(3));
        salamecheMoves.add(attacks.get(6));
        return new Pokemon("charmander", 30, 30, 19, 17, 22, 19, 21,
                39, 52, 43, 60, 50, 65,
                10, 10, 10, 10, 10, 10,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                11, Type.fire, salamecheMoves, "male",
                1000, 0, Experience.Fast, Status.normal);
    }
}
