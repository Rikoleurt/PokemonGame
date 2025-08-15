package Model.Pokemon;

import Model.Person.NPC;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Weather;
import Model.Person.Player;


import java.util.LinkedList;

public class Terrain {

    LinkedList<Pokemon> team;
    LinkedList<Pokemon> enemyTeam;

    Debris debris;
    Weather weather;

    private int nbSpikes = 0;
    private int nbPoisonSpikes = 0;
    private int nbStealthRocks = 0;

    public Terrain(LinkedList<Pokemon> team, LinkedList<Pokemon> enemyTeam, Debris debris, Weather weather) {
        this.team = team;
        this.enemyTeam = enemyTeam;
        this.debris = debris;
        this.weather = weather;
    }

    public Debris getDebris() {
        return debris;
    }

    public Weather getMeteo() {
        return weather;
    }

    public LinkedList<Pokemon> getTeam() {
        return team;
    }

    public LinkedList<Pokemon> getEnemyTeam() {
        return enemyTeam;
    }


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

    public void setWeather(Weather weather) {
        System.out.println("The weather is " + weather);
        this.weather = weather;
    }



    // debrisEffect is only called when the player or the npc changes its pokemon i.e changePokemon();
    public void debrisEffect(Player player, Terrain terrain) {
            updateDebris(player.getFrontPokemon(), terrain);
    }

    public void debrisEffect(NPC npc, Terrain terrain) {
        updateDebris(npc.getFrontPokemon(), terrain);
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
                    if (pokemon.weaknessesTable(pokemon).contains(Type.fighting) && pokemon.weaknessesTable(pokemon).contains(Type.ground)) {
                        pokemon.HP -= pokemon.maxHP / 32;
                    }
                    if (pokemon.weaknessesTable(pokemon).contains(Type.fighting) || pokemon.weaknessesTable(pokemon).contains(Type.ground)) {
                        pokemon.HP -= pokemon.maxHP / 16;
                    }
                    if (!pokemon.weaknessesTable(pokemon).contains(Type.fighting) || !pokemon.weaknessesTable(pokemon).contains(Type.ground)
                            || !pokemon.weaknessesTable(pokemon).contains(Type.fire) || !pokemon.weaknessesTable(pokemon).contains(Type.ice)
                            || !pokemon.weaknessesTable(pokemon).contains(Type.bug) || !pokemon.weaknessesTable(pokemon).contains(Type.flying)) {
                        pokemon.HP -= pokemon.maxHP / 8;
                    }
                    if (pokemon.weaknessesTable(pokemon).contains(Type.fire) || pokemon.weaknessesTable(pokemon).contains(Type.ice)
                            || pokemon.weaknessesTable(pokemon).contains(Type.bug) || pokemon.weaknessesTable(pokemon).contains(Type.flying)
                    ) {
                        pokemon.HP -= pokemon.maxHP / 4;

                    }
                    if (
                            (pokemon.weaknessesTable(pokemon).contains(Type.fire) && pokemon.weaknessesTable(pokemon).contains(Type.ice)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.fire) && pokemon.weaknessesTable(pokemon).contains(Type.bug)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.fire) && pokemon.weaknessesTable(pokemon).contains(Type.flying)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.ice) && pokemon.weaknessesTable(pokemon).contains(Type.bug)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.ice) && pokemon.weaknessesTable(pokemon).contains(Type.flying)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.flying) && pokemon.weaknessesTable(pokemon).contains(Type.ice)) ||
                                    (pokemon.weaknessesTable(pokemon).contains(Type.flying) && pokemon.weaknessesTable(pokemon).contains(Type.bug))
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
