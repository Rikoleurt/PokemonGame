package Model.StaticObjects.TrainingVersion;

import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;
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

        return attackDB;
    }
}