package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;

public class StatusAttack extends Move {

    double precision;
    Status status;

    public StatusAttack(String name, double precision, Status status, Type type, AttackMode Mode, int PP) {
        super(name, type, Mode, PP);
        this.precision = precision;
        this.status = status;
    }

    public double getPrecision() {
        return precision;
    }

    public Status getStatus() {
        return status;
    }

    public void setPrecision(double value) {
        precision = value;
    }
}
