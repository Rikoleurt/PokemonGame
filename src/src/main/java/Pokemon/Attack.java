package Pokemon;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.PokemonEnum.Effect;
import Pokemon.PokemonEnum.Type;

import java.sql.SQLOutput;
import java.util.Random;

import static java.lang.Math.*;

public class Attack {
    String name;
    int power;
    int precision;
    Type type;
    AttackMode Mode;
    Effect effect;
    int PP;


    public Attack(String name, int power, int precision, Type type, AttackMode Mode, int PP) {
        this.name = name;
        this.power = power;
        this.precision = precision;
        this.type = type;
        this.Mode = Mode;
        this.PP = PP;
    }

    public Attack(String name, Effect effect, int precision, Type type, int PP) {
        this.name = name;
        this.effect = effect;
        this.precision = precision;
        this.type = type;
        this.PP = PP;
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

    public Effect getEffect() {
        return effect;
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
    public float criticalDamage(Pokemon pokemon) {
        return (float) (2 * pokemon.getLevel() + 5) / (pokemon.getLevel() + 5);
    }
}
