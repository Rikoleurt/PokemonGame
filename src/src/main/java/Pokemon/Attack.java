package Pokemon;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.PokemonEnum.Type;

import static java.lang.Math.round;

public class Attack {
    String name;
    int power;
    Type type;
    AttackMode Mode;
    int PP;


    public Attack(String name, int power, Type type, AttackMode Mode, int PP) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.Mode = Mode;
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

    boolean isStab(Pokemon pokemon, Attack attack) {
        return pokemon.getType().equals(attack.getType());
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
