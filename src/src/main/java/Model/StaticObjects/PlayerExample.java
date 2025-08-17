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

import static Model.StaticObjects.PokemonsExample.*;

public class PlayerExample {

    public static Model.Person.Player initiatePlayer() {
        Map<Item, Integer> items = new HashMap<>();
        items.put(new Heal(Category.HEALTH, "Potion", "Heals 20HP", 20), 1);
        Bag bag = new Bag(items);
        LinkedList<Pokemon> team = new LinkedList<>();

        Pokemon pikachu = initiatePikachu();
        Pokemon bulbizarre = initiateBulbizarre();
        Pokemon salameche = initiateSalameche();

        team.add(salameche);
        team.add(pikachu);
        team.add(bulbizarre);

        for(int i = 0; i < bulbizarreMoves.size(); i++) System.out.println(bulbizarre.getAttacks().get(i).getName());
        pikachu.setStatus(Status.KO);

        return new Model.Person.Player("Jason", bag, team);
    }
}

