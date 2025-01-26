package Pokemon;

import Pokemon.PokemonEnum.Type;
import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;

public class Terrain {
    Pokemon[] pokemons;
    Debris debris;
    Meteo meteo;

    public Terrain(Pokemon[] pokemons, Debris debris, Meteo meteo) {
        this.pokemons = pokemons;
        this.debris = debris;
        this.meteo = meteo;
    }

    public Debris getDebris() {
        return debris;
    }

    public Meteo getMeteo() {
        return meteo;
    }

    public void setDebris(Debris debris) {
        this.debris = debris;
    }

    public void setMeteo(Meteo meteo) {
        this.meteo = meteo;
    }

    public void debrisEffect(Terrain terrain, Pokemon pokemon) {
        switch (terrain.getDebris()){
            case spikes:
            case stealthRock:
            case poisonSpikes:
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
