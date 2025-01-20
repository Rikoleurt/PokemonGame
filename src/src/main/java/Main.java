import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Effect;
import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        Attack thunderWave = new Attack("ThunderWave", Effect.paralyzed, 100, Type.electric, 40);
        ArrayList<Attack> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);
        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);
        Pokemon pikachu = new Pokemon(20,20,20,20,20,20, 20, Type.electric, pikachuAtk, "Pikachu" );
        Pokemon carapuce = new Pokemon(20,20,20,20,20,20, 20, Type.water, carapuceAtk, "Carapuce" );
        pikachu.useAttack(carapuce, thunder);
        System.out.println(carapuce.getHP());
    }
}

