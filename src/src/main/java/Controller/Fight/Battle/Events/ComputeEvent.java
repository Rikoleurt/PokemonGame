package Controller.Fight.Battle.Events;

public abstract class ComputeEvent<T> implements Event {
    public abstract T compute();
}
