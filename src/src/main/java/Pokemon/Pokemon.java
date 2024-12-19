package Pokemon;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Pokemon {

    int HP;
    int level;
    String name;
    Type type;
    String nature;
    String evolution;
    Attack attack;

    public Pokemon(int HP, int level, String name, Type type, String nature, Attack attack){
        this.HP = HP;
        this.level = level;
        this.name = name;
        this.type = type;
        this.nature = nature;
        this.attack = attack;
    }

    public Type getType() {
        return type;
    }

    public String getName(){
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHP() {
        return HP;
    }

    public String getNature() {
        return nature;
    }

    public String getEvolution() {
        return evolution;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setDamage(Pokemon pokemon, Attack attack){
        attack = new Attack(this.name, (int) round(calculateDamage(attack, pokemon)), type, "Special");
        pokemon.HP -= attack.getDamage();
        System.out.println(pokemon.getName() + "uses " + attack.getName() );
        System.out.println("Number of damage: " + calculateDamage(attack, pokemon));
    }

    public double calculateDamage(Attack attack, Pokemon pokemon) {

        attack = pokemon.getAttack();

        double AttackDamage = attack.getDamage();

        if(checkWeaknesses(pokemon).contains(attack.getType())){
            AttackDamage *= 2;
            System.out.println("The attack is super effective");
        }
        if(checkImmunities(pokemon).contains(attack.getType())){
            AttackDamage *= 0;
            System.out.println("No effect");
        }
        if(checkResistances(pokemon).contains(attack.getType())){
            AttackDamage *= 0.5;
            System.out.println("The attack is not very effective");
        }

        return AttackDamage;
    }


    public List<Type> checkWeaknesses(Pokemon pokemon) {

        List<Type> weaknesses = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                weaknesses.add(Type.fighting);
                break;
            case fire:
                weaknesses.add(Type.water);
                weaknesses.add(Type.ground);
                weaknesses.add(Type.rock);
                break;
            case water:
                weaknesses.add(Type.grass);
                weaknesses.add(Type.electric);
                break;
            case grass:
                weaknesses.add(Type.fire);
                weaknesses.add(Type.ice);
                weaknesses.add(Type.poison);
                weaknesses.add(Type.flying);
                weaknesses.add(Type.bug);
                break;
            case electric:
                weaknesses.add(Type.ground);
                break;
            case ice:
                weaknesses.add(Type.fire);
                weaknesses.add(Type.fighting);
                weaknesses.add(Type.rock);
                weaknesses.add(Type.steel);
                break;
            case fighting:
                weaknesses.add(Type.flying);
                weaknesses.add(Type.psychic);
                weaknesses.add(Type.fairy);
                break;
            case poison:
                weaknesses.add(Type.ground);
                weaknesses.add(Type.psychic);
                break;
            case ground:
                weaknesses.add(Type.water);
                weaknesses.add(Type.grass);
                weaknesses.add(Type.ice);
                break;
            case flying:
                weaknesses.add(Type.electric);
                weaknesses.add(Type.ice);
                weaknesses.add(Type.rock);
                break;
            case psychic:
                weaknesses.add(Type.bug);
                weaknesses.add(Type.ghost);
                weaknesses.add(Type.dark);
                break;
            case bug:
                weaknesses.add(Type.fire);
                weaknesses.add(Type.flying);
                weaknesses.add(Type.rock);
                break;
            case rock:
                weaknesses.add(Type.water);
                weaknesses.add(Type.grass);
                weaknesses.add(Type.fighting);
                weaknesses.add(Type.ground);
                weaknesses.add(Type.steel);
                break;
            case ghost:
                weaknesses.add(Type.ghost);
                weaknesses.add(Type.dark);
                break;
            case dragon:
                weaknesses.add(Type.ice);
                weaknesses.add(Type.dragon);
                weaknesses.add(Type.fairy);
                break;
            case dark:
                weaknesses.add(Type.fighting);
                weaknesses.add(Type.bug);
                weaknesses.add(Type.fairy);
                break;
            case steel:
                weaknesses.add(Type.fire);
                weaknesses.add(Type.fighting);
                weaknesses.add(Type.ground);
                break;
            case fairy:
                weaknesses.add(Type.poison);
                weaknesses.add(Type.steel);
                break;
            default:
                weaknesses = new ArrayList<>();
                break;
        }
        return weaknesses;
    }

    public List<Type> checkResistances(Pokemon pokemon) {

        List<Type> resistances = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                // Pas de résistances spécifiques
                break;
            case fire:
                resistances.add(Type.fire);
                resistances.add(Type.grass);
                resistances.add(Type.ice);
                resistances.add(Type.bug);
                resistances.add(Type.steel);
                resistances.add(Type.fairy);
                break;
            case water:
                resistances.add(Type.fire);
                resistances.add(Type.water);
                resistances.add(Type.ice);
                resistances.add(Type.steel);
                break;
            case grass:
                resistances.add(Type.water);
                resistances.add(Type.electric);
                resistances.add(Type.grass);
                resistances.add(Type.ground);
                break;
            case electric:
                resistances.add(Type.electric);
                resistances.add(Type.flying);
                resistances.add(Type.steel);
                break;
            case ice:
                resistances.add(Type.ice);
                break;
            case fighting:
                resistances.add(Type.bug);
                resistances.add(Type.rock);
                resistances.add(Type.dark);
                break;
            case poison:
                resistances.add(Type.grass);
                resistances.add(Type.fighting);
                resistances.add(Type.poison);
                resistances.add(Type.bug);
                resistances.add(Type.fairy);
                break;
            case ground:
                resistances.add(Type.poison);
                resistances.add(Type.rock);
                break;
            case flying:
                resistances.add(Type.grass);
                resistances.add(Type.fighting);
                resistances.add(Type.bug);
                resistances.add(Type.ground); // Immunité au sol
                break;
            case psychic:
                resistances.add(Type.fighting);
                resistances.add(Type.psychic);
                break;
            case bug:
                resistances.add(Type.grass);
                resistances.add(Type.fighting);
                resistances.add(Type.ground);
                break;
            case rock:
                resistances.add(Type.normal);
                resistances.add(Type.fire);
                resistances.add(Type.poison);
                resistances.add(Type.flying);
                break;
            case ghost:
                resistances.add(Type.poison);
                resistances.add(Type.bug);
                resistances.add(Type.normal); // Immunité
                resistances.add(Type.fighting); // Immunité
                break;
            case dragon:
                resistances.add(Type.fire);
                resistances.add(Type.water);
                resistances.add(Type.electric);
                resistances.add(Type.grass);
                break;
            case dark:
                resistances.add(Type.ghost);
                resistances.add(Type.dark);
                resistances.add(Type.psychic); // Immunité
                break;
            case steel:
                resistances.add(Type.normal);
                resistances.add(Type.grass);
                resistances.add(Type.ice);
                resistances.add(Type.flying);
                resistances.add(Type.psychic);
                resistances.add(Type.bug);
                resistances.add(Type.rock);
                resistances.add(Type.dragon);
                resistances.add(Type.steel);
                resistances.add(Type.fairy);
                break;
            case fairy:
                resistances.add(Type.fighting);
                resistances.add(Type.bug);
                resistances.add(Type.dark);
                resistances.add(Type.dragon); // Immunité
                break;
            default:
                resistances = new ArrayList<>();
                break;
        }

        return resistances;
    }


    public List<Type> checkImmunities(Pokemon pokemon) {

        List<Type> immunities = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                immunities.add(Type.ghost); // Immunité aux attaques de type Ghost
                break;
            case fire:
                // Pas d'immunité spécifique
                break;
            case water:
                // Pas d'immunité spécifique
                break;
            case grass:
                // Pas d'immunité spécifique
                break;
            case electric:
                // Pas d'immunité spécifique
                break;
            case ice:
                // Pas d'immunité spécifique
                break;
            case fighting:
                // Pas d'immunité spécifique
                break;
            case poison:
                // Pas d'immunité spécifique
                break;
            case ground:
                immunities.add(Type.electric); // Immunité aux attaques de type Electric
                break;
            case flying:
                immunities.add(Type.ground); // Immunité aux attaques de type Ground
                break;
            case psychic:
                // Pas d'immunité spécifique
                break;
            case bug:
                // Pas d'immunité spécifique
                break;
            case rock:
                // Pas d'immunité spécifique
                break;
            case ghost:
                immunities.add(Type.normal); // Immunité aux attaques de type Normal
                immunities.add(Type.fighting); // Immunité aux attaques de type Fighting
                break;
            case dragon:
                // Pas d'immunité spécifique
                break;
            case dark:
                immunities.add(Type.psychic); // Immunité aux attaques de type Psychic
                break;
            case steel:
                // Pas d'immunité spécifique
                break;
            case fairy:
                immunities.add(Type.dragon); // Immunité aux attaques de type Dragon
                break;
            default:
                immunities = new ArrayList<>();
                break;
        }

        return immunities;
    }

}
