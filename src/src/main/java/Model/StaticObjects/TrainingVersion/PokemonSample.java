package Model.StaticObjects.TrainingVersion;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import static Model.StaticObjects.TrainingVersion.MovesSample.initiateAttacks;

public class PokemonSample {

    static HashMap<Integer, Move> attacks = initiateAttacks();

    static ArrayList<Move> bulbizarreMoves = new ArrayList<>();
    static ArrayList<Move> pikachuMoves = new ArrayList<>();
    static ArrayList<Move> carapuceMoves = new ArrayList<>();
    static ArrayList<Move> salamecheMoves = new ArrayList<>();
    static ArrayList<Move> ekansMoves = new ArrayList<>();
    static ArrayList<Move> pidgeyMoves = new ArrayList<>();
    static ArrayList<Move> butterfreeMoves = new ArrayList<>();
    static ArrayList<Move> laggronMoves = new ArrayList<>();
    static ArrayList<Move> cizayoxMoves = new ArrayList<>();
    static ArrayList<Move> jungkoMoves = new ArrayList<>();

    public static Pokemon initiateBulbizarre() {
        if (!bulbizarreMoves.contains(attacks.get(0))) bulbizarreMoves.add(attacks.get(0)); // Tackle
        if (!bulbizarreMoves.contains(attacks.get(4))) bulbizarreMoves.add(attacks.get(4)); // Vine Whip

        return new Pokemon(
                "bulbasaur",
                30, 30,
                16, 16, 19, 19, 15,
                Type.grass,
                bulbizarreMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiatePikachu() {
        if (!pikachuMoves.contains(attacks.get(0))) pikachuMoves.add(attacks.get(0));   // Tackle
        if (!pikachuMoves.contains(attacks.get(11))) pikachuMoves.add(attacks.get(11)); // Thunder Shock

        return new Pokemon(
                "pikachu",
                28, 28,
                17, 12, 16, 14, 24,
                Type.electric,
                pikachuMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiateCarapuce() {
        if (!carapuceMoves.contains(attacks.get(0))) carapuceMoves.add(attacks.get(0)); // Tackle
        if (!carapuceMoves.contains(attacks.get(5))) carapuceMoves.add(attacks.get(5)); // Water Gun

        return new Pokemon(
                "squirtle",
                30, 30,
                16, 19, 16, 19, 15,
                Type.water,
                carapuceMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiateSalameche() {
        if (!salamecheMoves.contains(attacks.get(0))) salamecheMoves.add(attacks.get(0)); // Tackle
        if (!salamecheMoves.contains(attacks.get(3))) salamecheMoves.add(attacks.get(3)); // Ember

        return new Pokemon(
                "charmander",
                29, 29,
                16, 15, 18, 16, 19,
                Type.fire,
                salamecheMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiatePidgey() {
        if (!pidgeyMoves.contains(attacks.get(0))) pidgeyMoves.add(attacks.get(0)); // Tackle
        if (!pidgeyMoves.contains(attacks.get(6))) pidgeyMoves.add(attacks.get(6)); // Gust

        return new Pokemon(
                "pidgey",
                29, 29,
                15, 14, 13, 13, 17,
                Type.normal, Type.flying,
                pidgeyMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiateButterfree() {
        if (!butterfreeMoves.contains(attacks.get(0))) butterfreeMoves.add(attacks.get(0)); // Tackle
        if (!butterfreeMoves.contains(attacks.get(7))) butterfreeMoves.add(attacks.get(7)); // Confusion
        if (!butterfreeMoves.contains(attacks.get(8))) butterfreeMoves.add(attacks.get(8)); // Bug Bite

        return new Pokemon(
                "butterfree",
                33, 33,
                15, 16, 24, 22, 20,
                Type.bug, Type.flying,
                butterfreeMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiateEkans() {
        if (!ekansMoves.contains(attacks.get(9))) ekansMoves.add(attacks.get(9));   // Bite
        if (!ekansMoves.contains(attacks.get(10))) ekansMoves.add(attacks.get(10)); // Poison Sting

        return new Pokemon(
                "ekans",
                28, 28,
                18, 15, 14, 17, 17,
                Type.poison,
                ekansMoves,
                Status.normal,
                10
        );
    }

    public static Pokemon initiateLaggron() {
        if(!laggronMoves.contains(attacks.get(15))) laggronMoves.add(attacks.get(15)); // Surf

        return new Pokemon(
                "laggron",
                167, 167,
                122, 102, 97, 102, 72,
                Type.water, Type.ground,
                laggronMoves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCizayox() {
        if(!cizayoxMoves.contains(attacks.get(14))) cizayoxMoves.add(attacks.get(14)); // Iron Head

        return new Pokemon(
                "cizayox",
                137, 137,
                142, 112, 67, 92, 77,
                Type.bug, Type.steel,
                cizayoxMoves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateJungko() {
        if (!jungkoMoves.contains(attacks.get(12))) jungkoMoves.add(attacks.get(12)); // Leaf Blade
        if (!jungkoMoves.contains(attacks.get(13))) jungkoMoves.add(attacks.get(13)); // Energy Ball

        return new Pokemon(
                "jungko",
                137, 137,
                97, 77, 117, 97, 132,
                Type.grass,
                jungkoMoves,
                Status.normal,
                50
        );
    }
}