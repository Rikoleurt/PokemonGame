package Pokemon;

import Person.NPC;
import Person.Player;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Type;
import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;


import java.util.LinkedList;

public class Terrain {

    LinkedList<Pokemon> team;
    LinkedList<Pokemon> enemyTeam;

    Debris debris;
    Meteo meteo;

    private int nbSpikes = 0;
    private int nbPoisonSpikes = 0;
    private int nbStealthRocks = 0;

    public Terrain(LinkedList<Pokemon> team, LinkedList<Pokemon> enemyTeam, Debris debris, Meteo meteo) {
        this.team = team;
        this.enemyTeam = enemyTeam;
        this.debris = debris;
        this.meteo = meteo;
    }

    public Debris getDebris() {
        return debris;
    }

    public Meteo getMeteo() {
        return meteo;
    }

    public LinkedList<Pokemon> getTeam() {
        return team;
    }

    public LinkedList<Pokemon> getEnemyTeam() {
        return enemyTeam;
    }


    // setDebris is called when the a pokemon launches a debris attakc
    public void setDebris(Debris debris) {
        if(debris == Debris.spikes){
            nbSpikes++;
            System.out.println(nbSpikes);
            if(nbSpikes > 3) {
                System.out.println("It won't have any effect");
                nbSpikes = 3;
            }
        }
        if(debris == Debris.stealthRock){
            nbStealthRocks++;
            System.out.println(nbStealthRocks);
            if(nbStealthRocks > 1){
                System.out.println("It won't have any effect");
                nbStealthRocks = 1;
            }
        }
        if(debris == Debris.poisonSpikes){
            nbPoisonSpikes++;
            System.out.println(nbPoisonSpikes);
            if(nbPoisonSpikes > 2) {
                System.out.println("It won't have any effect");
                nbPoisonSpikes = 2;
            }
        }
        this.debris = debris;
    }

    public void setDebris2(Debris debris) {
        this.debris = debris;
    }

    public void setMeteo(Meteo meteo) {
        System.out.println("The meteo is " + meteo);
        this.meteo = meteo;
    }



    // debrisEffect is only called when the player or the npc changes its pokemon i.e changePokemon();
    public void debrisEffect(Player player, Terrain terrain) {
            updateDebris(player.getFrontPokemon(), terrain);
    }

    public void updateDebris(Pokemon pokemon, Terrain terrain) {
        switch (terrain.getDebris()){
            case spikes:
                if(nbSpikes == 1) {
                    System.out.println("Spikes affects " + pokemon.getName());
                    pokemon.HP -= pokemon.maxHP / 8;
                }
                if(nbSpikes == 2) {
                    System.out.println("Spikes affects " + pokemon.getName());
                    pokemon.HP -= (int) (pokemon.maxHP / 5.33);
                }
                if(nbSpikes == 3) {
                    System.out.println("Spikes affects " + pokemon.getName());
                    pokemon.HP -= pokemon.maxHP / 4;
                }
            case stealthRock:
                if(nbStealthRocks == 1) {
                    if (pokemon.checkWeaknesses(pokemon).contains(Type.fighting) && pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                        pokemon.HP -= pokemon.maxHP / 32;
                    }
                    if (pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                        pokemon.HP -= pokemon.maxHP / 16;
                    }
                    if (!pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || !pokemon.checkWeaknesses(pokemon).contains(Type.ground)
                            || !pokemon.checkWeaknesses(pokemon).contains(Type.fire) || !pokemon.checkWeaknesses(pokemon).contains(Type.ice)
                            || !pokemon.checkWeaknesses(pokemon).contains(Type.bug) || !pokemon.checkWeaknesses(pokemon).contains(Type.flying)) {
                        pokemon.HP -= pokemon.maxHP / 8;
                    }
                    if (pokemon.checkWeaknesses(pokemon).contains(Type.fire) || pokemon.checkWeaknesses(pokemon).contains(Type.ice)
                            || pokemon.checkWeaknesses(pokemon).contains(Type.bug) || pokemon.checkWeaknesses(pokemon).contains(Type.flying)
                    ) {
                        pokemon.HP -= pokemon.maxHP / 4;

                    }
                    if (
                            (pokemon.checkWeaknesses(pokemon).contains(Type.fire) && pokemon.checkWeaknesses(pokemon).contains(Type.ice)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.fire) && pokemon.checkWeaknesses(pokemon).contains(Type.bug)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.fire) && pokemon.checkWeaknesses(pokemon).contains(Type.flying)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.ice) && pokemon.checkWeaknesses(pokemon).contains(Type.bug)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.ice) && pokemon.checkWeaknesses(pokemon).contains(Type.flying)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.flying) && pokemon.checkWeaknesses(pokemon).contains(Type.ice)) ||
                                    (pokemon.checkWeaknesses(pokemon).contains(Type.flying) && pokemon.checkWeaknesses(pokemon).contains(Type.bug))
                    ) {
                        pokemon.HP -= pokemon.maxHP / 2;
                    }
                }
            case poisonSpikes:
                if(nbPoisonSpikes == 1) {
                    pokemon.status = Status.poisoned;
                }
                if(nbPoisonSpikes == 2) {
                    pokemon.status = Status.badlyPoisoned;
                }
            }
        }
}
