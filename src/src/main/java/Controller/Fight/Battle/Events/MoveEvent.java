package Controller.Fight.Battle.Events;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;

public class MoveEvent extends BattleEvent {
    private Pokemon attacker, target;
    private Move move;
    private Terrain terrain;


    public MoveEvent(Pokemon attacker, Pokemon target, Move move, Terrain terrain) {
        this.attacker = attacker;
        this.target = target;
        this.move = move;
        this.terrain = terrain;
    }

    public Pokemon getAttacker() {
        return attacker;
    }

    public Pokemon getTarget() {
        return target;
    }
    public Move getMove() {
        return move;
    }

    @Override
    public void execute(Runnable onFinished) {
        // Execute what should be executed when a Pokémon attacks i.e. if the Pokémon uses an attack -> check status
        // -> if status apply status effect -> if not canceled attack -> update target HP's
        attacker.attack(target, move, terrain);
    }
}
