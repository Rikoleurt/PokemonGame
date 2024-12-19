package Pokemon;

public class Attack {
    String name;
    int damage;
    Type type;
    String Mode;
    int PP;


    public Attack(String name, int damage, Type type, String Mode, int PP) {
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.Mode = Mode;
        this.PP = PP;
    }

    public int getDamage() {
        return damage;
    }

    public Type getType() {
        return type;
    }

    public String getMode() {
        return Mode;
    }
    public String getName() {
        return name;
    }
    public int getPP() {
        return PP;
    }

}
