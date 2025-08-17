package Model.StaticObjects;

import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Heal.Heal;
import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Model.StaticObjects.PokemonsExample.initiateBulbizarre;
import static Model.StaticObjects.PokemonsExample.initiateCarapuce;

public class NPCExample {

    public static Model.Person.NPC initiateEnemy()
    {
        Map<Item, Integer> items = new HashMap<>();
        items.put(new Heal(Category.HEALTH, "Potion", "Heals 20HP", 20), 3);
        Bag bag = new Bag(items);

        LinkedList<Pokemon> team = new LinkedList<>();

        Pokemon bulbizarre = initiateBulbizarre();
        Pokemon carapuce = initiateCarapuce();

        team.add(bulbizarre);
        team.add(carapuce);

        return new Model.Person.NPC("npc", team, bag);
    }
}
