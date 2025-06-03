package Controller.Fight.Battle;

import java.util.LinkedList;
import java.util.Queue;

public class BattleExecutor {
    private final Queue<BattleEvent> battleEvents;

    BattleExecutor(Queue<BattleEvent> battleEvents) {
        this.battleEvents = battleEvents;
    }

    public Queue<BattleEvent> getBattleEvents() {
        return battleEvents;
    }

    public void addEvent(BattleEvent battleEvent) {
        battleEvents.add(battleEvent);
    }

    public void executeEvents() {
        battleEvents.forEach(BattleEvent::execute);
    }
}
