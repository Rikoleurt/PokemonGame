package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.MessageEvent;

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
        battleEvents.add(event);
    }

    public void executeEvents() {
        battleEvents.forEach(BattleEvent::execute);
    }

    public void executeNext(Runnable onAllEventsFinished) {
        getEventsFromQueue();
        if (!battleEvents.isEmpty()) {
            BattleEvent event = battleEvents.poll();
            event.setOnFinish(() -> executeNext(onAllEventsFinished));
            event.execute();
        } else {
            if (onAllEventsFinished != null) {
                onAllEventsFinished.run();
            }
        }
    }

    public void getEventsFromQueue() {
        for(BattleEvent event : battleEvents) {
            System.out.println("Battle Event : " + event.getName() + " size : " + battleEvents.size());
        }
        System.out.println();
    }

    public Queue<BattleEvent> getBattleEvents() {
        return battleEvents;
    }
}
