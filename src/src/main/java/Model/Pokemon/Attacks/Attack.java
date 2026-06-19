package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;
import Utils.SeedManager;

import java.util.Random;

public class Attack extends Move {

    int power;
    double precision;
    Status status;
    float statusChance;

    public Attack(String name, int power, double precision, Type type, AttackMode Mode, int PP, int maxPP) {
        super(name, type, Mode, PP, maxPP);
        this.power = power;
        this.precision = precision;
    }

    /**
     * Attacks with status effect
     * @param name String
     * @param power int
     * @param precision double
     * @param type Type
     * @param Mode AttackMode
     * @param PP int
     * @param maxPP int
     * @param status enum of the status
     * @param statusChance float value between 0 and 1
     */
    public Attack(String name, int power, double precision, Type type, AttackMode Mode, int PP, int maxPP, Status status, float statusChance) {
        super(name, type, Mode, PP, maxPP);
        this.power = power;
        this.precision = precision;
        this.status = status;
        this.statusChance = statusChance; // Value between 0 and 1
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public int getPower() {
        return power;
    }

    public double getPrecision() {
        return precision;
    }

    public boolean isStatusApplied(){
        if(status == null) return false;
        return SeedManager.getRng().nextFloat() < statusChance;
    }

    public Status getStatus(){
        return status;
    }
}
