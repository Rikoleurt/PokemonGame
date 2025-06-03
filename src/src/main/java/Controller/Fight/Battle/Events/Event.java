package Controller.Fight.Battle.Events;

public interface Event {
    void execute(Runnable onFinished);
}
