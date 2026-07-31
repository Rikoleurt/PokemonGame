package Model.StaticObjects.TrainingVersion;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.SetUpMove;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

import java.util.HashMap;
import java.util.Map;

public class MovesSample {

    private static final HashMap<String, Move> attackDB = new HashMap<>();
    private static final HashMap<String, Integer> IDS_BY_DISPLAY_NAME = new HashMap<>();

    private static void registerMove(
            int id,
            Move move,
            String... keys
    ) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null");
        }
        if (id < 0 || id >= 255) {
            throw new IllegalArgumentException("Move ID must be between 0 and 254: " + id);
        }
        if (IDS_BY_DISPLAY_NAME.containsValue(id)) {
            throw new IllegalArgumentException("Move ID already registered: " + id);
        }
        if (IDS_BY_DISPLAY_NAME.containsKey(move.getName())) {
            throw new IllegalArgumentException("Move already registered: " + move.getName());
        }

        IDS_BY_DISPLAY_NAME.put(move.getName(), id);

        for (String key : keys) {
            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Move key cannot be blank");
            }
            if (attackDB.containsKey(key)) {
                throw new IllegalArgumentException("Move key already registered: " + key);
            }
            attackDB.put(key, move);
        }
    }

    public static synchronized HashMap<String, Move> initiateAttacks() {
        // Rebuild the templates on every call so each Pokémon can receive
        // independent Move instances (especially independent PP values).
        attackDB.clear();
        IDS_BY_DISPLAY_NAME.clear();

        // region Attaques offensives

        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40, 40);
        Attack flammeche = new Attack(
                "Flammèche",
                40,
                100,
                Type.fire,
                AttackMode.special,
                40,
                40,
                Status.burned,
                0.10f
        );
        Attack fouetLianes = new Attack("Fouet Lianes", 45, 100, Type.grass, AttackMode.physical, 25, 25);
        Attack pistoletAO = new Attack("Pistolet à O", 40, 100, Type.water, AttackMode.special, 25, 25);
        Attack morsure = new Attack("Morsure", 60, 100, Type.dark, AttackMode.physical, 25, 25);
        Attack eclair = new Attack(
                "Éclair",
                40,
                100,
                Type.electric,
                AttackMode.special,
                30,
                30,
                Status.paralyzed,
                0.10f
        );
        Attack tonnerre = new Attack(
                "Tonnerre",
                90,
                100,
                Type.electric,
                AttackMode.special,
                20,
                20,
                Status.paralyzed,
                0.10f
        );
        Attack surf = new Attack("Surf", 90, 100, Type.water, AttackMode.special, 15, 15);
        Attack cascade = new Attack("Cascade", 80, 100, Type.water, AttackMode.physical, 15, 15);
        Attack queueDeFer = new Attack("Queue de Fer", 100, 100, Type.steel, AttackMode.physical, 15, 15);
        Attack psyko = new Attack("Psyko", 90, 100, Type.psychic, AttackMode.special, 10, 10);
        Attack ecoSphere = new Attack("Éco-Sphère", 90, 100, Type.grass, AttackMode.special, 10, 10);
        Attack rayonGemme = new Attack("Rayon Gemme", 90, 100, Type.rock, AttackMode.special, 10, 10);
        Attack seisme = new Attack("Séisme", 100, 100, Type.ground, AttackMode.physical, 10, 10);
        Attack piedSaute = new Attack("Pied Sauté", 100, 100, Type.fighting, AttackMode.physical, 10, 10);
        Attack casseBrique = new Attack("Casse-Brique", 75, 100, Type.fighting, AttackMode.physical, 15, 15);
        Attack aurasphere = new Attack("Aurasphère", 80, 100, Type.fighting, AttackMode.special, 20, 20);
        Attack exploforce = new Attack("Exploforce", 120, 70, Type.fighting, AttackMode.special, 5, 5);
        Attack dracogriffe = new Attack("Dracogriffe", 80, 100, Type.dragon, AttackMode.physical, 15, 15);
        Attack lanceFlammes = new Attack(
                "Lance-Flammes",
                90,
                100,
                Type.fire,
                AttackMode.special,
                15,
                15,
                Status.burned,
                0.10f
        );
        Attack crocsGivre = new Attack("Crocs Givre", 65, 95, Type.ice, AttackMode.physical, 15, 15);
        Attack lameDeRoc = new Attack("Lame de Roc", 100, 100, Type.rock, AttackMode.physical, 5, 5);
        Attack poingMeteore = new Attack("Poing Météore", 90, 100, Type.steel, AttackMode.physical, 10, 10);
        Attack aileDAcier = new Attack("Aile d'Acier", 70, 100, Type.steel, AttackMode.physical, 25, 25);
        Attack telluriforce = new Attack("Telluriforce", 90, 100, Type.ground, AttackMode.special, 10, 10);
        Attack trancheNuit = new Attack("Tranche-Nuit", 70, 100, Type.dark, AttackMode.physical, 15, 15);
        Attack laserGlace = new Attack("Laser Glace", 90, 100, Type.ice, AttackMode.special, 10, 10);
        Attack vibrobscur = new Attack("Vibrobscur", 80, 100, Type.dark, AttackMode.special, 15, 15);
        Attack luminocanon = new Attack("Luminocanon", 80, 100, Type.steel, AttackMode.special, 10, 10);
        Attack eclatMagique = new Attack("Éclat Magique", 80, 100, Type.fairy, AttackMode.special, 10, 10);
        Attack gyroballe = new Attack("Gyroballe", 80, 100, Type.steel, AttackMode.physical, 10, 10);
        Attack ballOmbre = new Attack(
                "Ball'Ombre",
                80,
                100,
                Type.ghost,
                AttackMode.special,
                15,
                15
        );

        Attack ombrePortee = new Attack(
                "Ombre Portée",
                40,
                100,
                Type.ghost,
                AttackMode.physical,
                30,
                30
        );

        Attack pinceMasse = new Attack(
                "Pince-Masse",
                100,
                90,
                Type.water,
                AttackMode.physical,
                10,
                10
        );

        Attack dracoSouffle = new Attack(
                "Draco-Souffle",
                60,
                100,
                Type.dragon,
                AttackMode.special,
                20,
                20,
                Status.paralyzed,
                0.30f
        );

        Attack aeropique = new Attack(
                "Aéropique",
                60,
                100,
                Type.flying,
                AttackMode.physical,
                20,
                20
        );

        Attack eboulement = new Attack(
                "Éboulement",
                75,
                90,
                Type.rock,
                AttackMode.physical,
                10,
                10
        );

        Attack plaieCroix = new Attack(
                "Plaie-Croix",
                80,
                100,
                Type.bug,
                AttackMode.physical,
                15,
                15
        );

        Attack poingOmbre = new Attack(
                "Poing Ombre",
                60,
                100,
                Type.ghost,
                AttackMode.physical,
                20,
                20
        );
        // endregion

        // region Attaques de statut

        StatusAttack cageEclair = new StatusAttack("Cage-Éclair", 100, Status.paralyzed, Type.electric, AttackMode.status, 20, 20);
        StatusAttack gazToxik = new StatusAttack("Gaz Toxik", 100, Status.poisoned, Type.poison, AttackMode.status, 40, 40);
        StatusAttack toxik = new StatusAttack("Toxik", 100, Status.badlyPoisoned, Type.poison, AttackMode.status, 10, 10);
        StatusAttack feuFollet = new StatusAttack("Feu Follet", 100, Status.burned, Type.fire, AttackMode.status, 15, 15);
        StatusAttack ondeFolie = new StatusAttack("Onde Folie", 100, Status.confused, Type.ghost, AttackMode.status, 10, 10);
        StatusAttack poudreDodo = new StatusAttack("Poudre Dodo", 100, Status.asleep, Type.grass, AttackMode.status, 15, 15);

        // endregion

        // region Attaques de setup

        SetUpMove danseLames = new SetUpMove(
                "Danse-Lames",
                Map.of("atk", 2),
                Type.normal,
                AttackMode.status,
                20,
                20,
                true
        );
        SetUpMove cotogarde = new SetUpMove(
                "Cotogarde",
                Map.of("def", 3),
                Type.grass,
                AttackMode.status,
                10,
                10,
                true
        );
        SetUpMove plenitude = new SetUpMove(
                "Plénitude",
                Map.of("atkSpe", 1, "defSpe", 1),
                Type.psychic,
                AttackMode.status,
                20,
                20,
                true
        );
        SetUpMove amnesie = new SetUpMove(
                "Amnésie",
                Map.of("defSpe", 2),
                Type.psychic,
                AttackMode.status,
                20,
                20,
                true
        );
        SetUpMove danseDraco = new SetUpMove(
                "Danse Draco",
                Map.of("atk", 1, "speed", 1),
                Type.dragon,
                AttackMode.status,
                20,
                20,
                true
        );
        SetUpMove murDeFer = new SetUpMove(
                "Mur de Fer",
                Map.of("def", 2),
                Type.steel,
                AttackMode.status,
                15,
                15,
                true
        );
        SetUpMove turbo = new SetUpMove(
                "Turbo",
                Map.of("speed", 1),
                Type.fighting,
                AttackMode.status,
                20,
                20,
                true
        );

        // endregion

        // region Enregistrement des attaques

        registerMove(0, charge,
                "charge",
                "tackle"
        );

        registerMove(1, flammeche,
                "flammeche",
                "ember"
        );

        registerMove(2, fouetLianes,
                "fouet_lianes",
                "vine_whip"
        );

        registerMove(3, pistoletAO,
                "pistolet_a_o",
                "water_gun"
        );

        registerMove(4, morsure,
                "morsure",
                "bite"
        );

        registerMove(5, eclair,
                "eclair",
                "thunder_shock"
        );

        registerMove(6, tonnerre,
                "tonnerre",
                "thunder_bolt"
        );

        registerMove(7, surf,
                "surf"
        );

        registerMove(8, cascade,
                "cascade",
                "waterfall"
        );

        registerMove(9, queueDeFer,
                "queue_de_fer",
                "iron_tail"
        );

        registerMove(10, psyko,
                "psyko",
                "psychic"
        );

        registerMove(11, ecoSphere,
                "eco_sphere",
                "energy_ball"
        );

        registerMove(12, rayonGemme,
                "rayon_gemme",
                "power_gem"
        );

        registerMove(13, seisme,
                "seisme",
                "earthquake"
        );

        registerMove(14, piedSaute,
                "pied_saute",
                "high_jump_kick"
        );

        registerMove(15, casseBrique,
                "casse_brique",
                "brick_break"
        );

        registerMove(16, aurasphere,
                "aurasphere",
                "aura_sphere"
        );

        registerMove(17, exploforce,
                "exploforce",
                "focus_blast"
        );

        registerMove(18, dracogriffe,
                "dracogriffe",
                "dragon_claw"
        );

        registerMove(19, lanceFlammes,
                "lance_flammes",
                "flamethrower"
        );

        registerMove(20, crocsGivre,
                "crocs_givre",
                "ice_fang"
        );

        registerMove(21, lameDeRoc,
                "lame_de_roc",
                "stone_edge"
        );

        registerMove(22, poingMeteore,
                "poing_meteore",
                "meteor_mash"
        );

        registerMove(23, aileDAcier,
                "aile_d_acier",
                "steel_wing"
        );

        registerMove(24, telluriforce,
                "telluriforce",
                "earth_power"
        );

        registerMove(25, trancheNuit,
                "tranche_nuit",
                "night_slash"
        );

        registerMove(26, laserGlace,
                "laser_glace",
                "ice_beam"
        );

        registerMove(27, vibrobscur,
                "vibrobscur",
                "dark_pulse"
        );

        registerMove(28, luminocanon,
                "luminocanon",
                "flash_cannon"
        );

        registerMove(29, eclatMagique,
                "eclat_magique",
                "dazzling_gleam"
        );

        registerMove(30, gyroballe,
                "gyroballe",
                "gyro_ball"
        );

        registerMove(31, cageEclair,
                "cage_eclair",
                "thunder_wave"
        );

        registerMove(32, gazToxik,
                "gaz_toxik",
                "poison_gas"
        );

        registerMove(33, toxik,
                "toxik",
                "toxic"
        );

        registerMove(34, feuFollet,
                "feu_follet",
                "will_o_wisp"
        );

        registerMove(35, ondeFolie,
                "onde_folie",
                "confuse_ray"
        );

        registerMove(36, poudreDodo,
                "poudre_dodo",
                "sleep_powder"
        );

        registerMove(37, danseLames,
                "danse_lames",
                "swords_dance"
        );

        registerMove(38, cotogarde,
                "cotogarde",
                "cotton_guard"
        );

        registerMove(39, plenitude,
                "plenitude",
                "calm_mind"
        );

        registerMove(40, amnesie,
                "amnesie",
                "amnesia"
        );

        registerMove(41, danseDraco,
                "danse_draco",
                "dragon_dance"
        );

        registerMove(42, murDeFer,
                "mur_de_fer",
                "iron_defense"
        );

        registerMove(43, turbo,
                "turbo"
        );

        registerMove(44, ballOmbre,
                "ball_ombre",
                "shadow_ball"
        );

        registerMove(45, ombrePortee,
                "ombre_portee",
                "shadow_sneak"
        );

        registerMove(46, pinceMasse,
                "pince_masse",
                "crabhammer"
        );

        registerMove(47, dracoSouffle,
                "draco_souffle",
                "dragon_breath"
        );

        registerMove(48, aeropique,
                "aeropique",
                "aerial_ace"
        );

        registerMove(49, eboulement,
                "eboulement",
                "rock_slide"
        );

        registerMove(50, plaieCroix,
                "plaie_croix",
                "x_scissor"
        );

        registerMove(51, poingOmbre,
                "poing_ombre",
                "shadow_punch"
        );


    //endregion

        // Return a snapshot: later initialisations cannot alter the caller's lookup map.
        return new HashMap<>(attackDB);
    }

    public static int getIdByName(String attackName) {
        if (attackName == null || attackName.isBlank()) {
            return 255;
        }

        ensureInitialized();

        return IDS_BY_DISPLAY_NAME.getOrDefault(
                attackName,
                255
        );
    }

    public static int getIdByMove(Move move) {
        if (move == null) {
            return 255;
        }

        return getIdByName(move.getName());
    }

    private static void ensureInitialized() {
        if (attackDB.isEmpty()) {
            initiateAttacks();
        }
    }
}
