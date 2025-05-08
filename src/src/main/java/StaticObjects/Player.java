package StaticObjects;

import Inventory.Bag;
import Inventory.Items.Heal.Heal;
import Inventory.Items.Items;
import Pokemon.AttackEnum.AttackMode;
import Pokemon.Attacks.Attack;
import Pokemon.Attacks.DebrisAttack;
import Pokemon.Attacks.StatusAttack;
import Pokemon.Attacks.UpgradeMove;
import Pokemon.Move;
import Pokemon.Pokemon;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.TerrainEnum.Debris;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static StaticObjects.Pokemons.*;

public class Player {

    public static Person.Player initiatePlayer() {


        Map<Items, Integer> inventory = new HashMap<>();

        inventory.put(new Heal("Potion", "Heals 20HP", 20), 3);

        Bag bag = new Bag(inventory, 100);

        LinkedList<Pokemon> team = new LinkedList<>();

        Pokemon Pikachu = initiatePikachu();
        Pokemon Bulbizarre = initiateBulbizarre();
        Pokemon Salameche = initiateSalameche();

        team.add(Salameche);
        team.add(Pikachu);
        team.add(Bulbizarre);

        return new Person.Player("Jason", bag, team);
    }

}
