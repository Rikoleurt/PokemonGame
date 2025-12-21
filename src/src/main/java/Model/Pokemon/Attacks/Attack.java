package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;

public class Attack extends Move {

    int power;
    double precision;

    public Attack(String name, int power, double precision, Type type, AttackMode Mode, int PP, int maxPP) {
        super(name, type, Mode, PP, maxPP);
        this.power = power;
        this.precision = precision;
    }

    public Attack(String name, int power, Type type, AttackMode mode, int PP, int maxPP) {
        super(name, type, mode, PP, maxPP);
        this.power = power;
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
}
