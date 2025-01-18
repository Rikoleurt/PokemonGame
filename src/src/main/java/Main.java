import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;

public class Main {
    public static void main(String[] args) {
        Attack attack = new Attack("Charge", 40,Type.normal, AttackMode.physical, 40);
    }
}
