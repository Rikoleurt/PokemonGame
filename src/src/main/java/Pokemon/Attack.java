package Pokemon;

public class Attack {
    String name;
    int damage;
    Type type;
    String Mode;


    public Attack(String name, int damage, Type type, String Mode) {
        this.damage = damage;
        this.type = type;
        this.Mode = Mode;
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


}
