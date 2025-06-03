package Controller.Fight.Battle.Events;

public abstract class BattleEvent implements Event {
    public void execute(){}
    public String getEventName() {
        return getClass().getName();
    }
    public BattleEvent() {}
}
