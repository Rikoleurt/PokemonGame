package Model.StaticObjects;

import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Heal.Heal;
import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Model.StaticObjects.Pokemons.*;

public class Player {

    public static Model.Person.Player initiatePlayer() {
        Map<Item, Integer> items = new HashMap<>();
        items.put(new Heal(Category.HEALTH, "Potion", "Heals 20HP", 20), 3);
        Bag bag = new Bag(items);
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

