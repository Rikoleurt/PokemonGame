package Pokemon;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.PokemonEnum.Effect;
import Pokemon.PokemonEnum.Type;

import static java.lang.Math.round;

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

    boolean isStab(Attack attack) {
        return this.getType().equals(attack.getType());
    }


    double criticalProb(Pokemon pokemon, Attack attack) {
        float t;
        t = (float) round(((float) pokemon.getSpeed() / 2) % 2);
        double criticalProb = t/256;

        return criticalProb;
    }
    boolean isCritical(Pokemon pokemon) {
        return false;
    }
}
