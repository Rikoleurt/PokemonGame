package Fights;

import Person.NPC;
import Person.Player;
import Pokemon.Pokemon;

import java.util.Random;

public class Fights {

    boolean playerTurn = false;
    boolean npcTurn = false;
    public void turn(Player player, NPC npc) {
        Pokemon playerP = player.getFrontPokemon();
        Pokemon npcP = npc.getFrontPokemon();
        if(playerP.getBaseSpeed() > npcP.getBaseSpeed()) {
            playerTurn = true;
        }
        if(npcP.getBaseSpeed() > playerP.getBaseSpeed()) {
            npcTurn = true;
        }
    }

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