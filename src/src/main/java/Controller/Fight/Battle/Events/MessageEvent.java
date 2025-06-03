package Controller.Fight.Battle.Events;

import Controller.Fight.Log.Logger;
import View.FightView.Text.TextBubble;
import javafx.application.Platform;

public class MessageEvent extends BattleEvent {

    private final String message;
    private final Logger log;

    public MessageEvent(String message, Logger log) {
        this.message = message;
        this.log = log;
    }

    @Override
    public void execute(Runnable onFinished) {
        Platform.runLater(() -> log.printLog(message));
    }
}
