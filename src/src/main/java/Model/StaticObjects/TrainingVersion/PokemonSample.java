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
                Type.grass, null,
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
                Type.electric, null,
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
                Type.water, null,
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
                Type.fire, null,
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
                Type.poison, null,
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
                Type.normal, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLucario() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("brick_break"));
        moves.add(attacks.get("aura_sphere"));

        return new Pokemon(
                "lucario",
                145, 145,
                115, 80, 120, 80, 95,
                Type.fighting, Type.steel,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBlissey() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));

        return new Pokemon(
                "blissey",
                330, 330,
                30, 35, 95, 135, 55,
                Type.normal, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAvalugg() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));

        return new Pokemon(
                "avalugg",
                170, 170,
                95, 150, 50, 45, 40,
                Type.ice, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMareep() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("thunder_shock"));
        moves.add(attacks.get("thunder_wave"));

        return new Pokemon(
                "mareep",
                117, 117,
                52, 52, 82, 62, 42,
                Type.electric, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCrobat() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bite"));
        moves.add(attacks.get("poison_sting"));
        moves.add(attacks.get("poison_gas"));

        return new Pokemon(
                "crobat",
                160, 160,
                100, 90, 90, 100, 150,
                Type.poison, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateNostenferToxic() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bite"));
        moves.add(attacks.get("poison_sting"));
        moves.add(attacks.get("toxic"));

        return new Pokemon(
                "crobat",
                160, 160,
                100, 90, 90, 100, 150,
                Type.poison, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateShuckle() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "shuckle",
                95, 95,
                30, 230, 30, 230, 10,
                Type.bug, Type.rock,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateNinetales() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("ember"));
        moves.add(attacks.get("will_o_wisp"));

        return new Pokemon(
                "ninetales",
                148, 148,
                87, 95, 101, 120, 120,
                Type.fire, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMachop() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("brick_break"));

        return new Pokemon(
                "machop",
                145, 145,
                100, 70, 45, 45, 55,
                Type.fighting, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoir() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("confusion"));

        return new Pokemon(
                "gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirConfuseRay() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("confusion"));
        moves.add(attacks.get("confuse_ray"));

        return new Pokemon(
                "gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBrasegali() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("ember"));
        moves.add(attacks.get("brick_break"));

        return new Pokemon(
                "blaziken",
                155, 155,
                140, 90, 130, 90, 100,
                Type.fire, Type.fighting,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAlakazam() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("confusion"));

        return new Pokemon(
                "alakazam",
                130, 130,
                70, 65, 155, 105, 140,
                Type.psychic, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRaflesia() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("energy_ball"));
        moves.add(attacks.get("sleep_powder"));

        return new Pokemon(
                "raflesia",
                150, 150,
                100, 105, 125, 110, 70,
                Type.grass, Type.poison,
                moves,
                Status.normal,
                50
        );
    }
}