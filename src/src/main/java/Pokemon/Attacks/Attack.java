package Pokemon.Attacks;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.Move;
import Pokemon.PokemonEnum.Type;

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
