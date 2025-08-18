package Model.StaticObjects;

import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Heal.Heal;
import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Model.StaticObjects.PokemonExample.*;

public class PlayerExample {

    public static Model.Person.Player initiatePlayer() {
        Map<Item, Integer> items = new HashMap<>();
        items.put(new Heal(Category.HEALTH, "Potion", "Heals 20HP", 20), 1);
        items.put(new Heal(Category.HEALTH, "Super Potion", "Heals 50HP", 50), 2);
        items.put(new Heal(Category.HEALTH, "Hyper Potion", "Heals 100HP", 100), 3);

        Bag bag = new Bag(items);
        LinkedList<Pokemon> team = new LinkedList<>();

        Pokemon pikachu = initiatePikachu();
        Pokemon bulbizarre = initiateBulbizarre();
        Pokemon salameche = initiateSalameche();
        Pokemon abo = initiateEkans();
        Pokemon papilusion = initiateButterfree();
        Pokemon roucool = initiatePidgey();

        team.add(salameche);
        team.add(pikachu);
        team.add(bulbizarre);
        team.add(roucool);
        team.add(papilusion);
        team.add(abo);

        return new Model.Person.Player("Jason", bag, team);
    }
}

