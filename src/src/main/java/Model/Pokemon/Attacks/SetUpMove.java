package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SetUpMove extends Move {

    private final Map<String, Integer> stats;
    private final boolean isTargetSelf;

    public SetUpMove(String name, Map<String, Integer> stats, Type type, AttackMode Mode, int PP, int maxPP, boolean isTargetSelf) {
        super(name, type, Mode, PP, maxPP);
        this.stats = stats;
        this.isTargetSelf = isTargetSelf;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }
    public int getDeltaStage(String stat) {
        if (stats == null) return 0;
        return stats.getOrDefault(stat, 0);
    }
    public List<String> getAllStat(){
        if (stats == null) return new ArrayList<>();
        return new ArrayList<>(stats.keySet());
    }
    public boolean isTargetSelf() {
        return isTargetSelf;
    }
}
