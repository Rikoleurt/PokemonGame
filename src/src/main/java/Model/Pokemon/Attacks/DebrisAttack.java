package Model.Pokemon.Attacks;

import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.TerrainEnum.Debris;

public class DebrisAttack extends Move {
    Debris debris;

    public DebrisAttack(String name, Type type, AttackMode Mode, int PP, Debris debris) {
        super(name, type, Mode, PP);
        this.debris = debris;
    }

    public Debris getDebris() {
        return debris;
    }

}
