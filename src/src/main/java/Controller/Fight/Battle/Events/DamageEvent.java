package Controller.Fight.Battle.Events;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;

public class DamageEvent extends BattleEvent {
    private Pokemon launcher;
    private Pokemon target;
    private Move move;
    private Terrain terrain;

    public DamageEvent(Pokemon launcher, Pokemon target, Move move, Terrain terrain) {
        this.launcher = launcher;
        this.target = target;
        this.move = move;
        this.terrain = terrain;
    }

    @Override
    public void execute() {
        launcher.attack(target, move, terrain);
    }
}
