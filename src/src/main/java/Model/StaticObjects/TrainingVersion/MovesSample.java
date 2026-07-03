package Model.StaticObjects.TrainingVersion;

import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.AttackEnum.AttackMode;

import java.util.HashMap;

public class MovesSample {

    public static HashMap<String, Move> initiateAttacks() {
        HashMap<String, Move> attackDB = new HashMap<>();

        Attack tackle       = new Attack("Tackle", 40, 100, Type.normal, AttackMode.physical, 40, 40);
        Attack thunderBolt  = new Attack("Thunder Bolt", 90, 100, Type.electric, AttackMode.special, 20, 20);
        Attack thunderPunch = new Attack("Thunder Punch", 75, 100, Type.electric, AttackMode.physical, 15, 15);
        Attack ember        = new Attack("Ember", 40, 100, Type.fire, AttackMode.special, 40, 40);
        Attack vineWhip     = new Attack("Vine Whip", 40, 100, Type.grass, AttackMode.special, 25, 25);
        Attack waterGun     = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 25, 25);
        Attack gust         = new Attack("Gust", 40, 100, Type.flying, AttackMode.special, 35, 35);
        Attack confusion    = new Attack("Confusion", 50, 100, Type.psychic, AttackMode.special, 25, 25);
        Attack bugBite      = new Attack("Bug Bite", 60, 100, Type.bug, AttackMode.physical, 20, 20);
        Attack bite         = new Attack("Bite", 60, 100, Type.dark, AttackMode.physical, 25, 25);
        Attack poisonSting  = new Attack("Poison Sting", 15, 100, Type.poison, AttackMode.physical, 35, 35);
        Attack thunderShock = new Attack("Thunder Shock", 40, 100, Type.electric, AttackMode.special, 30, 30);
        Attack leafBlade    = new Attack("Leaf Blade", 90, 100, Type.grass, AttackMode.physical, 10, 10);
        Attack energyBall   = new Attack("Energy Ball", 90, 100, Type.grass, AttackMode.special, 10, 10);
        Attack ironHead     = new Attack("Iron Head", 80, 100, Type.steel, AttackMode.physical, 10, 10);
        Attack surf         = new Attack("Surf", 90, 100, Type.water, AttackMode.special, 15, 15);
        Attack rockTomb     = new Attack("Rock Tomb", 50, 95, Type.rock, AttackMode.physical, 15, 15);
        Attack brickBreak   = new Attack("Brick Break", 75, 100, Type.fighting, AttackMode.physical, 15, 15);
        Attack auraSphere   = new Attack("Aura Sphere", 80, 100, Type.fighting, AttackMode.special, 20, 20);

        StatusAttack thunderWave = new StatusAttack("Thunder Wave", 100, Status.paralyzed, Type.electric, AttackMode.status,20, 20);
        StatusAttack poisonGas   = new StatusAttack("Poison Gas", 100, Status.poisoned, Type.poison, AttackMode.status,40, 40);
        StatusAttack toxic       = new StatusAttack("Toxic", 100, Status.badlyPoisoned, Type.poison, AttackMode.status,10, 10);
        StatusAttack willOWisp   = new StatusAttack("Will-O-Wisp", 100, Status.burned, Type.fire, AttackMode.status,15, 15);
        StatusAttack confuseRay  = new StatusAttack("Confuse Ray", 100, Status.confused, Type.ghost, AttackMode.status, 10, 10);
        StatusAttack sleepPowder = new StatusAttack("Sleep Powder", 75, Status.asleep, Type.grass, AttackMode.status, 15, 15);

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
        attackDB.put("thunder_wave", thunderWave);
        attackDB.put("poison_gas", poisonGas);
        attackDB.put("toxic", toxic);
        attackDB.put("will_o_wisp", willOWisp);
        attackDB.put("confuse_ray", confuseRay);
        attackDB.put("sleep_powder", sleepPowder);

        return attackDB;
    }
}