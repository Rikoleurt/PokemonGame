package Pokemon;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.TerrainEnum.Debris;

import java.util.Random;

import static java.lang.Math.*;

public class Attack {
    String name;
    int power;
    int precision;
    Type type;
    AttackMode Mode;
    Status status;
    int PP;
    Debris debris;
    String stat;
    int raiseLevel;


    public Attack(String name, int power, int precision, Type type, AttackMode Mode, int PP) {
        this.name = name;
        this.power = power;
        this.precision = precision;
        this.type = type;
        this.Mode = Mode;
        this.PP = PP;
    }

    public Attack(String name, Status status, int precision, Type type, int PP) {
        this.name = name;
        this.status = status;
        this.precision = precision;
        this.type = type;
        this.PP = PP;
    }
    public Attack(String name, int precision, Type type, int PP, Status status) {
        this.name = name;
        this.precision = precision;
        this.type = type;
        this.PP = PP;
        this.status = status;
    }

    public Attack(String name, Type type, int PP, Debris debris){
        this.name = name;
        this.type = type;
        this.PP = PP;
        this.debris = debris;
    }

    public Attack(String name, Type type, int PP, String stat, int raiseLevel){
        this.name = name;
        this.type = type;
        this.PP = PP;
        this.stat = stat;
        this.raiseLevel = raiseLevel;
    }

    public int getPower() {
        return power;
    }

    public Type getType() {
        return type;
    }

    public AttackMode getMode() {
        return Mode;
    }

    public String getName() {
        return name;
    }

    public int getPP() {
        return PP;
    }

    public Status getEffect() {
        return status;
    }

    public Debris getDebris() {
        return debris;
    }

    public String getStat() {
        return stat;
    }

    public int getRaiseLevel() {
        return raiseLevel;
    }

    boolean isStab(Pokemon pokemon) {
        return pokemon.getType().equals(pokemon.getAttack(this).getType());
    }

    double criticalProb(Pokemon pokemon) {
        return (float) round(((float) pokemon.getSpeed() / 2));
    }
    boolean isCritical(Pokemon pokemon) {
        Random random = new Random();
        double rand = random.nextDouble(256)/256;
        double criticalProb = criticalProb(pokemon)/256;
        return rand < criticalProb;
    }
    public double criticalDamage(Pokemon pokemon) {
        return (double) (2 * pokemon.getLevel() + 5) / (pokemon.getLevel() + 5);
    }
}
