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

    public static HashMap<String, Move> initiateAttacks() {
        HashMap<String, Move> attackDB = new HashMap<>();

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

        attackDB.put("charge", charge);
        attackDB.put("tackle", charge);

        attackDB.put("flammeche", flammeche);
        attackDB.put("ember", flammeche);

        attackDB.put("fouet_lianes", fouetLianes);
        attackDB.put("vine_whip", fouetLianes);

        attackDB.put("pistolet_a_o", pistoletAO);
        attackDB.put("water_gun", pistoletAO);

        attackDB.put("morsure", morsure);
        attackDB.put("bite", morsure);

        attackDB.put("eclair", eclair);
        attackDB.put("thunder_shock", eclair);

        attackDB.put("tonnerre", tonnerre);
        attackDB.put("thunder_bolt", tonnerre);

        attackDB.put("surf", surf);
        attackDB.put("cascade", cascade);
        attackDB.put("queue_de_fer", queueDeFer);
        attackDB.put("iron_tail", queueDeFer);

        attackDB.put("psyko", psyko);
        attackDB.put("psychic", psyko);

        attackDB.put("eco_sphere", ecoSphere);
        attackDB.put("energy_ball", ecoSphere);

        attackDB.put("rayon_gemme", rayonGemme);
        attackDB.put("power_gem", rayonGemme);

        attackDB.put("seisme", seisme);
        attackDB.put("earthquake", seisme);

        attackDB.put("pied_saute", piedSaute);
        attackDB.put("high_jump_kick", piedSaute);

        attackDB.put("casse_brique", casseBrique);
        attackDB.put("brick_break", casseBrique);

        attackDB.put("aurasphere", aurasphere);
        attackDB.put("aura_sphere", aurasphere);

        attackDB.put("exploforce", exploforce);
        attackDB.put("focus_blast", exploforce);

        attackDB.put("dracogriffe", dracogriffe);
        attackDB.put("dragon_claw", dracogriffe);

        attackDB.put("lance_flammes", lanceFlammes);
        attackDB.put("flamethrower", lanceFlammes);

        attackDB.put("crocs_givre", crocsGivre);
        attackDB.put("ice_fang", crocsGivre);

        attackDB.put("lame_de_roc", lameDeRoc);
        attackDB.put("stone_edge", lameDeRoc);

        attackDB.put("poing_meteore", poingMeteore);
        attackDB.put("meteor_mash", poingMeteore);

        attackDB.put("aile_d_acier", aileDAcier);
        attackDB.put("steel_wing", aileDAcier);

        attackDB.put("telluriforce", telluriforce);
        attackDB.put("earth_power", telluriforce);

        attackDB.put("tranche_nuit", trancheNuit);
        attackDB.put("night_slash", trancheNuit);

        attackDB.put("laser_glace", laserGlace);
        attackDB.put("ice_beam", laserGlace);

        attackDB.put("vibrobscur", vibrobscur);
        attackDB.put("dark_pulse", vibrobscur);

        attackDB.put("luminocanon", luminocanon);
        attackDB.put("flash_cannon", luminocanon);

        attackDB.put("eclat_magique", eclatMagique);
        attackDB.put("dazzling_gleam", eclatMagique);

        attackDB.put("gyroballe", gyroballe);
        attackDB.put("gyro_ball", gyroballe);

        attackDB.put("cage_eclair", cageEclair);
        attackDB.put("thunder_wave", cageEclair);

        attackDB.put("gaz_toxik", gazToxik);
        attackDB.put("poison_gas", gazToxik);

        attackDB.put("toxik", toxik);
        attackDB.put("toxic", toxik);

        attackDB.put("feu_follet", feuFollet);
        attackDB.put("will_o_wisp", feuFollet);

        attackDB.put("onde_folie", ondeFolie);
        attackDB.put("confuse_ray", ondeFolie);

        attackDB.put("poudre_dodo", poudreDodo);
        attackDB.put("sleep_powder", poudreDodo);

        attackDB.put("danse_lames", danseLames);
        attackDB.put("swords_dance", danseLames);

        attackDB.put("cotogarde", cotogarde);
        attackDB.put("cotton_guard", cotogarde);

        attackDB.put("plenitude", plenitude);
        attackDB.put("calm_mind", plenitude);

        attackDB.put("amnesie", amnesie);
        attackDB.put("amnesia", amnesie);

        attackDB.put("danse_draco", danseDraco);
        attackDB.put("dragon_dance", danseDraco);

        attackDB.put("mur_de_fer", murDeFer);
        attackDB.put("iron_defense", murDeFer);

        attackDB.put("turbo", turbo);

        // endregion

        return attackDB;
    }
}
