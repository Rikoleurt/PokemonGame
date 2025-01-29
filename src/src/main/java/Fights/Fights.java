package Fights;

import Person.NPC;
import Pokemon.Pokemon;

import java.util.Random;

public class Fights {

    public void faintedPokemon(Pokemon pokemon) {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(pokemon.getHP() < 0) {
            if (randomNumber < 50) {
                System.out.println(pokemon.getName() + " has fainted");
            } if ( 50 <= randomNumber && 99 > randomNumber) {
                System.out.println(pokemon.getName() + " is K.O");
            } else {
                System.out.println(pokemon.getName() + " is bad");
            }
        }
    }

    public void endFight(NPC person) {
        System.out.println(person.getName() + " has been defeated");
    }
}

// Pas la bonne façon de faire, contact entre PNJ et Player engendre un combat