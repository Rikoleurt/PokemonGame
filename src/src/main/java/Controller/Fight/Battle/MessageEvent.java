package Controller.Fight.Battle;

import View.FightView.Text.TextBubble;
import javafx.application.Platform;

import java.util.Queue;

public class MessageEvent extends BattleEvent {

    private String message;
    private TextBubble bubble;

    public MessageEvent(String message, TextBubble bubble) {
        this.message = message;
        this.bubble = bubble;
    }

    @Override
    public void execute() {
        Platform.runLater(() -> bubble.addMessage(message));
    }
}
