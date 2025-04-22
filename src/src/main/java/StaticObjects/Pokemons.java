package StaticObjects;

import java.util.ArrayList;
import java.util.HashMap;
import Pokemon.Move;
import Pokemon.Pokemon;
import Pokemon.PokemonEnum.Experience;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;

import static StaticObjects.Moves.initiateAttacks;

public class Pokemons {
    static HashMap<Integer, Move> attacks = initiateAttacks();
    static ArrayList<Move> bulbizarreMoves = new ArrayList<>();
    static ArrayList<Move> pikachuMoves = new ArrayList<>();
    static ArrayList<Move> carapuceMoves = new ArrayList<>();
    static ArrayList<Move> salamecheMoves = new ArrayList<>();


    public static Pokemon initiateBulbizarre() {
        bulbizarreMoves.add(attacks.get(4));
        bulbizarreMoves.add(attacks.get(6));
        return new Pokemon("Bulbizarre", 45, 45, 49, 49, 49, 49, 65, 65, 65, 65,
                45, 45, 10, Type.grass, bulbizarreMoves, 0, 0, 0, 0, 0,
                Status.normal, "female", 0, 100, Experience.Fast);
    }
    public static Pokemon initiatePikachu() {
        pikachuMoves.add(attacks.get(0));
        pikachuMoves.add(attacks.get(1));
        pikachuMoves.add(attacks.get(2));
        return new Pokemon("Pikachu", 35, 35, 55, 55, 40, 40, 50, 50, 50, 50,
                90, 90, 9, Type.electric, pikachuMoves, 0, 0, 0, 0, 0,
                Status.normal, "male", 0, 100, Experience.Fast);
    }

    public static Pokemon initiateCarapuce() {
        carapuceMoves.add(attacks.get(5));
        carapuceMoves.add(attacks.get(7));
        return new Pokemon("Carapuce", 44, 44, 48, 48, 65, 65, 50, 50, 64, 64,
                43, 43, 10, Type.water, carapuceMoves, 0, 0, 0, 0, 0,
                Status.normal, "female", 0, 100, Experience.Fast);
    }

    public static Pokemon initiateSalameche(){
        salamecheMoves.add(attacks.get(0));
        salamecheMoves.add(attacks.get(3));
        salamecheMoves.add(attacks.get(6));
        return new Pokemon("Salamèche", 39, 39, 52, 52, 43, 43, 60, 60, 50, 50,
                65, 65, 11, Type.fire, salamecheMoves, 0, 0, 0, 0, 0,
                Status.normal, "male", 0, 100, Experience.Fast);
    }
}
