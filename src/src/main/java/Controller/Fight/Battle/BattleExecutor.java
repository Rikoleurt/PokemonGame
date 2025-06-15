package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.Event;

import java.util.Queue;

public record BattleExecutor(Queue<BattleEvent> battleEvents) {

    public void executeEvents() {
        battleEvents.forEach(BattleEvent::execute);
    }

    public void executeNext() {
        if (!battleEvents.isEmpty()) {
            BattleEvent event = battleEvents.poll();
            event.setOnFinish(this::executeNext);
            event.execute();
        } else {
            System.out.println("Every events were executed.");
        }
    }
}
