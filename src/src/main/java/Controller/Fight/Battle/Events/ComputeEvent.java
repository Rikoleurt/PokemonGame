package Controller.Fight.Battle.Events;

import java.io.IOException;

public abstract class ComputeEvent<T> implements Event {
    public abstract T compute() throws IOException;
}
