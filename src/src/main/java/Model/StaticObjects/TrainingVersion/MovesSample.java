package Model.StaticObjects.TrainingVersion;

import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.SetUpMove;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.AttackEnum.AttackMode;

import java.util.HashMap;
import java.util.Map;

public class MovesSample {

    public static HashMap<String, Move> initiateAttacks() {
        HashMap<String, Move> attackDB = new HashMap<>();

        Attack tackle = new Attack("Tackle", 40, 100, Type.normal, AttackMode.physical, 40, 40);

        Attack thunderBolt = new Attack(
                "Thunder Bolt",
                90,
                100,
                Type.electric,
                AttackMode.special,
                20,
                20,
                Status.paralyzed,
                0.10f
        );

        Attack thunderPunch = new Attack(
                "Thunder Punch",
                75,
                100,
                Type.electric,
                AttackMode.physical,
                15,
                15,
                Status.paralyzed,
                0.10f
        );

        Attack ember = new Attack(
                "Ember",
                40,
                100,
                Type.fire,
                AttackMode.special,
                40,
                40,
                Status.burned,
                0.10f
        );

        Attack vineWhip = new Attack("Vine Whip", 40, 100, Type.grass, AttackMode.special, 25, 25);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 25, 25);
        Attack gust = new Attack("Gust", 40, 100, Type.flying, AttackMode.special, 35, 35);

        Attack confusion = new Attack(
                "Confusion",
                50,
                100,
                Type.psychic,
                AttackMode.special,
                25,
                25,
                Status.confused,
                0.10f
        );

        Attack bugBite = new Attack("Bug Bite", 60, 100, Type.bug, AttackMode.physical, 20, 20);
        Attack bite = new Attack("Bite", 60, 100, Type.dark, AttackMode.physical, 25, 25);

        Attack poisonSting = new Attack(
                "Poison Sting",
                15,
                100,
                Type.poison,
                AttackMode.physical,
                35,
                35,
                Status.poisoned,
                0.30f
        );

        Attack thunderShock = new Attack(
                "Thunder Shock",
                40,
                100,
                Type.electric,
                AttackMode.special,
                30,
                30,
                Status.paralyzed,
                0.10f
        );

        Attack leafBlade = new Attack("Leaf Blade", 90, 100, Type.grass, AttackMode.physical, 10, 10);
        Attack energyBall = new Attack("Energy Ball", 90, 100, Type.grass, AttackMode.special, 10, 10);
        Attack ironHead = new Attack("Iron Head", 80, 100, Type.steel, AttackMode.physical, 10, 10);
        Attack surf = new Attack("Surf", 90, 100, Type.water, AttackMode.special, 15, 15);

        Attack rockTomb = new Attack(
                "Rock Tomb",
                50,
                95,
                Type.rock,
                AttackMode.physical,
                15,
                15,
                null,
                0.0f,
                Map.of("speed", -1),
                false
        );

        Attack brickBreak = new Attack("Brick Break", 75, 100, Type.fighting, AttackMode.physical, 15, 15);
        Attack auraSphere = new Attack("Aura Sphere", 80, 100, Type.fighting, AttackMode.special, 20, 20);

        Attack closeCombat = new Attack(
                "Close Combat",
                120,
                100,
                Type.fighting,
                AttackMode.physical,
                5,
                5,
                null,
                0.0f,
                Map.of("def", -1, "defSpe", -1),
                true
        );

        Attack earthquake = new Attack("Earthquake", 100, 100, Type.ground, AttackMode.physical, 10, 10);
        Attack psychic = new Attack("Psychic", 90, 100, Type.psychic, AttackMode.special, 10, 10);
        Attack flamethrower = new Attack("Flamethrower", 90, 100, Type.fire, AttackMode.special, 15, 15);
        Attack aerialAce = new Attack("Aerial Ace", 60, 100, Type.flying, AttackMode.physical, 20, 20);
        Attack dragonClaw = new Attack("Dragon Claw", 80, 100, Type.dragon, AttackMode.special, 15, 15);

