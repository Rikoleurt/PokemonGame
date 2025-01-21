import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Effect;
import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;

import java.util.ArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Définition des attaques
        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        Attack thunderWave = new Attack("ThunderWave", Effect.paralyzed, 100, Type.electric, 40);

        // Liste des attaques pour Pikachu
        ArrayList<Attack> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);

        // Liste des attaques pour Carapuce
        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);

        // Création de Pikachu avec ses statistiques de base
        Pokemon pikachu = new Pokemon(
                35, 35,
                55, 40,
                50, 50,
                90,
                9,
                Type.electric, pikachuAtk, "Pikachu"
        );

        // Création de Carapuce avec ses statistiques de base
        Pokemon carapuce = new Pokemon(
                44, 44, // HP et maxHP
                48, 65, // Attack et Defense
                50, 64, // Special Attack et Special Defense
                43,
                10,// Speed
                Type.water, carapuceAtk, "Carapuce"
        );

        // Utilisation d'une attaque : Pikachu attaque Carapuce avec Thunder
        pikachu.useAttack(carapuce, thunder);

        // Affichage des PV restants de Carapuce
        System.out.println("PV restants de Carapuce : " + carapuce.getHP());
    }
}


