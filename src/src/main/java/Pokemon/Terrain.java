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

    int nbSpikes;
    int nbPoisonSpikes;

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

    public void setDebris(Debris debris) {
        this.debris = debris;
    }

    public void setMeteo(Meteo meteo) {
        System.out.println("The meteo is " + meteo);
        this.meteo = meteo;
    }

    public void debrisEffect(Player player, Pokemon otherPokemon, Terrain terrain) {
        System.out.println(player.hasChanged(player.getFrontPokemon(), player.getPokemon(otherPokemon)));
        if(player.hasChanged(player.getFrontPokemon(), player.getPokemon(otherPokemon))){
            System.out.println(player.getFrontPokemon().getName());
            updateDebris(player.getFrontPokemon(), terrain);
        }
    }


    public void updateDebris(Pokemon pokemon, Terrain terrain) {
        System.out.println(terrain.getDebris());
        switch (terrain.getDebris()){
            case spikes:
                if(nbSpikes == 1) {
                    System.out.println("Spikes affects " + pokemon.getName());
                    pokemon.HP -= pokemon.maxHP / 8;
                    return;
                }
                if(nbSpikes == 2) {
                    pokemon.HP -= (int) (pokemon.maxHP / 5.33);
                    return;
                }
                if(nbSpikes == 3) {
                    pokemon.HP -= pokemon.maxHP / 4;
                    return;
                }
                if(nbSpikes > 3) {
                    System.out.println("It won't have any effect");
                    return;
                }
            case stealthRock:
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fighting) && pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                    pokemon.HP -= pokemon.maxHP / 32;
                    return;
                }
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                    pokemon.HP -= pokemon.maxHP / 16;
                    return;
                }
                if(!pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || !pokemon.checkWeaknesses(pokemon).contains(Type.ground)
                    || !pokemon.checkWeaknesses(pokemon).contains(Type.fire) || !pokemon.checkWeaknesses(pokemon).contains(Type.ice)
                    || !pokemon.checkWeaknesses(pokemon).contains(Type.bug)  || !pokemon.checkWeaknesses(pokemon).contains(Type.flying)) {
                    pokemon.HP -= pokemon.maxHP / 8;
                    return;
                }
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fire) || pokemon.checkWeaknesses(pokemon).contains(Type.ice)
                   || pokemon.checkWeaknesses(pokemon).contains(Type.bug) || pokemon.checkWeaknesses(pokemon).contains(Type.flying)
                ) {
                    pokemon.HP -= pokemon.maxHP / 4;
                    return;

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
                    return;
                }
                if(terrain.getDebris() != Debris.normal){
                    System.out.println("It won't have any effect");
                    return;
                }
            case poisonSpikes:
                System.out.println("nbPoisonSpikes is " + nbPoisonSpikes);
                if(nbPoisonSpikes == 1) {
                    pokemon.status = Status.poisoned;
                    return;
                }
                if(nbPoisonSpikes == 2) {
                    pokemon.status = Status.badlyPoisoned;
                    return;
                }
                if(nbPoisonSpikes > 2) {
                    System.out.println("It won't have any effect");
                }
            }
        }

    public void meteorEffect(Terrain terrain, Pokemon pokemon, Attack attack) {
        switch (terrain.getMeteo()){
            case rainy:
                if(pokemon.getAttack(attack).getType().equals(Type.water)){
                    attack.power *= 1.5;
                }
                if(pokemon.getAttack(attack).getType().equals(Type.fire)){
                    attack.power *= 0.5;
                }
                if(pokemon.getAttack(attack).getName().equals("Solar Beam")){
                    attack.power = 60;
                }
                if(pokemon.getAttack(attack).getName().equals("Thunder")){
                    attack.precision = 100;
                }
                if(pokemon.getAttack(attack).getName().equals("Hurricane")){
                    attack.precision = 100;
                }
                // More to add here
            case sunny:
                if(pokemon.getAttack(attack).getType().equals(Type.fire)){
                    attack.power *= 1.5;
                }
                if(pokemon.getAttack(attack).getType().equals(Type.water)){
                    attack.power *= 0.5;
                }
                if(pokemon.getAttack(attack).getName().equals("Thunder")){
                    attack.precision = 50;
                }
                if(pokemon.getAttack(attack).getName().equals("Hurricane")){
                    attack.precision = 50;
                }
            case hailstorm:
                if(!pokemon.getType().equals(Type.ice)){
                    pokemon.HP -= pokemon.maxHP/16;
                }
                if(pokemon.getAttack(attack).getName().equals("Solar Beam")){
                    attack.power = 60;
                }
                if(pokemon.getAttack(attack).getName().equals("Weather Ball")){
                    attack.type = Type.ice;
                }
            case sandstorm:
                if(!pokemon.getType().equals(Type.rock) || !pokemon.getType().equals(Type.ground) || !pokemon.getType().equals(Type.steel)){
                    pokemon.HP -= pokemon.maxHP/16;
                }
                if(pokemon.getAttack(attack).getName().equals("Solar Beam")){
                    attack.power = 60;
                }
                if(pokemon.getAttack(attack).getName().equals("Weather Ball")){
                    attack.power = 100;
                    attack.type = Type.rock;
                }
            default:
                // More to add here
        }
    }
}
