package Model.Pokemon;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.MessageEvent;
import Controller.Fight.Battle.Events.UpdateBarEvent;
import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Attacks.UpgradeMove;
import Model.Pokemon.PokemonEnum.Experience;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Nature;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.Attacks.DebrisAttack;

import java.io.*;
import java.util.*;

import View.FightView.InfoBars.Bar;
import View.FightView.Text.StatBubble;
import View.FightView.Text.TextBubble;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import javafx.scene.control.Label;


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
    int maxExp;
    String name;
    Type type;
    Type type1;
    Type type2;
    ArrayList<Move> moves;

    Nature nature;
    Status status;
    String gender;
    Experience expType;

    int wakeUp = 0;
    int poisonCoefficient = 1;
    int healConfusion = 1;
    int healFear = 0;

    int atkRaise;
    int defRaise;
    int speedRaise;
    int atkSpeRaise;
    int defSpeRaise;

    BattleExecutor executor = BattleExecutor.getInstance();

    public Pokemon(String name, int maxHP, int HP, int atk, int def, int atkSpe, int defSpe, int speed, int baseHP, int baseAtk, int baseDef, int baseAtkSpe, int baseDefSpe, int baseSpeed,
                   int hpIV, int atkIV, int defIV, int atkSpeIV, int defSpeIV, int speedIV, int hpEV, int atkEV, int defEV, int atkSpeEV, int defSpeEV, int speedEV, int atkRaise, int
                   defRaise, int atkSpeRaise, int defSpeRaise, int speedRaise, int level, Type type, ArrayList<Move> moves, String gender, int exp, int maxExp, Experience expType,
                   Status status){
        this.name = name;
        this.maxHP = maxHP;
        this.HP = HP;
        this.atk = atk;
        this.def = def;
        this.atkSpe = atkSpe;
        this.defSpe = defSpe;
        this.speed = speed;
        this.baseHP = baseHP;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseAtkSpe = baseAtkSpe;
        this.baseDefSpe = baseDefSpe;
        this.baseSpeed = baseSpeed;
        this.hpIV = hpIV;
        this.atkIV = atkIV;
        this.defIV = defIV;
        this.atkSpeIV = atkSpeIV;
        this.defSpeIV = defSpeIV;
        this.speedIV = speedIV;
        this.hpEV = hpEV;
        this.atkEV = atkEV;
        this.defEV = defEV;
        this.atkSpeEV = atkSpeEV;
        this.defSpeEV = defSpeEV;
        this.speedEV = speedEV;
        this.atkRaise = atkRaise;
        this.defRaise = defRaise;
        this.atkSpeRaise = atkSpeRaise;
        this.defSpeRaise = defSpeRaise;
        this.speedRaise = speedRaise;
        this.level = level;
        this.type = type;
        this.moves = moves;
        this.gender = gender;
        this.exp = exp;
        this.maxExp = maxExp;
        this.expType = expType;
        this.status = status;

    }


    // ------------------------------------------------------------------------------------------------------------------
    // Getter
    // ------------------------------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }
    public Type getType() {
        return type;
    }
    public int getHP() {
        return HP;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getAtk() {
        return atk;
    }
    public int getDef() {
        return def;
    }
    public int getAtkSpe(){
        return atkSpe;
    }
    public int getDefSpe() {
        return defSpe;
    }
    public int getSpeed() {
        return speed;
    }
    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }
    public int getMaxExp() {
        return maxExp;
    }
    public String getGender() {
        return gender;
    }
    public Status getStatus() {
        return status;
    }
    public Experience getExpType() {
        return expType;
    }
    public boolean isKO(){
        return this.getHP() <= 0;
    }
    public Nature getNature() {
        return nature;
    }
    public ArrayList<Move> getAttacks() {
        return moves;
    }
    public Move getAttack(Move move){
        return moves.get(moves.indexOf(move));
    }


    public void levelUp() {
        level++;

        int oldHP = HP;
//        int oldMaxHP = maxHP;
//        int oldAtk = atk;
//        int oldDef = def;
//        int oldAtkSpe = atkSpe;
//        int oldDefSpe = defSpe;
//        int oldSpeed = speed;

        double atkNature = getNatureMultiplier("atk");
        double defNature = getNatureMultiplier("def");
        double atkSpeNature = getNatureMultiplier("atkSpe");
        double defSpeNature = getNatureMultiplier("defSpe");
        double speedNature = getNatureMultiplier("speed");

        maxHP = (int) Math.floor(((2 * baseHP + hpIV + Math.floor(hpEV / 4.0)) * level) / 100) + level + 10;

        atk = (int) Math.floor((Math.floor(((2 * baseAtk + atkIV + Math.floor(atkEV / 4.0)) * level) / 100) + 5) * atkNature);
        def = (int) Math.floor((Math.floor(((2 * baseDef + defIV + Math.floor(defEV / 4.0)) * level) / 100) + 5) * defNature);
        atkSpe = (int) Math.floor((Math.floor(((2 * baseAtkSpe + atkSpeIV + Math.floor(atkSpeEV / 4.0)) * level) / 100) + 5) * atkSpeNature);
        defSpe = (int) Math.floor((Math.floor(((2 * baseDefSpe + defSpeIV + Math.floor(defSpeEV / 4.0)) * level) / 100) + 5) * defSpeNature);
        speed = (int) Math.floor((Math.floor(((2 * baseSpeed + speedIV + Math.floor(speedEV / 4.0)) * level) / 100) + 5) * speedNature);

        if(HP < maxHP) HP += maxHP-oldHP;
        if(HP > maxHP) HP = maxHP;

        System.out.println(name + " is now " + level + " !");

    }

    public void displayStatChanges(StatBubble bubble) {
        String s = this.getName() + " level up!" + this.getLevel() + "\n" +
                "HP      : " + this.getHP() + "/" + this.getMaxHP() + "\n" +
                "Atk     : " + this.getAtk() + "\n" +
                "Def     : " + this.getDef() + "\n" +
                "AtkSpe  : " + this.getAtkSpe() + "\n" +
                "DefSpe  : " + this.getDefSpe() + "\n" +
                "Speed   : " + this.getSpeed();

        bubble.showMessage(s);
    }


    private double getNatureMultiplier(String stat) {
        if (nature == null) return 1.0;

        switch (stat) {
            case "atk":
                if (nature == Nature.Adamant || nature == Nature.Brave || nature == Nature.Lonely || nature == Nature.Naughty)
                    return 1.1;
                if (nature == Nature.Bold || nature == Nature.Modest || nature == Nature.Timid || nature == Nature.Calm)
                    return 0.9;
                break;
            case "def":
                if (nature == Nature.Bold || nature == Nature.Relaxed || nature == Nature.Lax || nature == Nature.Impish)
                    return 1.1;
                if (nature == Nature.Lonely || nature == Nature.Mild || nature == Nature.Hasty || nature == Nature.Gentle)
                    return 0.9;
                break;
            case "atkSpe":
                if (nature == Nature.Modest || nature == Nature.Mild || nature == Nature.Rash || nature == Nature.Quiet)
                    return 1.1;
                if (nature == Nature.Adamant || nature == Nature.Careful || nature == Nature.Impish || nature == Nature.Jolly)
                    return 0.9;
                break;
            case "defSpe":
                if (nature == Nature.Calm || nature == Nature.Gentle || nature == Nature.Careful || nature == Nature.Sassy)
                    return 1.1;
                if (nature == Nature.Naughty || nature == Nature.Naive || nature == Nature.Lax || nature == Nature.Rash)
                    return 0.9;
                break;
            case "speed":
                if (nature == Nature.Timid || nature == Nature.Hasty || nature == Nature.Jolly || nature == Nature.Naive)
                    return 1.1;
                if (nature == Nature.Brave || nature == Nature.Quiet || nature == Nature.Relaxed || nature == Nature.Sassy)
                    return 0.9;
                break;
        }

        return 1.0;
    }

    // Getter for base stats
    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getBaseAtkSpe() {
        return baseAtkSpe;
    }

    public int getBaseDefSpe() {
        return baseDefSpe;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    //Getter for IV-EV
    public int getHpIV() {
        return hpIV;
    }

    public int getAtkIV() {
        return atkIV;
    }

    public int getDefIV() {
        return defIV;
    }

    public int getAtkSpeIV() {
        return atkSpeIV;
    }

    public int getDefSpeIV() {
        return defSpeIV;
    }

    public int getSpeedIV() {
        return speedIV;
    }

    public int getHpEV() {
        return hpEV;
    }

    public int getAtkEV() {
        return atkEV;
    }

    public int getDefEV() {
        return defEV;
    }

    public int getAtkSpeEV() {
        return atkSpeEV;
    }

    public int getDefSpeEV() {
        return defSpeEV;
    }

    public int getSpeedEV() {
        return speedEV;
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Setter
    // ------------------------------------------------------------------------------------------------------------------

    public void setStatus(Status status) {
        this.status = status;
    }
    public void setAttack(ArrayList<Move> moves, int position, Move move) {
        moves.set(position, move);
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setHP(int hp) {
        this.HP = hp;
        if (this.HP <= 0) {
            this.HP = 0;
            this.status = Status.KO;
        }
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Attack
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Verifies which instance is the player Pokémon's next move and applies the effect on the target according to the
     * move category (i.e. physical/special attack, a debris attack, a status attack or an upgrade move)
     * @param target The target of the player
     * @param move The move it uses
     * @param terrain The terrain the Pokémon are on
     */
    public void attack(Pokemon target, Move move, Terrain terrain, TextBubble bubble, Bar targetBar) {
        Move m = getAttack(move);
        if(m instanceof Attack attack){
            statusEffect(target, move, bubble);
            if((this.getStatus() == Status.normal || this.getStatus() == Status.cursed || this.getStatus() == Status.burned || this.getStatus() == Status.paralyzed || this.getStatus() == Status.freeze || this.getStatus() == Status.attracted || this.getStatus() == Status.confused || this.getStatus() == Status.asleep || this.getStatus() == Status.poisoned || this.getStatus() == Status.badlyPoisoned)){
                executor.addEvent(new MessageEvent(bubble,getName() + " uses " + move.getName()));
                executor.addEvent(new UpdateBarEvent(targetBar, targetBar.getHealth()));
                int damage = (int) totalDamage((Attack) getAttack(attack), this, target, bubble);
                target.setHP(Math.max(0, target.getHP() - damage));
            }
        }
        if(m instanceof DebrisAttack debrisAttack){
            statusEffect(target, move, bubble);
            terrain.setDebris(debrisAttack.getDebris());
        }
        if(m instanceof StatusAttack statusAttack){
            statusEffect(target, statusAttack, bubble);
            target.status = setStatus(target, statusAttack, bubble);
            System.out.println(target.getName() + " is " + target.getStatus() + "!");
        }
        if(m instanceof UpgradeMove upgradeMove){
            statusEffect(target, upgradeMove, bubble);
            switch (upgradeMove.getStat()) {
                case "atk"    -> atkRaise += upgradeMove.getRaiseLevel();
                case "def"    -> defRaise += upgradeMove.getRaiseLevel();
                case "speed"  -> speedRaise += upgradeMove.getRaiseLevel();
                case "atkSpe" -> atkSpeRaise += upgradeMove.getRaiseLevel();
                case "defSpe" -> defSpeRaise += upgradeMove.getRaiseLevel();
            }
            updateStat(bubble, target);
        }
        updateStatus();
        if (target.HP <= 0) {
            target.setStatus(Status.KO);
        }
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Everything that touches to stat changes in fights
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Update the stat modifier each turn
     */
    public void updateStat(TextBubble bubble, Pokemon target){
        this.atk = applyStatModifier(this.atk, atkRaise," attack", bubble, target);
        this.def = applyStatModifier(this.def, defRaise, " defense", bubble, target);
        this.speed = applyStatModifier(this.speed, speedRaise, " speed", bubble, target);
        this.atkSpe = applyStatModifier(this.atkSpe, atkSpeRaise," special attack", bubble, target);
        this.defSpe = applyStatModifier(this.defSpe, defSpeRaise," special defense", bubble, target);
    }

    private int applyStatModifier(int baseStat, int stage, String statName, TextBubble bubble, Pokemon target){
        int originalStage = stage;

        if (stage > 6) stage = 6;
        if (stage < -6) stage = -6;

        if (originalStage == 1) {
            executor.addEvent(new MessageEvent(bubble, target.getName() + " raises its " + statName));
        } else if (originalStage == 2) {
            executor.addEvent(new MessageEvent(bubble, target.getName() + " sharply raises its " + statName));
        } else if (originalStage == -1) {
            executor.addEvent(new MessageEvent(bubble, target.getName() + "'s" + statName + " decreased"));
        } else if (originalStage == -2) {
            executor.addEvent(new MessageEvent(bubble, target.getName() + "'s" + statName + " sharply decreased"));
        }

        int[] multipliersNumerator = {2, 2, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8};
        int[] multipliersDenominator = {8, 7, 6, 5, 4, 3, 2, 2, 2, 2, 2, 2, 2};

        return (baseStat * multipliersNumerator[stage + 6]) / multipliersDenominator[stage + 6];
    }


    // ------------------------------------------------------------------------------------------------------------------
    // Everything that touches to terrain, debris and climate
    // ------------------------------------------------------------------------------------------------------------------

    // In progress ...

    // ------------------------------------------------------------------------------------------------------------------
    // Everything that touches to status
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Sets the status according to the element table
     * @param target The target the player's Pokémon attacks
     * @param statusMove The status attack it uses
     * @return The status of the target
     */
    public Status setStatus(Pokemon target, StatusAttack statusMove, TextBubble bubble) {
        if(target.getStatus() != Status.normal){
            System.out.println(target.getName() + " is already " + target.getStatus() + "! It won't have any effect.");
            executor.addEvent(new MessageEvent(bubble,target.getName() + " is already " + target.getStatus() + "! It won't have any effect."));
        }
        if(immunitiesTable(target).contains(statusMove.getType())){
            System.out.println("This attack does not affect " + getName());
            executor.addEvent(new MessageEvent(bubble,"This attack does not affect " + getName()));
            return null;
        }
        if(target.getStatus() == Status.normal){
            return statusMove.getStatus();
        }
        return target.getStatus();
    }

    /**
     * Applies the effect on the Pokémon before it attacks
     * @param target The target the player's Pokémon attacks
     * @param move Move it uses
     */

    public void statusEffect(Pokemon target, Move move, TextBubble bubble){
        Random random = new Random();
        if(this.getAttack(move).getMode() == AttackMode.physical && this.getStatus() == Status.burned){
            target.HP -= (int) totalDamage((Attack) this.getAttack(move), this, target, bubble)/2;
            System.out.println(getName() + " uses " + move.getName());
            executor.addEvent(new MessageEvent(bubble,getName() + " uses " + move.getName()));
            System.out.println(target.getName() + " HP : " + target.HP + "/" + target.getMaxHP());
            return;
        }
        if(this.getStatus() == Status.paralyzed){
            int randInt = random.nextInt(0,4);
            if(randInt == 1){
                System.out.println(getName() + " is paralyzed! It can't move!");
                executor.addEvent(new MessageEvent(bubble,getName() + " is paralyzed! It can't move!"));
                return;
            }
        }
        if(this.getStatus() == Status.freeze){
            int randInt = random.nextInt(0,4);
            if(randInt < 3){
                System.out.println(getName() + " is frozen solid!");
                executor.addEvent(new MessageEvent(bubble,getName() + " is frozen solid!"));

                return;
            } else {
                System.out.println(getName() + " is not frozen anymore!");
                executor.addEvent(new MessageEvent(bubble,getName() + " is not frozen anymore!"));
                this.setStatus(null);
            }
        }
        if(this.getStatus() == Status.asleep){
            int randInt = random.nextInt(0,3);
            if(randInt == 0){
                System.out.println(getName() + " woke up!");
                executor.addEvent(new MessageEvent(bubble,getName() + " woke up!"));
                this.setStatus(Status.normal);
            }
            if(randInt > 0){
                System.out.println(getName() + " is asleep!");
                executor.addEvent(new MessageEvent(bubble,getName() + " is asleep!"));

                ++wakeUp;
                if(wakeUp == 4){
                    System.out.println(getName() + " woke up!");
                    executor.addEvent(new MessageEvent(bubble,getName() + " woke up!"));
                    this.status = Status.normal;
                }
                if(wakeUp != 4) {
                    return;
                }
            }
        }
        if(this.getStatus() == Status.attracted && !Objects.equals(this.getGender(), target.getGender())){
            int randInt = random.nextInt(0,2);
            if(randInt == 1){
                System.out.println(getName() + " is attracted to " + target.getName());
                executor.addEvent(new MessageEvent(bubble,getName() + " is attracted to " + target.getName()));
                return;
            }
        }
        if(this.getStatus() == Status.confused){
            int randInt = random.nextInt(0,2);
            ++healConfusion;
            if(randInt == 0){
                if(healConfusion < 4) {
                    executor.addEvent(new MessageEvent(bubble,getName() + " is confused!"));
                    System.out.println(getName() + " is confused!");
                }
            }
            if(randInt == 1){
                System.out.println(getName() + " is confused!");
                executor.addEvent(new MessageEvent(bubble,getName() + " is confused!"));
                executor.addEvent(new MessageEvent(bubble,getName() + " hurt itself in its confusion!"));
                System.out.println(getName() + " hurt itself in its confusion!");
                this.HP -= (int) (((((this.getLevel() * 0.4 + 2) * this.getAtk() * 40) / target.getDef()) / 50) + 2);
                return;
            }
            if(healConfusion > 4){
                System.out.println(getName() + " snapped out of confusion!");
                executor.addEvent(new MessageEvent(bubble,getName() + " snapped out of confusion!"));
                this.setStatus(Status.normal);
                healConfusion = 0;
            }
        }
        // To implement
        if(getStatus() == Status.fear){
            System.out.println(getName() + " is feared! It can't move!");
            executor.addEvent(new MessageEvent(bubble,getName() + " is feared! It can't move!"));
        }
    }

    /**
     * Updates the status of the Pokémon
     */
    public void updateStatus(){
        switch(this.getStatus()){
            case normal, attracted, asleep:
                break;
            case burned:
                System.out.println(getName() + " is burned!");
                this.HP = this.HP - (this.maxHP/16);
                break;
            case poisoned:
                System.out.println(getName() + " is poisoned!");
                this.HP = this.HP - (this.maxHP/8);
                break;
            case badlyPoisoned:
                System.out.println(getName() + " is badly poisoned!");
                this.HP = this.HP - (this.maxHP/16 * poisonCoefficient);
                ++poisonCoefficient;
                break;
            case confused:
            case fear:
                healFear++;
                if(healFear == 1) {
                    this.setStatus(Status.normal);
                    healFear = 0;
                }
                break;
            case cursed:
                this.HP = this.HP - (this.maxHP/4);
        }
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Total damages of special and physical attacks
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Calculates the total damage (includes critical hits and the stab)
     * @param attack
     * @param launcher
     * @param target
     * @return The total damage done to the target
     */
    public double totalDamage(Attack attack, Pokemon launcher, Pokemon target, TextBubble bubble) {
        float power = attack.getPower();
        double augmentedDamage;
        if(attack.isStab(launcher)) {
            power *= 1.5f;
        }
        if(attack.isCritical(launcher)){
            augmentedDamage = launcher.getAttack(attack).criticalDamage(launcher);
            executor.addEvent(new MessageEvent(bubble, "Critical hit !"));
            System.out.println("Critical hit !");
            return calculateEffectiveness(attack, launcher, target, power, bubble) * augmentedDamage;
        }
        return calculateEffectiveness(attack, launcher, target, power, bubble);
    }

    /**
     * Checks if the move is effective or not (only works for physical and special attakcs)
     * @param move The move that is used
     * @param launcher The Pokémon that launches the move
     * @param target The Pokémon that will get attacked
     * @param power The power of the move
     * @return The attack damage without taking into account critical/stab hits
     */
    private double calculateEffectiveness(Move move, Pokemon launcher, Pokemon target, float power, TextBubble bubble) {
        List<Type> targetWeaknesses =  weaknessesTable(target);
        List<Type> targetResistances = resistancesTable(target);
        List<Type> targetImmunities = immunitiesTable(target);

        int launcherLevel = this.getLevel();
        int launcherAtk = this.getAtk();
        int launcherAtkSpe = this.getAtkSpe();

        int targetDef = target.getDef();
        int targetDefSpe = target.getDefSpe();

        float effectivenessCoefficient;
        switch (launcher.getAttack(move).getMode()) {
            case physical:
                double physicalDamages = ((((launcherLevel * 0.4 + 2) * launcherAtk * power) / targetDef) / 50) + 2;
                if (targetWeaknesses.contains(move.getType())) {
                    effectivenessCoefficient = 2;
                    executor.addEvent(new MessageEvent(bubble, "The attack is super effective !"));
                    System.out.println("The attack is super effective !");
                    return physicalDamages * effectivenessCoefficient;
                }
                if (targetImmunities.contains(move.getType())) {
                    executor.addEvent(new MessageEvent(bubble, "The attack does not affect " + target.getName()));
                    System.out.println("This attack does not affect the pokemon");
                    return 0;
                }
                if (targetResistances.contains(move.getType())) {
                    effectivenessCoefficient = 0.5f;
                    executor.addEvent(new MessageEvent(bubble, "The attack is not very effective"));
                    System.out.println("The attack is not very effective");
                    return physicalDamages * effectivenessCoefficient;
                } else {
                    return physicalDamages;
                }
            case special:
                double specialDamages = ((((launcherLevel * 0.4 + 2) * launcherAtkSpe * power) / targetDefSpe) / 50) + 2;
                if (targetWeaknesses.contains(move.getType())) {
                    effectivenessCoefficient = 2;
                    executor.addEvent(new MessageEvent(bubble, "The attack is super effective !"));
                    System.out.println("The attack is super effective !");
                    return specialDamages * effectivenessCoefficient;
                }
                if (targetImmunities.contains(move.getType())) {
                    executor.addEvent(new MessageEvent(bubble, "The attack does not affect " + target.getName()));
                    System.out.println("This attack does not affect the pokemon");
                    return 0;
                }
                if (targetResistances.contains(move.getType())) {
                    effectivenessCoefficient = 0.5f;
                    executor.addEvent(new MessageEvent(bubble, "The attack is not very effective"));
                    System.out.println("The attack is not very effective !");
                    return specialDamages * effectivenessCoefficient;
                } else {
                    return specialDamages;
                }
            }
            return 0;
        }


    //------------------------------------------------------------------------------------------------------------------
    // Type table
    // ------------------------------------------------------------------------------------------------------------------

    public List<Type> weaknessesTable(Pokemon pokemon) {

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

    public List<Type> resistancesTable(Pokemon pokemon) {

        List<Type> resistances = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                // No resistances
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
                resistances.add(Type.ground);
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
                resistances.add(Type.normal);
                resistances.add(Type.fighting);
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
                resistances.add(Type.psychic);
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

    public  List<Type> immunitiesTable(Pokemon pokemon) {

        List<Type> immunities = new ArrayList<>();

        switch (pokemon.getType()) {
            case normal:
                immunities.add(Type.ghost);
                break;
            case fire, water, grass, electric, ice, fighting, poison, psychic, bug, rock, dragon:
                break;
            case ground:
                immunities.add(Type.electric);
                break;
            case flying:
                immunities.add(Type.ground);
                break;
            case ghost:
                immunities.add(Type.normal);
                immunities.add(Type.fighting);
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

    // ------------------------------------------------------------------------------------------------------------------
    // IV - EV
    // ------------------------------------------------------------------------------------------------------------------

    private int generateIV(){
        Random rand = new Random();
        return rand.nextInt(0,32);
    }


//    private int calculateIV (Model.Pokemon pokemon, int stat) {
//        return (stat * 100/pokemon.getLevel() - pokemon.getEV(stat)/4 - 2 * pokemon.getBaseStat(stat));
//    }
//
//    private int calculatePVIV (Model.Pokemon pokemon, int stat) {
//        return ((100 * pokemon.getBaseStat(stat) - pokemon.getLevel() - 10) / pokemon.getLevel()) - pokemon.getEV(stat) / 4 - 2 * pokemon.getBaseStat(stat);
//    }

    // ------------------------------------------------------------------------------------------------------------------
    // EXP
    // ------------------------------------------------------------------------------------------------------------------

    /**
     *
     * Calculates the experience gain based on a formula, the base experience of the Pokémon can be got in the csv file
     * @param defeatedPokemon The defeated Pokémon
     * @return ((baseExperience * defeatedPokemon.getLevel()) / 7)
     */
    public int calculateEXP(Pokemon defeatedPokemon) {
        String fileName = "data/pokemon.csv";
        System.out.println(this.getMaxExp());
        int a = 1; // if the Pokémon has been captured by a trainer, then a = 1.5;
        int e = 1; // if the Pokémon has a lucky egg, then e = 1.5;
        int t = 1; // if the Pokémon has been exchanged before, then t = 1.5 and 1.7 if it comes from another region from the player
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                System.out.println("File not found in resources: " + fileName);
                return 0;
            }

            int baseExperience = getBaseExperience(defeatedPokemon.getName(), inputStream);

            if (baseExperience == -1) {
                System.out.println("Can't find base exp for: " + defeatedPokemon.getName());
                return 0;
            }

            return ((a * e * t * baseExperience * defeatedPokemon.getLevel()) / 7);
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }



    /**
     * Get the base experience in the csv file
     * @param pokemonName name of the Pokémon we have to search for
     * @return The base experience of the Pokémon
     */

    public static int getBaseExperience(String pokemonName, InputStream inputStream) {
        try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(inputStream)))) {
            String[] nextLine;
            boolean isHeader = true;
            int expIndex = -1, nameIndex = -1;

            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    for (int i = 0; i < nextLine.length; i++) {
                        if (nextLine[i].equalsIgnoreCase("identifier")) {
                            nameIndex = i;
                        } else if (nextLine[i].equalsIgnoreCase("base_experience")) {
                            expIndex = i;
                        }
                    }
                    isHeader = false;

                    if (nameIndex == -1 || expIndex == -1) {
                        System.err.println("Error : column 'identifier' or 'base_experience' didn't find.");
                        return -1;
                    }
                    continue;
                }

                if (nextLine.length > nameIndex && nextLine[nameIndex].equalsIgnoreCase(pokemonName)) {
                    return Integer.parseInt(nextLine[expIndex]);
                }
            }
        } catch (IOException | NumberFormatException | CsvValidationException e) {
            e.printStackTrace();
        }

        System.err.println("Error : Pokémon '" + pokemonName + "' didn't find in the csv file");
        return -1;
    }

    /**
     * Calculates the maximum experience to reach for each level
     * @return The current maximum experience
     */
    public int calculateMaxExp() {
        int currentMaxExp = 0;
        int N = this.getLevel();
        double nCubed = Math.pow(N,3);

        switch (this.getExpType()) {
            case Fast -> currentMaxExp = (int) (0.8 * nCubed);
            case Medium -> currentMaxExp = (int) nCubed;
            case Slow -> currentMaxExp = (int) (1.25 * nCubed);
            case Parabolic -> currentMaxExp = (int) (1.2 * nCubed - 15 * Math.pow(N, 2) + 100 * N - 140);
            case Erratic -> {
                if (1 <= N && N <= 50) {
                    currentMaxExp = (int) (nCubed * (100 - N) / 50);
                } else if (51 <= N && N <= 68) {
                    currentMaxExp = (int) (nCubed * (150 - N) / 100);
                } else if (69 <= N && N <= 98) {
                    currentMaxExp = (int) (nCubed * (
                                                1.274 - (1.0 / 50) * Math.floor(N / 3.0) - calculateP()
                                        ));
                } else if (99 <= N && N <= 100) {
                    currentMaxExp = (int) (nCubed * (160 - N) / 100);
                }
            }
            case Fluctuating -> {
                if (1 <= N && N <= 15) {
                    currentMaxExp = (int) (nCubed * ((24 + Math.floor((N + 1) / 3.0)) / 50));
                } else if (16 <= N && N <= 35) {
                    currentMaxExp = (int) (nCubed * ((14 + N) / 50.0));
                } else if (36 <= N && N <= 100) {
                    currentMaxExp = (int) (nCubed * ((32 + Math.floor(N / 2.0)) / 50));
                }
            }
        }

        return currentMaxExp;
    }

    private double calculateP() {
        double[] pValues = {0.0, 0.008, 0.014};
        return pValues[this.getLevel() % 3];
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Model.Pokemon AI
    // ------------------------------------------------------------------------------------------------------------------

    /**
     * Recursively choose a random move in the Pokémon's move pool
     * @return A random move
     */
    public Move chooseMove() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1,4);
        Move move = null;
        ArrayList<Move> moves = this.getAttacks();

        if(randomNumber == 1){
            if(moves.getFirst() != null){
                move = moves.getFirst();
            }
            if(moves.getFirst() == null){
                chooseMove();
            }
        }
        if(randomNumber == 2){
            if(moves.get(1) != null){
                move = moves.get(1);
            }
            if(moves.get(1) == null){
                chooseMove();
            }
        }
        if(randomNumber == 3){
            if(moves.get(2) != null){
                move = moves.get(2);
            }
            if(moves.get(2) == null){
                chooseMove();
            }
        }
        if(randomNumber == 4){
            if(moves.get(3) != null){
                move = moves.get(3);
            }
            if(moves.get(3) == null){
                chooseMove();
            }
        }
        return move;
    }
}
