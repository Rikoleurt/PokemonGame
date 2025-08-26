package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.Event;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;

import java.util.LinkedList;
import java.util.Queue;

public class BattleExecutor {

    private static BattleExecutor instance;
    private final Queue<BattleEvent> events;
    private int turn;

    public BattleExecutor() {
        this.events = new LinkedList<>();
    }

    public static synchronized BattleExecutor getInstance() {
        if (instance == null) {
            instance = new BattleExecutor();
        }
        return instance;
    }

    public void addEvent(BattleEvent event) {
        events.add(event);
    }

    public void clearEvents() {
        if(events.isEmpty()) return;
        events.clear();
    }

    public void executeNext(Runnable onAllEventsFinished) {
        getEventsFromQueue();
        if (!events.isEmpty()) {
            BattleEvent event = events.poll();
            System.out.println("Next event: " + event.getName());
            event.setOnFinish(() -> executeNext(onAllEventsFinished));
            event.execute();
        } else {
            if (onAllEventsFinished != null) {
                onAllEventsFinished.run();
            }
        }
    }

    public void getEventsFromQueue() {
        for(BattleEvent event : events) {
            System.out.println("Battle Event : " + event.getName() + ", size : " + events.size());
            if(event instanceof MessageEvent) {
                System.out.println("Battle Event : " + event.getName() + ", message : " + ((MessageEvent) event).getMessage() + ", size : " + events.size());
            }
        }
        System.out.println();
    }

    public Queue<BattleEvent> getEvents() {
        return events;
    }

    public void increaseTurn() {
        System.out.println("Turn : " + turn);
        turn++;
    }
    public int getTurn() {
        return turn;
    }
}
