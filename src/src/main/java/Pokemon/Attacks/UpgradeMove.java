package Pokemon.Attacks;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.Move;
import Pokemon.PokemonEnum.Type;

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
