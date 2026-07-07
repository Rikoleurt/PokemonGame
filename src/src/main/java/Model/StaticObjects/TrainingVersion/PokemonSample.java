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
                "vileplume",
                150, 150,
                100, 105, 125, 110, 70,
                Type.grass, Type.poison,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLucarioSwordsDance() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("swords_dance"));
        moves.add(attacks.get("close_combat"));
        moves.add(attacks.get("earthquake"));
        moves.add(attacks.get("aura_sphere"));

        return new Pokemon(
                "lucario",
                145, 145,
                115, 80, 120, 80, 115,
                Type.fighting, Type.steel,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAltariaCottonGuard() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("cotton_guard"));
        moves.add(attacks.get("thunder_wave"));
        moves.add(attacks.get("aerial_ace"));
        moves.add(attacks.get("dragon_claw"));

        return new Pokemon(
                "altaria",
                165, 165,
                90, 90, 90, 115, 100,
                Type.dragon, Type.flying,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirCalmMind() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("calm_mind"));
        moves.add(attacks.get("psychic"));
        moves.add(attacks.get("thunder_bolt"));
        moves.add(attacks.get("energy_ball"));

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

    public static Pokemon initiateMiloticAmnesia() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("amnesia"));
        moves.add(attacks.get("toxic"));
        moves.add(attacks.get("surf"));
        moves.add(attacks.get("water_gun"));

        return new Pokemon(
                "milotic",
                170, 170,
                70, 99, 120, 145, 100,
                Type.water, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBrasegaliTurbo() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("turbo"));
        moves.add(attacks.get("close_combat"));
        moves.add(attacks.get("earthquake"));
        moves.add(attacks.get("flamethrower"));

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

    public static Pokemon initiateSnorlaxSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("tackle"));
        moves.add(attacks.get("bite"));

        return new Pokemon(
                "snorlax",
                235, 235,
                130, 85, 75, 130, 50,
                Type.normal, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateHariyamaSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("brick_break"));
        moves.add(attacks.get("tackle"));

        return new Pokemon(
                "hariyama",
                220, 220,
                140, 80, 55, 80, 55,
                Type.fighting, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateDonphanSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("earthquake"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "donphan",
                165, 165,
                140, 140, 60, 80, 50,
                Type.ground, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMachampPhysicalTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("brick_break"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "machamp",
                165, 165,
                150, 90, 75, 95, 75,
                Type.fighting, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRhydonPhysicalTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("earthquake"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "rhydon",
                180, 180,
                150, 140, 65, 65, 45,
                Type.ground, Type.rock,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateArmaldoPhysicalTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bug_bite"));
        moves.add(attacks.get("rock_tomb"));

        return new Pokemon(
                "armaldo",
                165, 165,
                145, 120, 80, 100, 55,
                Type.rock, Type.bug,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMukSpecialSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("poison_sting"));
        moves.add(attacks.get("bite"));

        return new Pokemon(
                "muk",
                165, 165,
                115, 95, 85, 120, 50,
                Type.poison, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMiloticSpecialSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("surf"));
        moves.add(attacks.get("water_gun"));

        return new Pokemon(
                "milotic",
                170, 170,
                70, 99, 120, 145, 80,
                Type.water, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateClaydolSpecialSetupTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("confusion"));
        moves.add(attacks.get("earthquake"));

        return new Pokemon(
                "claydol",
                155, 155,
                90, 125, 90, 140, 75,
                Type.ground, Type.psychic,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateKadabraSpecialTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("psychic"));
        moves.add(attacks.get("confusion"));

        return new Pokemon(
                "kadabra",
                120, 120,
                55, 50, 145, 90, 125,
                Type.psychic, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMagnetonSpecialTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("thunder_bolt"));
        moves.add(attacks.get("thunder_shock"));

        return new Pokemon(
                "magneton",
                130, 130,
                80, 115, 140, 90, 90,
                Type.electric, Type.steel,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRoseliaSpecialTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("energy_ball"));
        moves.add(attacks.get("poison_sting"));

        return new Pokemon(
                "roselia",
                125, 125,
                70, 65, 130, 105, 90,
                Type.grass, Type.poison,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateJolteonSpeedTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("thunder_bolt"));
        moves.add(attacks.get("tackle"));

        return new Pokemon(
                "jolteon",
                135, 135,
                75, 70, 140, 115, 160,
                Type.electric, null,
                moves,
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCrobatSpeedTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("bite"));
        moves.add(attacks.get("poison_sting"));

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

    public static Pokemon initiateAlakazamSpeedTarget() {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(attacks.get("psychic"));
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
}