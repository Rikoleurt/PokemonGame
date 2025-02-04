package Pokemon.Attacks;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.Move;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;

public class StatusAttack extends Move {

    int precision;
    Status status;

    public StatusAttack(String name, int precision, Status status, Type type, AttackMode Mode, int PP) {
        super(name, type, Mode, PP);
        this.precision = precision;
        this.status = status;
    }

    public int getPrecision() {
        return precision;
    }

    public Status getStatus() {
        return status;
    }
}
