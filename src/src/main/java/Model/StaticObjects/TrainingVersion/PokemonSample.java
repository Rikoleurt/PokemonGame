package Model.StaticObjects.TrainingVersion;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import static Model.StaticObjects.TrainingVersion.MovesSample.initiateAttacks;

public class PokemonSample {

    static HashMap<String, Move> attacks = initiateAttacks();

    public static Pokemon initiateBulbizarre() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("vine_whip"));

        return new Pokemon(
                "bulbasaur",
                112, 112,
                61, 61, 72, 72, 57,
                Type.grass,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiatePikachu() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("thunder_shock"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "pikachu",
                102, 102,
                67, 52, 62, 62, 102,
                Type.electric,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCarapuce() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("water_gun"));

        return new Pokemon(
                "squirtle",
                111, 111,
                61, 77, 62, 76, 55,
                Type.water,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateSalameche() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("ember"));

        return new Pokemon(
                "charmander",
                106, 106,
                64, 55, 72, 62, 77,
                Type.fire,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiatePidgey() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("gust"));

        return new Pokemon(
                "pidgey",
                107, 107,
                57, 52, 47, 47, 68,
                Type.normal, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateButterfree() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("confusion"));
        moves.add(attacks.get("bug_bite"));

        return new Pokemon(
                "butterfree",
                127, 127,
                57, 62, 102, 92, 82,
                Type.bug, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateEkans() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bite"));
        moves.add(attacks.get("poison_sting"));

        return new Pokemon(
                "ekans",
                102, 102,
                72, 56, 52, 66, 67,
                Type.poison,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLeviator() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bite"));
        moves.add(attacks.get("surf"));

        return new Pokemon(
                "gyarados",
                162, 162,
                137, 91, 72, 112, 93,
                Type.water, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateParas() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("vine_whip"));
        moves.add(attacks.get("bug_bite"));

        return new Pokemon(
                "paras",
                102, 102,
                82, 67, 57, 67, 37,
                Type.bug, Type.grass,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateEvoli() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("bite"));

        return new Pokemon(
                "eevee",
                122, 122,
                67, 62, 57, 77, 67,
                Type.normal,
                moves,
                Status.normal,
                50
        );
    }
}