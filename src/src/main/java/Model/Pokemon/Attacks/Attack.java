package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;

public class Attack extends Move {

    int power;
    int precision;

    public Attack(String name, int power, int precision, Type type, AttackMode Mode, int PP) {
        super(name, type, Mode, PP);
        this.power = power;
        this.precision = precision;
    }

    public int getPower() {
        return power;
    }

    public int getPrecision() {
        return precision;
    }
}
