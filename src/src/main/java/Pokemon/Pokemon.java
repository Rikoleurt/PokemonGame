package Pokemon;

import Pokemon.PokemonEnum.Effect;
import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Pokemon {

    int HP;
    int maxHP;
    int hpIV;
    int hpEV;
    int baseHP;
    int speed;
    int speedIV;
    int speedEV;
    int baseSpeed;
    int atk;
    int atkIV;
    int atkEV;
    int baseAtk;
    int def;
    int defIV;
    int defEV;
    int baseDef;
    int atkSpe;
    int atkSpeIV;
    int atkSpeEV;
    int baseAtkSpe;
    int defSpe;
    int defSpeIV;
    int defSpeEV;
    int baseDefSpe;
    int level;
    int exp;
    String name;
    Type type;
    Attack attack;
    Nature nature;
    Effect effect;


    public Pokemon(int HP, int maxHP, int hpIV, int hpEV, int baseHP, int speed, int speedIV, int speedEV, int baseSpeed,
                   int atk, int atkIV, int atkEV, int baseAtk, int def, int defIV, int defEV, int baseDef, int atkSpe, int atkSpeIV,
                   int atkSpeEV, int baseAtkSpe, int defSpe, int defSpeIV, int defSpeEV, int baseDefSpe, int level, int exp,
                   String name, Type type, Nature nature, Attack attack, Effect effect){
        this.HP = HP;
        this.maxHP = maxHP;
        this.hpIV = hpIV;
        this.hpEV = hpEV;
        this.baseHP = baseHP;
        this.speed = speed;
        this.speedIV = speedIV;
        this.speedEV = speedEV;
        this.baseSpeed = baseSpeed;
        this.atk = atk;
        this.atkIV = atkIV;
        this.atkEV = atkEV;
        this.baseAtk = baseAtk;
        this.def = def;
        this.defIV = defIV;
        this.defEV = defEV;
        this.baseDef = baseDef;
        this.atkSpe = atkSpe;
        this.atkSpeIV = atkSpeIV;
        this.atkSpeEV = atkSpeEV;
        this.baseAtkSpe = baseAtkSpe;
        this.defSpe = defSpe;
        this.defSpeIV = defSpeIV;
        this.defSpeEV = defSpeEV;
        this.baseDefSpe = baseDefSpe;
        this.level = level;
        this.exp = exp;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.nature = nature;
        this.effect = effect;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHpIV() {
        return hpIV;
    }

    public int getHpEV() {
        return hpEV;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpeedIV() {
        return speedIV;
    }

    public int getSpeedEV() {
        return speedEV;
    }
    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getAtk() {
        return atk;
    }

    public int getAtkIV() {
        return atkIV;
    }

    public int getAtkEV() {
        return atkEV;
    }
    public int getBaseAtk() {
        return baseAtk;
    }

    public int getDef() {
        return def;
    }

    public int getDefIV() {
        return defIV;
    }

    public int getDefEV() {
        return defEV;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getDefSpe() {
        return defSpe;
    }

    public int getDefSpeIV() {
        return defSpeIV;
    }

    public int getDefSpeEV() {
        return defSpeEV;
    }
    public int getBaseDefSpe() {
        return baseDefSpe;
    }

    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }

    public Nature getNature() {
        return nature;
    }

    public Attack getAttack() {
        return attack;
    }

    public Effect getEffect() {
        return effect;
    }

    public static void useAttack(Pokemon pokemon, Attack attack){
        pokemon.HP -= (int) totalDamage(pokemon.getAttack(), pokemon);
        System.out.println(pokemon.getName() + " uses " + attack.getName());
        System.out.println("Pokemon HP : "  + pokemon.getHP());
    }

    public static double totalDamage(Attack attack, Pokemon pokemon) {
        float power = attack.getPower();
        if(attack.isStab(pokemon, attack)) {
            power *= 1.5f;
            System.out.println("stab true");
        } else {
            System.out.println("stab false");
        }
        return calculateEffectiveness(attack, pokemon, power);
    }

    private static double calculateEffectiveness(Attack attack, Pokemon pokemon, float power) {
        float effectivenessCoefficient;
        if (checkWeaknesses(pokemon).contains(attack.getType())) {
            effectivenessCoefficient = 2;
            System.out.println("The attack is super effective");
            return (((((pokemon.level * 0.4 + 2) * pokemon.getAtk() * power) / pokemon.getDef()) / 50) + 2) * effectivenessCoefficient ;
        }
        if (checkImmunities(pokemon).contains(attack.getType())) {
            System.out.println("This attack does not affect the pokemon");
            return 0;
        }
        if (checkResistances(pokemon).contains(attack.getType())) {
            effectivenessCoefficient = 0.5f;
            System.out.println("The attack is not very effective");
            return (((((pokemon.level * 0.4 + 2) * pokemon.getAtk() * power) / pokemon.getDef()) / 50) + 2) * effectivenessCoefficient;
        } else {
            return (((((pokemon.level * 0.4 + 2) * pokemon.getAtk() * power) / pokemon.getDef()) / 50) + 2);
        }
    }

    private int calculateIV (Pokemon pokemon, int stat) {
        int IV = 0;
        // IV = (stat/pokemon.getNatureValue() * 100/pokemon.getLevel() - pokemon.getEV()/4 - 2 * pokemon.getBaseStat()
        return IV;
    }


    public static List<Type> checkWeaknesses(Pokemon pokemon) {

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

    public static List<Type> checkResistances(Pokemon pokemon) {

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


    public static List<Type> checkImmunities(Pokemon pokemon) {

        List<Type> immunities = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                immunities.add(Type.ghost);
                break;
            case fire:
                break;
            case water:
                break;
            case grass:
                break;
            case electric:
                break;
            case ice:
                break;
            case fighting:
                break;
            case poison:
                break;
            case ground:
                immunities.add(Type.electric);
                break;
            case flying:
                immunities.add(Type.ground);
                break;
            case psychic:
                break;
            case bug:
                break;
            case rock:
                break;
            case ghost:
                immunities.add(Type.normal);
                immunities.add(Type.fighting);
                break;
            case dragon:
                break;
            case dark:
                immunities.add(Type.psychic);
                break;
            case steel:
                break;
            case fairy:
                immunities.add(Type.dragon);
                break;
            default:
                immunities = new ArrayList<>();
                break;
        }

        return immunities;
    }

}
