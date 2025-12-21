package Model.StaticObjects;

import Model.Pokemon.Attacks.*;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.TerrainEnum.Debris;

import java.util.HashMap;

public class MovesSample {

    public static HashMap<Integer, Move> initiateAttacks() {
        HashMap<Integer, Move> attackDB = new HashMap<>();

        Attack tackle       = new Attack("Tackle", 40, Type.normal,   AttackMode.physical, 40, 40);
        Attack thunderBolt  = new Attack("Thunder Bolt", 90, Type.electric, AttackMode.special, 20, 20);
        Attack thunderPunch = new Attack("Thunder Punch", 75, Type.electric, AttackMode.physical, 15, 15);
        Attack ember        = new Attack("Ember", 40, Type.fire,     AttackMode.special, 40, 40);
        Attack vineWhip     = new Attack("Vine Whip", 45, Type.grass, AttackMode.physical, 25, 25);
        Attack waterGun     = new Attack("Water Gun", 40, Type.water, AttackMode.special, 25, 25);
        Attack gust         = new Attack("Gust", 40, Type.flying,    AttackMode.special, 35, 35);
        Attack confusion    = new Attack("Confusion", 50, Type.psychic, AttackMode.special, 25, 25);
        Attack bugBite      = new Attack("Bug Bite", 60, Type.bug,   AttackMode.physical, 20, 20);
        Attack bite         = new Attack("Bite", 60, Type.dark,      AttackMode.physical, 25, 25);
        Attack poisonSting  = new Attack("Poison Sting", 15, Type.poison, AttackMode.physical, 35, 35);

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

        return attackDB;
    }
}
