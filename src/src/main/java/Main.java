import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;

public class Main {
    public static void main(String[] args) {
        Attack attack = new Attack("Charge", 40,Type.normal, AttackMode.physical, 40);
        Pokemon pokemon1 = new Pokemon(40,40,31,40,31,40,31,40,31,40,31,40,31,5,
                0, "Rattata", Type.normal, Nature.Adamant, attack, null);
        Pokemon pokemon2 = new Pokemon(40,40,31,40,31,40,31,40,31,40,31,40,31,5,
                0, "Rattata", Type.normal, Nature.Adamant, attack, null);
        pokemon1.useAttack(pokemon2, attack);
        System.out.println(pokemon2.getHP());
    }
}
