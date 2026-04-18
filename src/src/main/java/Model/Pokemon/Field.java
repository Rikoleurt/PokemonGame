package Model.Pokemon;

import Model.Person.Trainer;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Weather;

import java.util.LinkedList;

public class Field {

    LinkedList<Pokemon> team;
    LinkedList<Pokemon> enemyTeam;

    Debris debris;
    Weather weather;

    private int nbSpikes = 0;
    private int nbPoisonSpikes = 0;
    private int nbStealthRocks = 0;

    public Field(LinkedList<Pokemon> team, LinkedList<Pokemon> enemyTeam, Debris debris, Weather weather) {
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
        if (debris == Debris.spikes) {
            nbSpikes++;
            System.out.println(nbSpikes);
            if (nbSpikes > 3) {
                System.out.println("It won't have any effect");
                nbSpikes = 3;
            }
        }

        if (debris == Debris.stealthRock) {
            nbStealthRocks++;
            System.out.println(nbStealthRocks);
            if (nbStealthRocks > 1) {
                System.out.println("It won't have any effect");
                nbStealthRocks = 1;
            }
        }

        if (debris == Debris.poisonSpikes) {
            nbPoisonSpikes++;
            System.out.println(nbPoisonSpikes);
            if (nbPoisonSpikes > 2) {
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

    // debrisEffect is only called when the player or the npc changes its pokemon
    public void debrisEffect(Trainer trainer, Field field) {
        updateDebris(trainer.getFrontPokemon(), field);
    }

    public void updateDebris(Pokemon pokemon, Field field) {
        if (field.getDebris() == null || pokemon == null || pokemon.isKO()) return;

        switch (field.getDebris()) {
            case spikes:
                applySpikes(pokemon);
                break;

            case stealthRock:
                applyStealthRock(pokemon);
                break;

            case poisonSpikes:
                applyPoisonSpikes(pokemon);
                break;
        }

        if (pokemon.getHP() <= 0) {
            pokemon.setHP(0);
        }
    }

    private void applySpikes(Pokemon pokemon) {
        boolean isFlyingType =
                pokemon.getType() == Type.flying ||
                        (pokemon.hasTwoTypes() && pokemon.getType2() == Type.flying);

        if (isFlyingType) {
            System.out.println("Spikes do not affect " + pokemon.getName());
            return;
        }

        int damage = 0;

        if (nbSpikes == 1) {
            damage = pokemon.getMaxHP() / 8;
        } else if (nbSpikes == 2) {
            damage = (int) (pokemon.getMaxHP() / 6.0);
        } else if (nbSpikes == 3) {
            damage = pokemon.getMaxHP() / 4;
        }

        if (damage > 0) {
            System.out.println("Spikes affect " + pokemon.getName());
            pokemon.setHP(Math.max(0, pokemon.getHP() - damage));
        }
    }

    private void applyStealthRock(Pokemon pokemon) {
        if (nbStealthRocks < 1) return;

        float coef = pokemon.getOverallTypeCoefficient(Type.rock);
        int damage = 0;

        if (coef == 0.25f) {
            damage = pokemon.getMaxHP() / 32;
        } else if (coef == 0.5f) {
            damage = pokemon.getMaxHP() / 16;
        } else if (coef == 1.0f) {
            damage = pokemon.getMaxHP() / 8;
        } else if (coef == 2.0f) {
            damage = pokemon.getMaxHP() / 4;
        } else if (coef == 4.0f) {
            damage = pokemon.getMaxHP() / 2;
        }

        if (damage > 0) {
            System.out.println("Stealth Rock affects " + pokemon.getName() + " | coef = " + coef);
            pokemon.setHP(Math.max(0, pokemon.getHP() - damage));
        } else {
            System.out.println("Stealth Rock does not affect " + pokemon.getName() + " | coef = " + coef);
        }
    }

    private void applyPoisonSpikes(Pokemon pokemon) {
        boolean isFlyingType =
                pokemon.getType() == Type.flying ||
                        (pokemon.hasTwoTypes() && pokemon.getType2() == Type.flying);

        if (isFlyingType) {
            System.out.println("Poison Spikes do not affect " + pokemon.getName());
            return;
        }

        boolean isPoisonType =
                pokemon.getType() == Type.poison ||
                        (pokemon.hasTwoTypes() && pokemon.getType2() == Type.poison);

        if (isPoisonType) {
            System.out.println(pokemon.getName() + " absorbed the Poison Spikes!");
            nbPoisonSpikes = 0;
            if (debris == Debris.poisonSpikes) {
                debris = null;
            }
            return;
        }

        if (pokemon.getStatus() != Status.normal) return;

        if (nbPoisonSpikes == 1) {
            pokemon.setStatus(Status.poisoned);
        } else if (nbPoisonSpikes >= 2) {
            pokemon.setStatus(Status.badlyPoisoned);
        }
    }
}