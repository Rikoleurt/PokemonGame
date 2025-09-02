package Model.Pokemon;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Controller.Fight.Battle.Events.UIEvents.UpdateBarEvent;
import Model.Pokemon.AttackEnum.AttackMode;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Attacks.SetUpMove;
import Model.Pokemon.PokemonEnum.Experience;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.PokemonEnum.Nature;
import Model.Pokemon.PokemonEnum.Type;
import Model.Pokemon.Attacks.DebrisAttack;
import java.io.*;
import java.util.*;
import View.Console.BattleLayout.BattleConsole;
import View.Game.Battle.BattleView;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class Pokemon {
    //region Variable
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

    boolean isTurn = true;
    boolean isDeadFromStatus = false;

    BattleExecutor executor = BattleExecutor.getInstance();
    BattleConsole console = BattleConsole.getInstance();
    //endregion

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


    //region Getter

    public String getName(){
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
        return HP <= 0;
    }
    public ArrayList<Move> getAttacks() {
        return moves;
    }
    public Move getAttack(Move move){
        try {
            return moves.get(moves.indexOf(move));
        } catch (IndexOutOfBoundsException e) {
            return moves.getLast();
        }
    }
    public void levelUp() {
        level++;
        int oldHP = HP;
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
    public int getBaseSpeed() {
        return baseSpeed;
    }
    public boolean hasPriority(Pokemon other){
        return speed > other.speed;
    }
    //endregion

    //region Setter
    public void setStatus(Status status) {
        if(status == Status.KO) {
            setHP(0);
        }
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
    //endregion

    //region Attack
    /**
     * Verifies which instance is the player Pokémon's next move and applies the effect on the target according to the
     * move category (i.e. physical/special attack, a debris attack, a status attack or an upgrade move)
     * @param target The target of the player
     * @param move The move it uses
     * @param terrain The terrain the Pokémon are on
     */
    public void attack(Pokemon target, Move move, Terrain terrain) {
        isTurn = true;
        Move m = getAttack(move); // Gets the move from the move pool
        applyStatusEffect(target, move); // Apply the effect of the status
        if(m instanceof Attack attack && isTurn){
            executor.addEvent(new MessageEvent(name + " uses " + attack.getName()));
            if(!canHit(m)){
                executor.addEvent(new MessageEvent("It missed!"));
            } else if((status == Status.normal || status == Status.cursed || status == Status.burned || status == Status.paralyzed || status == Status.freeze || status == Status.attracted || status == Status.confused || status == Status.asleep || status == Status.poisoned || status == Status.badlyPoisoned)){
                int damage = (int) totalDamage((Attack) getAttack(attack), this, target);
                target.setHP(Math.max(0, target.getHP() - damage)); // Apply the damage
//                System.out.println(target.getHP() + ", damage : " + damage);
                executor.addEvent(new UpdateBarEvent(target));
            }
        }
        if(m instanceof DebrisAttack debrisAttack && isTurn){
            executor.addEvent(new MessageEvent(name + " uses " + debrisAttack.getName()));
            terrain.setDebris(debrisAttack.getDebris());
        }
        if(m instanceof StatusAttack statusAttack && isTurn){
            executor.addEvent(new MessageEvent(name + " uses " + statusAttack.getName()));
            if(!canHit(m)){
                executor.addEvent(new MessageEvent("It missed!"));
                return;
            } else {
                target.status = setStatus(target, statusAttack);
                BattleView.getOpponentBar().refreshStatus();
                BattleView.getPlayerBar().refreshStatus();
            }
        }
        if(m instanceof SetUpMove setUpMove && isTurn){
            executor.addEvent(new MessageEvent(name + " uses " + setUpMove.getName()));
            switch (setUpMove.getStat()) {
                case "atk" -> {
                    target.atkRaise += setUpMove.getRaiseLevel();
                    writeStatUpgradeMsg(target, "attack", setUpMove);
                }
                case "def" -> {
                    target.defRaise += setUpMove.getRaiseLevel();
                    writeStatUpgradeMsg(target, "defense", setUpMove);
                }
                case "speed" -> {
                    target.speedRaise += setUpMove.getRaiseLevel();
                    writeStatUpgradeMsg(target, "speed", setUpMove);
                }
                case "atkSpe" -> {
                    target.atkSpeRaise += setUpMove.getRaiseLevel();
                    writeStatUpgradeMsg(target, "special attack", setUpMove);
                }
                case "defSpe" -> {
                    target.defSpeRaise += setUpMove.getRaiseLevel();
                    writeStatUpgradeMsg(target, "special defense", setUpMove);
                }
                case "precision" -> {
                    updatePrecision(target, setUpMove.getRaiseLevel());
                    writeStatUpgradeMsg(target, "precision", setUpMove);
                }
            }
            updateStatChanges();
        }
    }
    /**
     * Calculates the total damage (includes critical hits and the stab)
     * @param attack The attack
     * @param launcher The Pokémon that launches the attack
     * @param target The chosen target
     * @return The total damage done to the target
     */
    public double totalDamage(Attack attack, Pokemon launcher, Pokemon target) {
        float power = attack.getPower();
        double augmentedDamage;
        if(attack.isStab(launcher)) {
            power *= 1.5f;
        }
        if(attack.isCritical(launcher)){
            augmentedDamage = launcher.getAttack(attack).criticalDamage(launcher);
            executor.addEvent(new MessageEvent( "Critical hit !"));
            return calculateEffectiveness(attack, launcher, target, power) * augmentedDamage;
        }
        return calculateEffectiveness(attack, launcher, target, power);
    }
    /**
     * Checks if the move is effective or not (only works for physical and special attacks)
     * @param move The move that is used
     * @param launcher The Pokémon that launches the move
     * @param target The Pokémon that will get attacked
     * @param power The power of the move
     * @return The attack damage without taking into account critical/stab hits
     */
    private double calculateEffectiveness(Move move, Pokemon launcher, Pokemon target, float power) {
        List<Type> targetWeaknesses = weaknessesTable(target);
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
                    executor.addEvent(new MessageEvent("The attack is super effective !"));
                    return physicalDamages * effectivenessCoefficient;
                }
                if (targetImmunities.contains(move.getType())) {
                    console.log("This attack does not affect the pokemon");
                    return 0;
                }
                if (targetResistances.contains(move.getType())) {
                    effectivenessCoefficient = 0.5f;
                    executor.addEvent(new MessageEvent("The attack is not very effective..."));
                    return physicalDamages * effectivenessCoefficient;
                } else {
                    return physicalDamages;
                }
            case special:
                double specialDamages = ((((launcherLevel * 0.4 + 2) * launcherAtkSpe * power) / targetDefSpe) / 50) + 2;
                if (targetWeaknesses.contains(move.getType())) {
                    effectivenessCoefficient = 2;
                    console.log("The attack is super effective !");
                    executor.addEvent(new MessageEvent("The attack is super effective !"));
                    return specialDamages * effectivenessCoefficient;
                }
                if (targetImmunities.contains(move.getType())) {
                    executor.addEvent(new MessageEvent( "The attack does not affect " + target.name));
                    return 0;
                }
                if (targetResistances.contains(move.getType())) {
                    effectivenessCoefficient = 0.5f;
                    executor.addEvent(new MessageEvent( "The attack is not very effective..."));
                    return specialDamages * effectivenessCoefficient;
                } else {
                    return specialDamages;
                }
        }
        return 0;
    }
    //endregion

    //region Status
    /**
     * Sets the status according to the element table
     * @param target The target the player's Pokémon attacks
     * @param statusMove The status attack it uses
     * @return The status of the target
     */
    private Status setStatus(Pokemon target, StatusAttack statusMove) {
        if(target.getStatus() != Status.normal){
            executor.addEvent(new MessageEvent(target.name + " is already " + target.getStatus() + "! It won't have any effect."));
        }
        if(immunitiesTable(target).contains(statusMove.getType())){
            executor.addEvent(new MessageEvent("This attack does not affect " + name));
            return null;
        }
        if(target.getStatus() == Status.normal){
            return statusMove.getStatus();
        }
        return target.status;
    }

    /**
     * Applies the effect on the Pokémon before it attacks
     * @param target The target the player's Pokémon attacks
     * @param move Move it uses
     */
    private void applyStatusEffect(Pokemon target, Move move){
        Random random = new Random();
        if(getAttack(move).getMode() == AttackMode.physical && status == Status.burned){
            executor.addEvent(new MessageEvent(name + " uses " + move.getName()));
            target.HP -= (int) totalDamage((Attack) getAttack(move), this, target)/2;
            executor.addEvent(new UpdateBarEvent(target));
            isTurn = false;
            return;
        }

        if(status == Status.paralyzed){
            boolean paralyzed = random.nextInt(0,4) == 0;
            executor.addEvent(new MessageEvent( name + " is paralyzed!"));
            System.out.println("isTurn : " + isTurn);
            System.out.println("paralyzed : " + paralyzed);
            if(paralyzed){
                executor.addEvent(new MessageEvent(name + " can't move!"));
                isTurn = false;
                return;
            }
        }

        if(status == Status.freeze){
            boolean frozen = random.nextInt(0,4) < 3;
            if(frozen){
                executor.addEvent(new MessageEvent(name + " is frozen solid!"));
                isTurn = false;
                return;
            } else {
                executor.addEvent(new MessageEvent(name + " is not frozen anymore!"));
                setStatus(Status.normal);
                BattleView.getOpponentBar().refreshStatus();
                BattleView.getPlayerBar().refreshStatus();
            }
        }

        if(status == Status.asleep){
            int asleep = random.nextInt(0,3);
            if(asleep == 0){
                executor.addEvent(new MessageEvent(name + " woke up!"));
                setStatus(Status.normal);
                BattleView.getOpponentBar().refreshStatus();
                BattleView.getPlayerBar().refreshStatus();
            }
            if(asleep > 0){
                executor.addEvent(new MessageEvent(name + " is asleep!"));
                ++wakeUp;
                if(wakeUp == 4){
                    executor.addEvent(new MessageEvent(name + " woke up!"));
                    setStatus(Status.normal);
                    BattleView.getOpponentBar().refreshStatus();
                    BattleView.getPlayerBar().refreshStatus();
                }
                if(wakeUp != 4) {
                    isTurn = false;
                    return;
                }
            }
        }

        if(status == Status.attracted && !Objects.equals(gender, target.gender)){
            boolean inLove = random.nextInt(0,2) == 1;
            if(inLove){
                executor.addEvent(new MessageEvent(name + " is attracted to " + target.name));
                isTurn = false;
                return;
            }
        }

        if(status == Status.confused){
            int confused = random.nextInt(0,2);
            ++healConfusion;
            if(confused == 0){
                if(healConfusion < 4) {
                    console.log(name + " is confused!");
                    executor.addEvent(new MessageEvent(name + " is confused!"));
                }
            }
            if(confused == 1){
                executor.addEvent(new MessageEvent(name + " is confused!"));
                executor.addEvent(new MessageEvent(name + " hurt itself in its confusion!"));
                HP -= (int) (((((level * 0.4 + 2) * atk * 40) / def) / 50) + 2);
                executor.addEvent(new UpdateBarEvent(this));
                isTurn = false;
                return;
            }
            if(healConfusion > 4){
                executor.addEvent(new MessageEvent(name + " snapped out of confusion!"));
                setStatus(Status.normal);
                BattleView.getOpponentBar().refreshStatus();
                BattleView.getPlayerBar().refreshStatus();
                healConfusion = 0;
                executor.addEvent(new MessageEvent(name + " uses " + move.getName()));
                target.HP -= (int) totalDamage((Attack) getAttack(move), this, target)/2;
                executor.addEvent(new UpdateBarEvent(target));
            }
        }
        // To implement
//        if(status == Status.fear){
//            console.log(name + " is feared! It can't move!");
//            System.out.println(name + " is feared! It can't move!");
//            executor.addEvent(new MessageEvent(name + " is feared! It can't move!"));
//        }
    }
    /**
     * Updates the status of the Pokémon
     */
    public void registerStatusEffect(){
        switch(status){
            case attracted, asleep:
                break;
            case burned:
                executor.addEvent(new MessageEvent(name + " is burned!"));
                setHP(Math.max(0,HP - (maxHP/16)));
                executor.addEvent(new MessageEvent(name + " suffers from its burn!"));
                executor.addEvent(new UpdateBarEvent(this));
                break;
            case poisoned:
                executor.addEvent(new MessageEvent(name + " is poisoned!"));
                setHP(Math.max(0,HP - (maxHP/8)));
                executor.addEvent(new MessageEvent(name + " suffers from poison!"));
                executor.addEvent(new UpdateBarEvent(this));
                break;
            case badlyPoisoned:
                executor.addEvent(new MessageEvent(name + " is badly poisoned!"));
                setHP(Math.max(0,HP - (maxHP/16) * poisonCoefficient));
                executor.addEvent(new MessageEvent(name + " suffers from poison!"));
                executor.addEvent(new UpdateBarEvent(this));
                ++poisonCoefficient;
                break;
            case confused:
//            case fear:
//                healFear++;
//                if(healFear == 1) {
//                    this.setStatus(Status.normal);
//                    healFear = 0;
//                }
//                break;
//            case cursed:
//                HP = HP - (maxHP/4);
        }
//        System.out.println("isDead : " + isDeadFromStatus());
        if(status == Status.KO) isDeadFromStatus = true;
//        System.out.println("isDead : " + isDeadFromStatus());
    }

    public boolean isDeadFromStatus(){
        return isDeadFromStatus;
    }

    //endregion

    //region Type table
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
            case fire, water, grass, electric, ice, fighting, poison, psychic, bug, rock, dragon, steel:
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
            case fairy:
                immunities.add(Type.dragon);
                break;
            default:
                immunities = new ArrayList<>();
                break;
        }

        return immunities;
    }
    //endregion

    //region Experience
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

            int baseExperience = getBaseExperience(defeatedPokemon.name, inputStream);

            if (baseExperience == -1) {
                System.out.println("Can't find base exp for: " + defeatedPokemon.name);
                return 0;
            }

            return ((a * e * t * baseExperience * defeatedPokemon.getLevel()) / 7);
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
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
            System.out.println("Exception : " + e.getMessage());
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
    //endregion

    //region AI
    /**
     * Recursively choose a random move in the Pokémon's move pool
     * @return A random move
     */
    public Move chooseMove() {
        ArrayList<Move> movePool = getAttacks();
        if (movePool == null || movePool.isEmpty()) return null;

        ArrayList<Move> pool = new ArrayList<>();

        for (Move move : movePool) if (move != null) pool.add(move);

        if (pool.isEmpty()) return null;
        Random random = new Random();

        return pool.get(random.nextInt(pool.size()));
    }
    //endregion

    //region Helpers
    private void writeStatUpgradeMsg(Pokemon target, String stat, SetUpMove move) {
        switch (move.getRaiseLevel()){
            case 1 -> {
                console.log(target.name + " raises its " + stat);
                executor.addEvent(new MessageEvent(target.name  + " raises its " + stat));
            }
            case 2 -> {
                console.log(target.name + " sharply raises its " + stat);
                executor.addEvent(new MessageEvent(target.name  + " sharply raises its " + stat));
            }
            case -1 -> {
                console.log(target.name + "'s " + stat + " decreased");
                executor.addEvent(new MessageEvent(target.name + "'s " + stat + " decreased"));
            }
            case -2 -> {
                console.log(target.name + "'s " + stat + " harshly decreased");
                executor.addEvent(new MessageEvent(target.name + "'s " + stat + " harshly decreased"));
            }
        }
    }

    private boolean canHit(Move move){
        boolean canHit = false;
        double precision;
        Random rand = new Random();
        int imprecision = rand.nextInt(1,100);

        if(move instanceof Attack){
            precision = ((Attack) move).getPrecision();
            // If the precision of the attack is superior to the random integer then the attack hits
            if(precision > imprecision){
                canHit = true;
            }
        }
        if(move instanceof StatusAttack){
            precision = ((StatusAttack) move).getPrecision();
            if(precision > imprecision){
                canHit = true;
            }
        }
        return canHit;
    }

    /**
     * Update the stat modifier each turn
     */
    private void updateStatChanges(){
        this.atk = applyStatModifier(atk, atkRaise);
        this.def = applyStatModifier(def, defRaise);
        this.speed = applyStatModifier(speed, speedRaise);
        this.atkSpe = applyStatModifier(atkSpe, atkSpeRaise);
        this.defSpe = applyStatModifier(defSpe, defSpeRaise);
    }

    private int applyStatModifier(int baseStat, int stage){
        if (stage > 6) stage = 6;
        if (stage < -6) stage = -6;
        int[] multipliersNumerator = {2, 2, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8};
        int[] multipliersDenominator = {8, 7, 6, 5, 4, 3, 2, 2, 2, 2, 2, 2, 2};
        return (baseStat * multipliersNumerator[stage + 6]) / multipliersDenominator[stage + 6];
    }

    private void updatePrecision(Pokemon target, int stage){
        if(stage > 6) stage = 6;
        if (stage < -6) stage = -6;
        ArrayList<Move> movePool = target.getAttacks();
        double precision = 100;
        for(Move move : movePool) {
            System.out.println("Updating precision...");
            double[] multipliers = {1.0 / 3, 3.0 / 8, 3.0 / 7, 1.0 / 2, 3.0 / 5, 3.0 / 4, 0, 4.0 / 3, 5.0 / 3, 2, 7.0 / 3, 8.0 / 3, 3};
            if (move instanceof Attack attack) {
                attack.setPrecision(precision * multipliers[stage + 6]);
                System.out.println(attack.getName() + ", precision : " + attack.getPrecision() + ", stage : " + stage);
            }
            if (move instanceof StatusAttack statusAttack){
                statusAttack.setPrecision(precision * multipliers[stage + 6]);
                System.out.println(statusAttack.getName() + ", precision : " + statusAttack.getPrecision() + ", stage : " + stage);
            }
        }
    }

    public int getEffectiveSpeed() {
        int s = speed;
        if (status == Status.paralyzed) s = s / 2;
        if (s < 1) s = 1;
        return s;
    }


    //endregion
}
