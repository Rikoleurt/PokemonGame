package Model.StaticObjects;

import Model.Inventory.Bag;
import Model.Inventory.Items.Heal.Heal;
import Model.Inventory.Items.Items;
import Model.Pokemon.Pokemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Model.StaticObjects.Pokemons.*;

public class Player {

    public static Model.Person.Player initiatePlayer() {


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

        return new Model.Person.Player("Jason", bag, team);
    }

}
