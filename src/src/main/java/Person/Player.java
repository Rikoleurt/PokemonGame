package Person;

import Inventory.Inventory;

import java.util.List;
import java.util.Objects;
import java.awt.event.KeyEvent;

public class Player {

    String nickname;
    Inventory inventory;

    Player(String nickname, Inventory inventory) {
        this.nickname = nickname;
        this.inventory = inventory;
    }
    public String getNickname() {
        return nickname;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
