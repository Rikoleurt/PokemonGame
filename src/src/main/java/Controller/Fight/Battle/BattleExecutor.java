package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;

import java.util.LinkedList;
import java.util.Queue;

public class BattleExecutor {

    private static BattleExecutor instance;
    private final Queue<BattleEvent> battleEvents;

    private BattleExecutor() {
        this.battleEvents = new LinkedList<>();
    }

    public static synchronized BattleExecutor getInstance() {
        if (instance == null) {
            instance = new BattleExecutor();
        }
        return instance;
    }

    public void addEvent(BattleEvent event) {
        System.out.println("Added Event : " + event);
        battleEvents.add(event);
    }

    public void executeEvents() {
        battleEvents.forEach(BattleEvent::execute);
    }

    public void executeNext() {
        if (!battleEvents.isEmpty()) {
            BattleEvent event = battleEvents.poll();
            System.out.println("Executing next event : " + event.getName());
            event.setOnFinish(this::executeNext);
            event.execute();
        } else {
            System.out.println("Every events were executed.");
        }
    }
}
