package Controller.Fight.Battle.Events;

public abstract class BattleEvent implements Event {
    private Runnable onFinish;

    public void setOnFinish(Runnable onFinish) {
        this.onFinish = onFinish;
    }

    protected void onFinish() {
        if (onFinish != null) {
            System.out.println("On finish");
            onFinish.run();
        }
    }

    @Override
    public void execute() {
    }

    public String getName(){
        return getClass().getSimpleName();
    }
}
