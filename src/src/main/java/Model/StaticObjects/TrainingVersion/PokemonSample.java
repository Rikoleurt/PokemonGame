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

    private static Move requireAttack(String key) {
        Move move = attacks.get(key);

        if (move == null) {
            throw new IllegalArgumentException(
                    "Attack not found in MovesSample: " + key
                            + "\nAvailable attacks: " + attacks.keySet()
            );
        }

        return move;
    }

    private static ArrayList<Move> moves(String... keys) {
        ArrayList<Move> moves = new ArrayList<>();
        for (String key : keys) {
            moves.add(requireAttack(key));
        }
        return moves;
    }

    // region Pokémon offensifs et starters

    public static Pokemon initiateBulbizarre() {
        return new Pokemon(
                "Bulbizarre",
                112, 112,
                61, 61, 72, 72, 57,
                Type.grass, null,
                moves("fouet_lianes", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateSalameche() {
        return new Pokemon(
                "Salamèche",
                106, 106,
                64, 55, 72, 62, 77,
                Type.fire, null,
                moves("flammeche", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCarapuce() {
        return new Pokemon(
                "Carapuce",
                111, 111,
                61, 77, 62, 76, 55,
                Type.water, null,
                moves("pistolet_a_o", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLeviator() {
        return new Pokemon(
                "Léviator",
                162, 162,
                137, 91, 72, 112, 93,
                Type.water, Type.flying,
                moves("cascade", "morsure"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirOffensive() {
        return new Pokemon(
                "Gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, Type.fairy,
                moves("psyko", "tonnerre"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBrasegaliOffensive() {
        return new Pokemon(
                "Braségali",
                155, 155,
                140, 90, 130, 90, 100,
                Type.fire, Type.fighting,
                moves("seisme", "pied_saute"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLugulabre() {
        return new Pokemon(
                "Lugulabre",
                135, 135,
                75, 100, 165, 110, 90,
                Type.ghost, Type.fire,
                moves("lance_flammes", "vibrobscur"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRaichuOffensive() {
        return new Pokemon(
                "Raichu",
                135, 135,
                110, 80, 110, 100, 130,
                Type.electric, null,
                moves("tonnerre", "surf"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateSablaireau() {
        return new Pokemon(
                "Sablaireau",
                150, 150,
                120, 130, 65, 75, 85,
                Type.ground, null,
                moves("seisme", "tranche_nuit"),
                Status.normal,
                50
        );
    }

    // endregion

    // region Pokémon de statut

    public static Pokemon initiateWattouatStatus() {
        return new Pokemon(
                "Wattouat",
                117, 117,
                52, 52, 82, 62, 42,
                Type.electric, null,
                moves("cage_eclair", "eclair", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateNostenferPoisonGas() {
        return new Pokemon(
                "Nostenfer",
                160, 160,
                100, 90, 90, 100, 150,
                Type.poison, Type.flying,
                moves("gaz_toxik", "morsure"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateNostenferToxic() {
        return new Pokemon(
                "Nostenfer",
                160, 160,
                100, 90, 90, 100, 150,
                Type.poison, Type.flying,
                moves("toxik", "morsure"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCaratrocSetupTarget() {
        return new Pokemon(
                "Caratroc",
                120, 120,
                30, 230, 30, 230, 10,
                Type.bug, Type.rock,
                moves("mur_de_fer", "amnesie", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateFeunardStatus() {
        return new Pokemon(
                "Feunard",
                148, 148,
                87, 95, 101, 120, 120,
                Type.fire, null,
                moves("feu_follet", "flammeche", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMachopeurPhysical() {
        return new Pokemon(
                "Machopeur",
                150, 150,
                120, 90, 65, 70, 70,
                Type.fighting, null,
                moves("casse_brique", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirSpecialTarget() {
        return new Pokemon(
                "Gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, Type.fairy,
                moves("psyko", "eco_sphere"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirConfuseRay() {
        return new Pokemon(
                "Gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, Type.fairy,
                moves("onde_folie", "psyko"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateDracolossePhysical() {
        return new Pokemon(
                "Dracolosse",
                166, 166,
                154, 115, 120, 120, 100,
                Type.dragon, Type.flying,
                moves("dracogriffe", "lance_flammes"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAlakazamSpecial() {
        return new Pokemon(
                "Alakazam",
                130, 130,
                70, 65, 155, 105, 140,
                Type.psychic, null,
                moves("psyko", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRaflesiaSleep() {
        return new Pokemon(
                "Raflesia",
                150, 150,
                100, 105, 125, 110, 70,
                Type.grass, Type.poison,
                moves("poudre_dodo", "eco_sphere", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateEvoli() {
        return new Pokemon(
                "Évoli",
                122, 122,
                67, 62, 57, 77, 67,
                Type.normal, null,
                moves("charge", "morsure"),
                Status.normal,
                50
        );
    }

    // endregion

    // region Pokémon de setup

    public static Pokemon initiateLucarioSwordsDance() {
        return new Pokemon(
                "Lucario",
                145, 145,
                115, 80, 120, 80, 115,
                Type.fighting, Type.steel,
                moves("danse_lames", "pied_saute", "seisme", "aurasphere"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAltariaCottonGuard() {
        return new Pokemon(
                "Altaria",
                165, 165,
                90, 90, 90, 115, 100,
                Type.dragon, Type.flying,
                moves("cotogarde", "cage_eclair", "dracogriffe", "aile_d_acier"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirCalmMind() {
        return new Pokemon(
                "Gardevoir",
                143, 143,
                85, 85, 145, 135, 100,
                Type.psychic, Type.fairy,
                moves("plenitude", "psyko", "tonnerre", "eco_sphere"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMiloticAmnesiaBalanced() {
        return new Pokemon(
                "Milobellus",
                190, 190,
                65, 90, 130, 80, 110,
                Type.water, null,
                moves("amnesie", "surf", "laser_glace"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBrasegaliTurboBalanced() {
        return new Pokemon(
                "Braségali",
                165, 165,
                120, 85, 95, 85, 90,
                Type.fire, Type.fighting,
                moves("turbo", "casse_brique", "flammeche"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateSnorlaxSetupTarget() {
        return new Pokemon(
                "Ronflex",
                235, 235,
                130, 85, 75, 130, 50,
                Type.normal, null,
                moves("charge", "morsure"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateHariyamaSetupTarget() {
        return new Pokemon(
                "Hariyama",
                220, 220,
                140, 80, 55, 80, 55,
                Type.fighting, null,
                moves("casse_brique", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateDonphanSetupTarget() {
        return new Pokemon(
                "Donphan",
                165, 165,
                140, 140, 60, 80, 50,
                Type.ground, null,
                moves("seisme", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMachampPhysicalTarget() {
        return new Pokemon(
                "Mackogneur",
                165, 165,
                150, 90, 75, 95, 75,
                Type.fighting, null,
                moves("casse_brique", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRhydonPhysicalTarget() {
        return new Pokemon(
                "Rhinoféros",
                180, 180,
                150, 140, 65, 65, 45,
                Type.ground, Type.rock,
                moves("seisme", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateArmaldoPhysicalTarget() {
        return new Pokemon(
                "Armaldo",
                165, 165,
                145, 120, 80, 100, 55,
                Type.rock, Type.bug,
                moves("lame_de_roc", "tranche_nuit"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMukSpecialSetupTarget() {
        return new Pokemon(
                "Grotadmorv",
                165, 165,
                115, 95, 85, 120, 50,
                Type.poison, null,
                moves("morsure", "charge"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMiloticSpecialSetupTarget() {
        return new Pokemon(
                "Milobellus",
                170, 170,
                70, 99, 120, 145, 80,
                Type.water, null,
                moves("surf", "laser_glace"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateClaydolSpecialSetupTarget() {
        return new Pokemon(
                "Kaorine",
                155, 155,
                90, 125, 90, 140, 75,
                Type.ground, Type.psychic,
                moves("psyko", "seisme"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateKadabraSpecialTargetBalanced() {
        return new Pokemon(
                "Kadabra",
                135, 135,
                35, 55, 105, 85, 105,
                Type.psychic, null,
                moves("psyko"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateVenomothSpecialTargetBalanced() {
        return new Pokemon(
                "Aéromite",
                145, 145,
                45, 70, 105, 90, 80,
                Type.bug, Type.poison,
                moves("vibrobscur"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMisdreavusSpecialTargetBalanced() {
        return new Pokemon(
                "Feuforêve",
                140, 140,
                45, 70, 110, 90, 85,
                Type.ghost, null,
                moves("vibrobscur"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateJolteonSpeedTargetBalanced() {
        return new Pokemon(
                "Voltali",
                150, 150,
                50, 110, 70, 70, 125,
                Type.electric, null,
                moves("eclair"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateElectrodeSpeedTargetBalanced() {
        return new Pokemon(
                "Électrode",
                140, 140,
                50, 110, 65, 70, 130,
                Type.electric, null,
                moves("eclair"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiatePersianSpeedTargetBalanced() {
        return new Pokemon(
                "Persian",
                160, 160,
                70, 110, 55, 65, 120,
                Type.normal, null,
                moves("charge"),
                Status.normal,
                50
        );
    }

    // endregion

    // region Pokémon mixed - Maîtres de Ligue

    public static Pokemon initiateDracolossePeter() {
        return new Pokemon(
                "Dracolosse",
                166, 166,
                154, 115, 120, 120, 100,
                Type.dragon, Type.flying,
                moves("danse_draco", "dracogriffe", "lance_flammes"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLeviatorPeter() {
        return new Pokemon(
                "Léviator",
                162, 162,
                137, 91, 72, 112, 93,
                Type.water, Type.flying,
                moves("danse_draco", "cascade", "crocs_givre"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiatePteraPeter() {
        return new Pokemon(
                "Ptéra",
                155, 155,
                125, 85, 80, 95, 150,
                Type.rock, Type.flying,
                moves("lame_de_roc", "seisme", "crocs_givre"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMagnetonMixed() {
        return new Pokemon(
                "Magnéton",
                145, 145,
                70, 105, 120, 95, 75,
                Type.electric, Type.steel,
                moves("tonnerre", "luminocanon"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLaggronMixed() {
        return new Pokemon(
                "Laggron",
                175, 175,
                130, 110, 105, 110, 80,
                Type.water, Type.ground,
                moves("surf", "seisme"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAlakazamBlue() {
        return new Pokemon(
                "Alakazam",
                130, 130,
                70, 65, 155, 105, 140,
                Type.psychic, null,
                moves("psyko", "exploforce", "plenitude"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateArcaninBlue() {
        return new Pokemon(
                "Arcanin",
                165, 165,
                130, 100, 120, 100, 115,
                Type.fire, null,
                moves("lance_flammes", "morsure"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateRhinoférosBlue() {
        return new Pokemon(
                "Rhinoféros",
                180, 180,
                150, 140, 65, 65, 45,
                Type.ground, Type.rock,
                moves("seisme", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateNoacierMixed() {
        return new Pokemon(
                "Noacier",
                160, 160,
                115, 150, 65, 135, 40,
                Type.grass, Type.steel,
                moves("gyroballe", "eco_sphere", "toxik"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLucarioMixed() {
        return new Pokemon(
                "Lucario",
                145, 145,
                115, 80, 120, 80, 115,
                Type.fighting, Type.steel,
                moves("danse_lames", "casse_brique", "aurasphere"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMetalossePierreRochard() {
        return new Pokemon(
                "Métalosse",
                155, 155,
                155, 150, 115, 110, 90,
                Type.steel, Type.psychic,
                moves("poing_meteore", "seisme", "psyko"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAirmurePierreRochard() {
        return new Pokemon(
                "Airmure",
                140, 140,
                100, 160, 60, 90, 90,
                Type.steel, Type.flying,
                moves("mur_de_fer", "aile_d_acier", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateVacilysPierreRochard() {
        return new Pokemon(
                "Vacilys",
                165, 165,
                101, 117, 101, 127, 63,
                Type.rock, Type.grass,
                moves("eco_sphere", "lame_de_roc", "toxik"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMilobellusMixed() {
        return new Pokemon(
                "Milobellus",
                170, 170,
                70, 99, 120, 145, 100,
                Type.water, null,
                moves("surf", "laser_glace", "toxik"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateCarchacrokCynthia() {
        return new Pokemon(
                "Carchacrok",
                183, 183,
                150, 115, 100, 105, 122,
                Type.dragon, Type.ground,
                moves("danse_lames", "seisme", "dracogriffe"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLucarioCynthia() {
        return new Pokemon(
                "Lucario",
                145, 145,
                115, 80, 120, 80, 115,
                Type.fighting, Type.steel,
                moves("aurasphere", "luminocanon", "danse_lames"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMammochonMixed() {
        return new Pokemon(
                "Mammochon",
                185, 185,
                150, 100, 80, 80, 100,
                Type.ice, Type.ground,
                moves("seisme", "crocs_givre", "lame_de_roc"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateDracolosseMixedTarget() {
        return new Pokemon(
                "Dracolosse",
                166, 166,
                154, 115, 120, 120, 100,
                Type.dragon, Type.flying,
                moves("dracogriffe", "lance_flammes"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateTranchodonIris() {
        return new Pokemon(
                "Tranchodon",
                151, 151,
                167, 110, 80, 90, 117,
                Type.dragon, null,
                moves("danse_draco", "dracogriffe", "seisme"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateDracolosseIris() {
        return new Pokemon(
                "Dracolosse",
                166, 166,
                154, 115, 120, 120, 100,
                Type.dragon, Type.flying,
                moves("danse_draco", "dracogriffe", "lance_flammes"),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLokhlassIris() {
        return new Pokemon(
                "Lokhlass",
                205, 205,
                105, 100, 105, 115, 80,
                Type.water, Type.ice,
                moves("surf", "laser_glace", "tonnerre"),
                Status.normal,
                50
        );
    }

    // endregion

    // region Compatibilité avec anciens noms de méthodes

    public static Pokemon initiateGardevoir() {
        return initiateGardevoirSpecialTarget();
    }

    public static Pokemon initiateBrasegali() {
        return initiateBrasegaliOffensive();
    }

    public static Pokemon initiateMareep() {
        return initiateWattouatStatus();
    }

    public static Pokemon initiateCrobat() {
        return initiateNostenferPoisonGas();
    }

    public static Pokemon initiateShuckle() {
        return initiateCaratrocSetupTarget();
    }

    public static Pokemon initiateNinetales() {
        return initiateFeunardStatus();
    }

    public static Pokemon initiateMachop() {
        return initiateMachopeurPhysical();
    }

    public static Pokemon initiateAlakazam() {
        return initiateAlakazamSpecial();
    }

    public static Pokemon initiateRaflesia() {
        return initiateRaflesiaSleep();
    }
    //endregion

    // region Scénarios expérimentaux 6 contre 6

    // ============================================================================
    // SCÉNARIO 1 : JUAN
    // ============================================================================

    public static Pokemon initiateLovdiscJuan() {
        return new Pokemon(
                "Lovdisc",
                125, 125,
                55, 75, 65, 85, 120,
                Type.water, null,
                moves(
                        "surf",
                        "laser_glace",
                        "cage_eclair",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBarbichaJuan() {
        return new Pokemon(
                "Barbicha",
                175, 175,
                105, 95, 95, 90, 75,
                Type.water, Type.ground,
                moves(
                        "surf",
                        "seisme",
                        "toxik",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiatePhogleurJuan() {
        return new Pokemon(
                "Phogleur",
                180, 180,
                80, 110, 100, 110, 65,
                Type.ice, Type.water,
                moves(
                        "surf",
                        "laser_glace",
                        "toxik",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateColhomardJuan() {
        return new Pokemon(
                "Colhomard",
                150, 150,
                140, 105, 110, 75, 75,
                Type.water, Type.dark,
                moves(
                        "pince_masse",
                        "tranche_nuit",
                        "danse_lames",
                        "toxik"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateHyporoiJuan() {
        return new Pokemon(
                "Hyporoi",
                160, 160,
                115, 115, 115, 115, 105,
                Type.water, Type.dragon,
                moves(
                        "surf",
                        "draco_souffle",
                        "danse_draco",
                        "laser_glace"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLeviatorJuan() {
        return new Pokemon(
                "Léviator",
                175, 175,
                145, 100, 80, 120, 105,
                Type.water, Type.flying,
                moves(
                        "cascade",
                        "crocs_givre",
                        "danse_draco",
                        "cage_eclair"
                ),
                Status.normal,
                50
        );
    }


    // ============================================================================
    // SCÉNARIO 2 : SPECTRA
    // ============================================================================

    public static Pokemon initiateBranetteSpectraPhysical() {
        return new Pokemon(
                "Branette",
                145, 145,
                135, 85, 100, 85, 85,
                Type.ghost, null,
                moves(
                        "ombre_portee",
                        "poing_ombre",
                        "danse_lames",
                        "feu_follet"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateTeraclopeSpectraDefensive() {
        return new Pokemon(
                "Téraclope",
                150, 150,
                90, 165, 85, 165, 55,
                Type.ghost, null,
                moves(
                        "ball_ombre",
                        "poing_ombre",
                        "feu_follet",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateTenefixSpectra() {
        return new Pokemon(
                "Ténéfix",
                145, 145,
                100, 100, 85, 95, 75,
                Type.dark, Type.ghost,
                moves(
                        "ball_ombre",
                        "vibrobscur",
                        "onde_folie",
                        "toxik"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateFeuforeveSpectra() {
        return new Pokemon(
                "Feuforêve",
                145, 145,
                75, 90, 120, 120, 105,
                Type.ghost, null,
                moves(
                        "ball_ombre",
                        "psyko",
                        "onde_folie",
                        "plenitude"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateEctoplasmaSpectra() {
        return new Pokemon(
                "Ectoplasma",
                145, 145,
                80, 80, 150, 100, 130,
                Type.ghost, Type.poison,
                moves(
                        "ball_ombre",
                        "tonnerre",
                        "feu_follet",
                        "plenitude"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMomartikSpectra() {
        return new Pokemon(
                "Momartik",
                145, 145,
                90, 85, 115, 85, 130,
                Type.ice, Type.ghost,
                moves(
                        "ball_ombre",
                        "laser_glace",
                        "cage_eclair",
                        "onde_folie"
                ),
                Status.normal,
                50
        );
    }


    // ============================================================================
    // SCÉNARIO 3 : PIERRE ROCHARD
    // ============================================================================

    public static Pokemon initiateAirmurePierreRochardFinal() {
        return new Pokemon(
                "Airmure",
                155, 155,
                105, 165, 65, 100, 95,
                Type.steel, Type.flying,
                moves(
                        "aeropique",
                        "aile_d_acier",
                        "mur_de_fer",
                        "toxik"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateVacilysPierreRochardFinal() {
        return new Pokemon(
                "Vacilys",
                180, 180,
                110, 125, 115, 140, 65,
                Type.rock, Type.grass,
                moves(
                        "eco_sphere",
                        "lame_de_roc",
                        "toxik",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateArmaldoPierreRochardFinal() {
        return new Pokemon(
                "Armaldo",
                170, 170,
                150, 130, 85, 105, 65,
                Type.rock, Type.bug,
                moves(
                        "plaie_croix",
                        "eboulement",
                        "danse_lames",
                        "toxik"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateKaorinePierreRochardFinal() {
        return new Pokemon(
                "Kaorine",
                165, 165,
                90, 130, 115, 145, 85,
                Type.ground, Type.psychic,
                moves(
                        "psyko",
                        "telluriforce",
                        "plenitude",
                        "cage_eclair"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGalekingPierreRochardFinal() {
        return new Pokemon(
                "Galeking",
                175, 175,
                145, 170, 80, 90, 70,
                Type.steel, Type.rock,
                moves(
                        "queue_de_fer",
                        "lame_de_roc",
                        "seisme",
                        "mur_de_fer"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMetalossePierreRochardFinal() {
        return new Pokemon(
                "Métalosse",
                170, 170,
                160, 155, 120, 120, 100,
                Type.steel, Type.psychic,
                moves(
                        "poing_meteore",
                        "seisme",
                        "psyko",
                        "danse_lames"
                ),
                Status.normal,
                50
        );
    }



    public static Pokemon initiateJungkoEvaluation() {
        return new Pokemon(
                "Jungko",
                155, 155,
                105, 85, 135, 100, 140,
                Type.grass, null,
                moves(
                        "eco_sphere",
                        "dracogriffe",
                        "toxik",
                        "danse_lames"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateBrasegaliEvaluation() {
        return new Pokemon(
                "Braségali",
                165, 165,
                135, 90, 125, 90, 110,
                Type.fire, Type.fighting,
                moves(
                        "lance_flammes",
                        "casse_brique",
                        "danse_lames",
                        "feu_follet"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateLaggronEvaluation() {
        return new Pokemon(
                "Laggron",
                185, 185,
                135, 120, 105, 110, 75,
                Type.water, Type.ground,
                moves(
                        "surf",
                        "seisme",
                        "toxik",
                        "amnesie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateGardevoirEvaluation() {
        return new Pokemon(
                "Gardevoir",
                155, 155,
                80, 90, 150, 140, 105,
                Type.psychic, Type.fairy,
                moves(
                        "psyko",
                        "tonnerre",
                        "plenitude",
                        "onde_folie"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateMagnetonEvaluation() {
        return new Pokemon(
                "Magnéton",
                150, 150,
                75, 115, 135, 105, 85,
                Type.electric, Type.steel,
                moves(
                        "tonnerre",
                        "luminocanon",
                        "cage_eclair",
                        "mur_de_fer"
                ),
                Status.normal,
                50
        );
    }

    public static Pokemon initiateAbsolEvaluation() {
        return new Pokemon(
                "Absol",
                155, 155,
                150, 90, 90, 85, 105,
                Type.dark, null,
                moves(
                        "tranche_nuit",
                        "lame_de_roc",
                        "danse_lames",
                        "cage_eclair"
                ),
                Status.normal,
                50
        );
    }

    // endregion
}
