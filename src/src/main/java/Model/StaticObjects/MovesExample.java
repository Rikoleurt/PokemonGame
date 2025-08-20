package Model.StaticObjects;

import Model.Pokemon.Attacks.*;

import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.TerrainEnum.Debris;

import java.util.HashMap;

public class MovesExample {

    public static HashMap<Integer, Move> initiateAttacks()
    {
        HashMap<Integer, Move> attackDB = new HashMap<>();

        Attack tackle = new Attack("Tackle",40,100, Type.normal, AttackMode.physical,40);
        Attack thunderBolt = new Attack("Thunder Bolt", 90, 100, Type.electric, AttackMode.special, 20);
        Attack thunderPunch = new Attack("Thunder punch", 75, 100, Type.electric, AttackMode.physical, 15);
        Attack ember = new Attack("Ember", 40,100, Type.fire, AttackMode.special,40);
        Attack vineWhip = new Attack("Vine Whip", 45, 100, Type.grass, AttackMode.physical, 25);
        Attack waterGun = new Attack("Water gun", 40, 100, Type.water, AttackMode.special, 25);
        Attack gust = new Attack("Gust", 40, 100, Type.flying, AttackMode.special, 35);
        Attack confusion = new Attack("Confusion", 50, 100, Type.psychic, AttackMode.special, 25);
        Attack bugBite = new Attack("Bug Bite", 60, 100, Type.bug, AttackMode.physical, 20);
        Attack bite = new Attack("Bite", 60, 100, Type.dark, AttackMode.physical, 25);
        Attack poisonSting = new Attack("Poison Sting", 15, 100, Type.poison, AttackMode.physical, 35);

        // UpgradeMove sandAttack = new UpgradeMove("Sand Attack", "precision", -1, Type.ground, AttackMode.status, 15);
        SetUpMove leer = new SetUpMove("Leer", "def", -1, Type.normal, AttackMode.status, 30);
        SetUpMove tailWhip = new SetUpMove("Tail Whip", "def", -1, Type.normal, AttackMode.status, 30);
        SetUpMove swordDance = new SetUpMove("Sword Dance", "atk", -2, Type.normal, AttackMode.status, 20);

        StatusAttack stunSpore = new StatusAttack("Stun Spore", 100, Status.paralyzed, Type.electric, AttackMode.status, 30);

        DebrisAttack toxicSpikes = new DebrisAttack("Toxic Spikes", Type.poison, AttackMode.status, 20, Debris.poisonSpikes);
        DebrisAttack stealthRock = new DebrisAttack("Stealth Rock", Type.rock, AttackMode.status, 40, Debris.stealthRock);
        DebrisAttack spikes = new DebrisAttack("Spikes", Type.normal, AttackMode.status, 40, Debris.spikes);

        attackDB.put(0, tackle);
        attackDB.put(1, thunderBolt);
        attackDB.put(2, thunderPunch);
        attackDB.put(3, ember);
        attackDB.put(4, vineWhip);
        attackDB.put(5, waterGun);
        attackDB.put(6, leer);
        attackDB.put(7, tailWhip);
        attackDB.put(8, swordDance);
        attackDB.put(9, toxicSpikes);
        attackDB.put(10, stealthRock);
        attackDB.put(11, spikes);
        attackDB.put(12, gust);
        attackDB.put(13, confusion);
        attackDB.put(14, bugBite);
        attackDB.put(15, bite);
        attackDB.put(16, poisonSting);
        attackDB.put(17, stunSpore);

        return attackDB;
    }
}
