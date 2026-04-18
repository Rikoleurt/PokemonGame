package Model.StaticObjects.TrainingVersion;

import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.AttackEnum.AttackMode;

import java.util.HashMap;

public class MovesSample {

    public static HashMap<Integer, Move> initiateAttacks() {
        HashMap<Integer, Move> attackDB = new HashMap<>();

        Attack tackle       = new Attack("Tackle", 40, Type.normal,   AttackMode.physical, 40, 40);
        Attack thunderBolt  = new Attack("Thunder Bolt", 90, Type.electric, AttackMode.special, 20, 20);
        Attack thunderPunch = new Attack("Thunder Punch", 75, Type.electric, AttackMode.physical, 15, 15);
        Attack ember        = new Attack("Ember", 40, Type.fire,     AttackMode.special, 40, 40);
        Attack vineWhip     = new Attack("Vine Whip", 40, Type.grass, AttackMode.special, 25, 25);
        Attack waterGun     = new Attack("Water Gun", 40, Type.water, AttackMode.special, 25, 25);
        Attack gust         = new Attack("Gust", 40, Type.flying,    AttackMode.special, 35, 35);
        Attack confusion    = new Attack("Confusion", 50, Type.psychic, AttackMode.special, 25, 25);
        Attack bugBite      = new Attack("Bug Bite", 60, Type.bug,   AttackMode.physical, 20, 20);
        Attack bite         = new Attack("Bite", 60, Type.dark,      AttackMode.physical, 25, 25);
        Attack poisonSting  = new Attack("Poison Sting", 15, Type.poison, AttackMode.physical, 35, 35);
        Attack thunderShock = new Attack("Thunder Shock", 40, Type.electric, AttackMode.special, 30, 30);

        Attack leafBlade    = new Attack("Leaf Blade",90, Type.grass, AttackMode.physical, 10, 10);
        Attack energyBall   = new Attack("Energy Ball", 90, Type.grass, AttackMode.special, 10, 10);

        Attack ironHead     = new Attack("Iron Head", 80, Type.steel, AttackMode.physical, 10, 10);

        Attack surf         = new Attack("Surf", 90, Type.water, AttackMode.special, 15, 15);

        attackDB.put(0, tackle);
        attackDB.put(1, thunderBolt);
        attackDB.put(2, thunderPunch);
        attackDB.put(3, ember);
        attackDB.put(4, vineWhip);
        attackDB.put(5, waterGun);
        attackDB.put(6, gust);
        attackDB.put(7, confusion);
        attackDB.put(8, bugBite);
        attackDB.put(9, bite);
        attackDB.put(10, poisonSting);
        attackDB.put(11, thunderShock);

        attackDB.put(12, leafBlade);
        attackDB.put(13, energyBall);

        attackDB.put(14, ironHead);

        attackDB.put(15, surf);

        return attackDB;
    }
}
