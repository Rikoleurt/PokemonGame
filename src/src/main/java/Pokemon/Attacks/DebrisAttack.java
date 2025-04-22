package Pokemon.Attacks;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.Move;
import Pokemon.PokemonEnum.Type;
import Pokemon.TerrainEnum.Debris;

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
