package Controller.Fight.Battle.Events;

public abstract class BattleEvent implements Event {
    private Runnable onFinish;

    public void setOnFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    protected void onFinish() {
        if (onFinish != null) onFinish.run();
    }

    @Override
    public void execute() {
    }
}