        StatusAttack thunderWave = new StatusAttack("Thunder Wave", 100, Status.paralyzed, Type.electric, AttackMode.special, 20, 20);
        StatusAttack poisonGas = new StatusAttack("Poison Gas", 100, Status.poisoned, Type.poison, AttackMode.special, 40, 40);
        StatusAttack toxic = new StatusAttack("Toxic", 100, Status.badlyPoisoned, Type.poison, AttackMode.special, 10, 10);
        StatusAttack willOWisp = new StatusAttack("Will-O-Wisp", 100, Status.burned, Type.fire, AttackMode.special, 15, 15);
        StatusAttack confuseRay = new StatusAttack("Confuse Ray", 100, Status.confused, Type.ghost, AttackMode.special, 10, 10);
        StatusAttack sleepPowder = new StatusAttack("Sleep Powder", 75, Status.asleep, Type.grass, AttackMode.special, 15, 15);

        SetUpMove swordsDance = new SetUpMove(
                "Swords Dance",
                Map.of("atk", 2),
                Type.normal,
                AttackMode.status,
                20,
                20,
                true
        );

        SetUpMove cottonGuard = new SetUpMove(
                "Cotton Guard",
                Map.of("def", 3),
                Type.grass,
                AttackMode.status,
                10,
                10,
                true
        );

        SetUpMove calmMind = new SetUpMove(
                "Calm Mind",
                Map.of("atkSpe", 1, "defSpe", 1),
                Type.psychic,
                AttackMode.status,
                20,
                20,
                true
        );

        SetUpMove amnesia = new SetUpMove(
                "Amnesia",
                Map.of("defSpe", 2),
                Type.psychic,
                AttackMode.status,
                20,
                20,
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

        Attack signalBeam = new Attack(
                "Signal Beam",
                60,
                100,
                Type.bug,
                AttackMode.special,
                20,
                20
        );

        Attack shadowBall = new Attack(
                "Shadow Ball",
                80,
                100,
                Type.ghost,
                AttackMode.special,
                15,
                15
        );

        SetUpMove dragonDance = new SetUpMove(
                "Dragon dance",
                Map.of("atk", 1, "speed", 1),
                Type.dragon,
                AttackMode.special,
                20,
                20,
                true
        );

        attackDB.put("tackle", tackle);
        attackDB.put("thunder_bolt", thunderBolt);
        attackDB.put("thunder_punch", thunderPunch);
        attackDB.put("ember", ember);
        attackDB.put("vine_whip", vineWhip);
        attackDB.put("water_gun", waterGun);
        attackDB.put("gust", gust);
        attackDB.put("confusion", confusion);
        attackDB.put("bug_bite", bugBite);
        attackDB.put("bite", bite);
        attackDB.put("poison_sting", poisonSting);
        attackDB.put("thunder_shock", thunderShock);
        attackDB.put("leaf_blade", leafBlade);
        attackDB.put("energy_ball", energyBall);
        attackDB.put("iron_head", ironHead);
        attackDB.put("surf", surf);
        attackDB.put("rock_tomb", rockTomb);
        attackDB.put("brick_break", brickBreak);
        attackDB.put("aura_sphere", auraSphere);

        attackDB.put("close_combat", closeCombat);
        attackDB.put("earthquake", earthquake);
        attackDB.put("psychic", psychic);
        attackDB.put("flamethrower", flamethrower);
        attackDB.put("aerial_ace", aerialAce);
        attackDB.put("dragon_claw", dragonClaw);

        attackDB.put("thunder_wave", thunderWave);
        attackDB.put("poison_gas", poisonGas);
        attackDB.put("toxic", toxic);
        attackDB.put("will_o_wisp", willOWisp);
        attackDB.put("confuse_ray", confuseRay);
        attackDB.put("sleep_powder", sleepPowder);

        attackDB.put("swords_dance", swordsDance);
        attackDB.put("cotton_guard", cottonGuard);
        attackDB.put("calm_mind", calmMind);
        attackDB.put("amnesia", amnesia);
        attackDB.put("turbo", turbo);

        attackDB.put("signal_beam", signalBeam);
        attackDB.put("shadow_ball", shadowBall);
        attackDB.put("dragon_dance", dragonDance);
        return attackDB;
    }
}