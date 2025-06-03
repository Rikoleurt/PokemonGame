package Controller.Fight.Battle;

import Controller.Fight.Battle.Events.BattleEvent;

import java.util.Queue;

public class BattleExecutor {
    private final Queue<BattleEvent> battleEvents;

    public BattleExecutor(Queue<BattleEvent> battleEvents) {
        this.battleEvents = battleEvents;
    }

    public Queue<BattleEvent> getBattleEvents() {
        return battleEvents;
    }

    public void getEveryBattleEvent() {
        for (BattleEvent battleEvent : battleEvents) {
            System.out.println(battleEvent.getEventName());
        }
    }

    public void addEvent(BattleEvent battleEvent) {
        battleEvents.add(battleEvent);
    }

    public void executeNextEvent(){
        BattleEvent battleEvent = battleEvents.poll();
        if (battleEvent != null) {
            battleEvent.execute(this::executeNextEvent);
        }
    }
}
