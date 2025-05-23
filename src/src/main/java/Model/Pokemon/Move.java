package Model.Pokemon;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.PokemonEnum.Type;

import java.util.Random;

import static java.lang.Math.*;

public class Move {
    String name;
    Type type;
    AttackMode Mode;
    int PP;
    int raiseLevel;

    public Move(String name, Type type, AttackMode Mode, int PP){
        this.name = name;
        this.type = type;
        this.Mode = Mode;
        this.PP = PP;
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

    protected boolean isStab(Pokemon pokemon) {
        return pokemon.getType().equals(pokemon.getAttack(this).getType());
    }

    double criticalProb(Pokemon pokemon) {
        return (float) round(((float) pokemon.getSpeed() / 2));
    }

    protected boolean isCritical(Pokemon pokemon) {
        Random random = new Random();
        double rand = random.nextDouble(256)/256;
        double criticalProb = criticalProb(pokemon)/256;
        return rand < criticalProb;
    }

    public double criticalDamage(Pokemon pokemon) {
        return (double) (2 * pokemon.getLevel() + 5) / (pokemon.getLevel() + 5);
    }
}
