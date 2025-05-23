package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;

public class UpgradeMove extends Move {

    String stat;
    int raiseLevel;

    public UpgradeMove(String name, String stat, int raiseLevel, Type type, AttackMode Mode, int PP) {
        super(name, type, Mode, PP);
        this.stat = stat;
        this.raiseLevel = raiseLevel;
    }

    public String getStat() {
        return stat;
    }

    public int getRaiseLevel() {
        return raiseLevel;
    }
}
