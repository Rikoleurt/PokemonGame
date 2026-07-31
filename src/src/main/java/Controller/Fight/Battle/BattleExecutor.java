package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BattleExecutor {

    private static BattleExecutor instance;
    private final Queue<BattleEvent> events;
    private int turn;
    private Runnable onAllEventsFinishedPending;
    private boolean isBusy;

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

    public void executeEvents(Runnable onAllEventsFinished) throws IOException {
//        getEventsFromQueue();
//        System.out.println("Executing Battle Events");
        if (!events.isEmpty()) {
            BattleEvent event = events.poll();
//            System.out.println("Battle Event being executed : " + event);
            event.setOnFinish(() -> {
                try {
                    executeEvents(onAllEventsFinished);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
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
        System.out.println("-------------------------- Turn number " + turn + " --------------------------");
        turn++;
    }
    public int getTurn() {
        return turn;
    }
}
