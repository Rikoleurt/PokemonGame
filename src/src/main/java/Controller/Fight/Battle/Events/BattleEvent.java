package Controller.Fight.Battle.Events;

import java.io.IOException;

/**
 * This class represents the battle events available, the method execute has to contain a onFinish call to
 * respect the chaining.
 */

public abstract class BattleEvent implements Event {
    private Runnable onFinish;

    public void setOnFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    protected void onFinish() throws IOException {
        if (onFinish != null) {
            onFinish.run();
        }
    }

    /**
     * Executes the event. One must use onFinish() to respect the chaining
     * (mandatory in the body of execute())
     */
    public abstract void execute() throws IOException;

    public String getName(){
        return getClass().getSimpleName();
    }
}
