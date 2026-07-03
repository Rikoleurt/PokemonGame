package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;

public class SetUpMove extends Move {

    private final String stat;
    private final int stageDelta;
    private final boolean isTargetSelf;

    public SetUpMove(String name, String stat, int stageDelta, Type type, AttackMode Mode, int PP, int maxPP, boolean isTargetSelf) {
        super(name, type, Mode, PP, maxPP);
        this.stat = stat;
        this.stageDelta = stageDelta;
        this.isTargetSelf = isTargetSelf;
    }

    public String getStat() {
        return stat;
    }
    public int getStageDelta() {
        return stageDelta;
    }
    public boolean isTargetSelf() {
        return isTargetSelf;
    }
}
