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

    int nbSpikes = 0;
    int nbPoisonSpikes = 0;

    public Terrain(LinkedList<Pokemon> team, LinkedList<Pokemon> enemyTeam, Debris debris, Meteo meteo) {
        this.team = team;
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

    public void setDebris(Debris debris) {
        this.debris = debris;
    }

    public void setMeteo(Meteo meteo) {
        System.out.println("The meteo is " + meteo);
        this.meteo = meteo;
    }

    public void debrisEffect(Player player, Pokemon pokemon, Pokemon otherPokemon, Terrain terrain) {
        if(player.hasChanged(player.getFrontPokemon(pokemon), player.getPokemon(otherPokemon))){
            updateDebris(otherPokemon, terrain);
        }
    }


    public void updateDebris(Pokemon pokemon, Terrain terrain) {
        switch (terrain.getDebris()){
            case spikes:
                if(nbSpikes == 1) {
                    pokemon.HP -= pokemon.maxHP / 8;
                }
                if(nbSpikes == 2) {
                    pokemon.HP -= (int) (pokemon.maxHP / 5.33);
                }
                if(nbSpikes == 3) {
                    pokemon.HP -= pokemon.maxHP / 4;
                }
                if(nbSpikes < 3) {
                    System.out.println("It won't have any effect");
                    return;
                }
            case stealthRock:
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fighting) && pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                    pokemon.HP -= pokemon.maxHP / 32;
                }
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || pokemon.checkWeaknesses(pokemon).contains(Type.ground)) {
                    pokemon.HP -= pokemon.maxHP / 16;
                }
                if(!pokemon.checkWeaknesses(pokemon).contains(Type.fighting) || !pokemon.checkWeaknesses(pokemon).contains(Type.ground)
                    || !pokemon.checkWeaknesses(pokemon).contains(Type.fire) || !pokemon.checkWeaknesses(pokemon).contains(Type.ice)
                    || !pokemon.checkWeaknesses(pokemon).contains(Type.bug)  || !pokemon.checkWeaknesses(pokemon).contains(Type.flying)) {
                    pokemon.HP -= pokemon.maxHP / 8;
                }
                if(pokemon.checkWeaknesses(pokemon).contains(Type.fire) || pokemon.checkWeaknesses(pokemon).contains(Type.ice)
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
                if(terrain.getDebris() != Debris.normal){
                    System.out.println("It won't have any effect");
                    return;
                }
            case poisonSpikes:
                if(nbPoisonSpikes == 1) {
                    pokemon.status = Status.poisoned;
                }
                if(nbPoisonSpikes == 2) {
                    pokemon.status = Status.badlyPoisoned;
                }
                if(nbPoisonSpikes < 2) {
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
    public void addPokemon(Player player, NPC npc) {
        Terrain terrain = new Terrain(player.getTeam(), npc.getPokemons(), debris, meteo);

        terrain.getTeam().add(player.getTeam().get(0));
        System.out.println(player.getTeam().get(0).getName() + " Go!");

        terrain.getTeam().add(npc.getPokemons().get(0));
        System.out.println("The foe is sending " + npc.getPokemons().get(0).getName());
    }
}
