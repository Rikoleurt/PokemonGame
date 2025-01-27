package Pokemon;

import Pokemon.AttackEnum.AttackMode;
import Pokemon.PokemonEnum.Status;
import Pokemon.PokemonEnum.Nature;
import Pokemon.PokemonEnum.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
    ArrayList<Attack> attacks;
    Nature nature;
    Status status;
    String gender;

    Random random = new Random();
    int wakeUp = 0;
    int poisonCoefficient = 1;
    int healConfusion = 1;
    int healFear = 0;

    Nature[][] natures = {
            {Nature.Hardy},  {Nature.Lonely},  {Nature.Adamant}, {Nature.Naughty},  {Nature.Brave},
            {Nature.Bold},   {Nature.Docile},  {Nature.Impish},  {Nature.Lax},      {Nature.Relaxed},
            {Nature.Modest}, {Nature.Mild},    {Nature.Bashful}, {Nature.Quiet},    {Nature.Rash},
            {Nature.Calm},   {Nature.Gentle},  {Nature.Careful}, {Nature.Sassy},    {Nature.Quirky},
            {Nature.Timid},  {Nature.Hasty},   {Nature.Jolly},   {Nature.Naive},    {Nature.Serious}
    };

    private Pokemon(int HP, int maxHP, int hpIV, int hpEV, int baseHP, int speed, int speedIV, int speedEV, int baseSpeed,
                   int atk, int atkIV, int atkEV, int baseAtk, int def, int defIV, int defEV, int baseDef, int atkSpe, int atkSpeIV,
                   int atkSpeEV, int baseAtkSpe, int defSpe, int defSpeIV, int defSpeEV, int baseDefSpe, int level, int exp,
                   String name, Type type, Nature nature, ArrayList<Attack> attacks, Status status){
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
        this.attacks = attacks;
        this.nature = nature;
        this.status = status;
    }

    public Pokemon(int HP, int maxHP, int atk, int def, int atkSpe, int defSpe, int speed, int level, Type type, ArrayList<Attack> attacks, String name, Status status, String gender){
        this.HP = HP;
        this.maxHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.atkSpe = atkSpe;
        this.defSpe = defSpe;
        this.speed = speed;
        this.type = type;
        this.level = level;
        this.attacks = attacks;
        this.name = name;
        this.status = status;
        this.gender = gender;
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

    public int getAtkSpe(){
        return atkSpe;
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

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public Attack getAttack(Attack attack){
        return attacks.get(attacks.indexOf(attack));
    }

    public Status getEffect() {
        return status;
    }

    public void setEffect(Status status) {
        this.status = status;
    }

    public int getEV(int stat){
        return stat;
    }

    public int getIV(int stat){
        return stat;
    }
    public int getBaseStat(int stat){
        return stat;
    }

    public String getGender() {
        return gender;
    }

    public void setAttack(ArrayList<Attack> attacks, int position, Attack attack) {
        attacks.set(position, attack);
    }

    public void useAttack(Pokemon target, Attack attack){
        Random random = new Random();
        if(this.getAttack(attack).getMode() == AttackMode.physical && this.getEffect() == Status.burned){
             target.HP -= (int) totalDamage(this.getAttack(attack), this, target)/2;
             System.out.println(this.getName() + " uses " + attack.getName());
             System.out.println(target.getName() + " HP : " + target.HP + "/" + target.getMaxHP());
             return;
        }
        if(this.getEffect() == Status.paralyzed){
            int randInt = random.nextInt(0,4);
            if(randInt == 1){
                System.out.println(this.getName() + " is paralyzed! It can't move!");
                return;
            }
        }
        if(this.getEffect() == Status.freeze){
            int randInt = random.nextInt(0,4);
            System.out.println(randInt);
            if(randInt < 3){
                System.out.println(this.getName() + " is frozen solid!");
                return;
            } else {
                System.out.println(this.getName() + " is not frozen anymore!");
                this.setEffect(null);
            }
        }
        if(this.getEffect() == Status.asleep){
            int randInt = random.nextInt(0,3);
            if(randInt == 0){
                System.out.println(this.getName() + " woke up!");
                this.setEffect(Status.normal);
            }
            if(randInt > 0){
                System.out.println(this.getName() + " is asleep!");
                ++wakeUp;
                if(wakeUp == 4){
                    System.out.println(this.getName() + " woke up!");
                    this.status = Status.normal;
                }
                if(wakeUp != 4) {
                    return;
                }
            }
        }
        if(this.getEffect() == Status.attracted && !Objects.equals(this.getGender(), target.getGender())){
            int randInt = random.nextInt(0,2);
            if(randInt == 1){
                System.out.println(this.getName() + " is in love with " + target.getName() + "!");
                return;
            }
        }
        if(this.getEffect() == Status.confused){
            int randInt = random.nextInt(0,2);
            System.out.println(healConfusion);
            ++healConfusion;
            if(randInt == 0){
                if(healConfusion < 4) {
                    System.out.println(this.getName() + " is confused!");
                }
            }
            if(randInt == 1){
                System.out.println(this.getName() + " is confused!");
                System.out.println(this.getName() + " hurt itself in its confusion!");
                this.HP -= (int) (((((this.getLevel() * 0.4 + 2) * this.getAtk() * 40) / target.getDef()) / 50) + 2);
                return;
            }
            if(healConfusion > 4){
                System.out.println(this.getName() + " snapped out of confusion!");
                this.setEffect(Status.normal);
                healConfusion = 0;
            }
        }
        if(this.getEffect() == Status.fear){
            System.out.println(this.getName() + " is fear! It can't attack!");
        }
        if((this.getEffect() == Status.normal || this.getEffect() == Status.cursed || this.getEffect() == Status.burned
           || this.getEffect() == Status.paralyzed || this.getEffect() == Status.freeze || this.getEffect() == Status.attracted
           || this.getEffect() == Status.confused || this.getEffect() == Status.asleep)
        ){
            System.out.println(this.getName() + " uses " + attack.getName());
            target.HP -= (int) totalDamage(this.getAttack(attack), this, target);
            System.out.println(target.getName() + " HP : " + target.HP + "/" + target.getMaxHP());
        }
        updateStatusEffect();
    }


    public void updateStatusEffect(){
        switch(this.getEffect()){
            case normal, attracted, asleep:
                break;
            case burned:
                System.out.println(this.getName() + " suffers from burn!");
                this.HP = this.HP - (this.maxHP/16);
                break;
            case poisoned:
                System.out.println(this.getName() + " is poisoned!");
                this.HP = this.HP - (this.maxHP/8);
                break;
            case badlyPoisoned:
                System.out.println(this.getName() + " is badly poisoned!");
                this.HP = this.HP - (this.maxHP/16 * poisonCoefficient);
                ++poisonCoefficient;
                System.out.println(poisonCoefficient);
                break;
            case confused:
            case fear:
                healFear++;
                if(healFear == 1) {
                    this.setEffect(Status.normal);
                    healFear = 0;
                }
                break;
            case cursed:
                this.HP = this.HP - (this.maxHP/4);
        }
    }
    public void useStatusAttack(Pokemon target, Attack statusAttack){
        System.out.println(this.getName() + " uses " + statusAttack.getName());
        target.status = calculateEffect(target, statusAttack);
        System.out.println(target.getName() + " is " + target.getEffect() + "! It may be unable to move!");
    }

    public Status calculateEffect(Pokemon target, Attack statusAttack){
        if(target.getEffect() != null){
            System.out.println(target.getName() + " is already " + target.getEffect() + "! It won't have any effect.");
        }
        if(checkImmunities(target).contains(statusAttack.getType())){
            System.out.println("This attack does not affect the pokemon");
            return null;
        }
        if(target.getEffect() == null){
            return statusAttack.getEffect();
        }
        return target.getEffect();
    }

    public double totalDamage(Attack attack, Pokemon launcher, Pokemon target) {
        float power = attack.getPower();
        double augmentedDamage;
        if(attack.isStab(launcher)) {
            power *= 1.5f;
        }
        if(attack.isCritical(launcher)){
            augmentedDamage = launcher.getAttack(attack).criticalDamage(launcher);
            System.out.println("Critical hit !");
            return calculateEffectiveness(attack, launcher, target, power) * augmentedDamage;
        }
        return calculateEffectiveness(attack, launcher, target, power);
    }

    private double calculateEffectiveness(Attack attack, Pokemon launcher, Pokemon target, float power) {
        float effectivenessCoefficient;
        switch (launcher.getAttack(attack).getMode()) {
            case physical:
                if (checkWeaknesses(target).contains(attack.getType())) {
                    effectivenessCoefficient = 2;
                    System.out.println("The attack is super effective");
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtk() * power) / target.getDef()) / 50) + 2) * effectivenessCoefficient;
                }
                if (checkImmunities(target).contains(attack.getType())) {
                    System.out.println("This attack does not affect the pokemon");
                    return 0;
                }
                if (checkResistances(target).contains(attack.getType())) {
                    effectivenessCoefficient = 0.5f;
                    System.out.println("The attack is not very effective");
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtk() * power) / target.getDef()) / 50) + 2) * effectivenessCoefficient;
                } else {
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtk() * power) / target.getDef()) / 50) + 2);
                }
            case special:
                if (checkWeaknesses(target).contains(attack.getType())) {
                    effectivenessCoefficient = 2;
                    System.out.println("The attack is super effective");
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtkSpe() * power) / target.getDefSpe()) / 50) + 2) * effectivenessCoefficient;
                }
                if (checkImmunities(target).contains(attack.getType())) {
                    System.out.println("This attack does not affect the pokemon");
                    return 0;
                }
                if (checkResistances(target).contains(attack.getType())) {
                    effectivenessCoefficient = 0.5f;
                    System.out.println("The attack is not very effective");
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtkSpe() * power) / target.getDefSpe()) / 50) + 2) * effectivenessCoefficient;
                } else {
                    return (((((launcher.level * 0.4 + 2) * launcher.getAtkSpe() * power) / target.getDefSpe()) / 50) + 2);
                }
            }
            return 0;
        }

    private int calculateIV (Pokemon pokemon, int stat) {
        int IV = (stat * 100/pokemon.getLevel() - pokemon.getEV(stat)/4 - 2 * pokemon.getBaseStat(stat));
        return IV;
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
                resistances.add(Type.dragon);
                break;
            default:
                resistances = new ArrayList<>();
                break;
        }

        return resistances;
    }


    public  List<Type> checkImmunities(Pokemon pokemon) {

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
