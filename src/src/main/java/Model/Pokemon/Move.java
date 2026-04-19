package Model.Pokemon;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.PokemonEnum.Type;
import Utils.SeedManager;

import java.util.Random;

import static java.lang.Math.*;

public class Move {
    String name;
    Type type;
    AttackMode mode;
    int PP;
    int maxPP;

    public Move(String name, Type type, AttackMode mode, int PP, int maxPP) {
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.PP = PP;
        this.maxPP = maxPP;
    }

    public Type getType() {
        return type;
    }

    public AttackMode getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public int getPP() {
        return PP;
    }

    public int getMaxPP() {
        return maxPP;
    }

    public void setPP(int PP) {
        this.PP = Math.max(0, Math.min(PP, maxPP));
    }

    public boolean hasPP() {
        return PP > 0;
    }

    public boolean consumePP() {
        if (PP <= 0) {
            return false;
        }
        PP--;
        return true;
    }

    public void restorePP() {
        PP = maxPP;
    }

    public boolean isStab(Pokemon pokemon) {
        if (pokemon == null || this.type == null) {
            return false;
        }

        return this.type == pokemon.getType()
                || (pokemon.getType2() != null && this.type == pokemon.getType2());
    }

    double criticalProb(Pokemon pokemon) {
        return (float) round(((float) pokemon.getSpeed() / 2));
    }

    protected boolean isCritical(Pokemon pokemon) {
        Random random = new Random(SeedManager.getSeed());
        double rand = random.nextDouble(256) / 256;
        double criticalProb = criticalProb(pokemon) / 256;
        return rand < criticalProb;
    }

    public double criticalDamage(Pokemon pokemon) {
        return (double) (2 * pokemon.getLevel() + 5) / (pokemon.getLevel() + 5);
    }
}