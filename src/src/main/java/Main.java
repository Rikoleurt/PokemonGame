import Inventory.Inventory;
import Inventory.Items.Items;
import Person.NPC;
import Person.Player;
import Pokemon.AttackEnum.AttackMode;
import Pokemon.Pokemon;

import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.Attack;
import Pokemon.Terrain;
import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Attack charge = new Attack("Charge", 40, 100, Type.normal, AttackMode.physical, 40);
        Attack waterGun = new Attack("Water Gun", 40, 100, Type.water, AttackMode.special, 40);
        Attack thunder = new Attack("Thunder", 80, 100, Type.electric, AttackMode.special, 20);
        Attack thunderWave = new Attack("ThunderWave", Status.paralyzed, 100, Type.electric, 40);
        Attack electricPunch = new Attack("Electric punch", 80,100, Type.electric, AttackMode.physical, 40);
        Attack ember = new Attack("Ember", 40,100,Type.fire,AttackMode.special, 40);
        Attack vineWhip = new Attack("Ember", 40,100,Type.grass, AttackMode.physical, 40);

        ArrayList<Attack> pikachuAtk = new ArrayList<>();
        pikachuAtk.add(charge);
        pikachuAtk.add(thunder);
        pikachuAtk.add(thunderWave);
        pikachuAtk.add(electricPunch);

        ArrayList<Attack> carapuceAtk = new ArrayList<>();
        carapuceAtk.add(charge);
        carapuceAtk.add(waterGun);

        ArrayList<Attack> salamecheAtk = new ArrayList<>();
        salamecheAtk.add(ember);
        ArrayList<Attack> bulbizarreAtk = new ArrayList<>();
        bulbizarreAtk.add(vineWhip);

        Pokemon pikachu = new Pokemon(
                35, 35,
                55, 40,
                51, 50,
                90,
                9,
                Type.electric, pikachuAtk, "Pikachu", Status.attracted,
                "male"
        );

        Pokemon carapuce = new Pokemon(
                44, 44, // HP et maxHP
                48, 65, // Attack et Defense
                50, 64, // Special Attack et Special Defense
                43,
                10,// Speed
                Type.water, carapuceAtk, "Carapuce", Status.normal,
                "female"
        );

        Pokemon salameche = new Pokemon(
                39, 39, // HP et maxHP
                52, 43, // Attack et Defense
                60, 50, // Special Attack et Special Defense
                65,     // Speed
                11,     // Niveau (exemple)
                Type.fire, salamecheAtk, "Salamèche", Status.normal,
                "male"
        );

        Pokemon bulbizarre = new Pokemon(
                45, 45, // HP et maxHP
                49, 49, // Attack et Defense
                65, 65, // Special Attack et Special Defense
                45,     // Speed
                10,     // Niveau (exemple)
                Type.grass, bulbizarreAtk, "Bulbizarre", Status.normal,
                "female"
        );


        LinkedList<Pokemon> pokemons = new LinkedList<>();
        LinkedList<Pokemon> pokemons2 = new LinkedList<>();
        LinkedList<Pokemon> onField = new LinkedList<>();
        pokemons.add(pikachu);
        pokemons2.add(carapuce);
        NPC npc = new NPC("NPC", pokemons2);
        Map<Items, Integer> bag = Map.of();
        Inventory inventory = new Inventory(bag, 100);
        Terrain terrain = new Terrain(onField, Debris.normal, Meteo.normal);
        Player player = new Player("Jason", inventory, pokemons);
        terrain.addPokemon(player, npc);
        terrain.setMeteo(Meteo.sunny);
        terrain.setDebris(Debris.spikes);

    }
}


