package Controller.Fight.Battle.MoveEvent;

import Controller.Fight.Battle.BattleEvent;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;

public class AttackEvent implements BattleEvent {
    private Pokemon attacker, target;
    private Move move;
    private Terrain terrain;

    public AttackEvent(Pokemon attacker, Pokemon target, Move move, Terrain terrain) {
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
    public void execute() {
        // Execute what should be executed when a Pokémon attacks i.e. if the Pokémon uses an attack -> check status
        // -> if status apply status effect -> if not canceled attack -> update target HP's
    }
}
