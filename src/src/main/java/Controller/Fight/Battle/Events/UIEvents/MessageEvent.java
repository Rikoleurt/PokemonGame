package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import View.GameView.BattleViews.BattleView;
import View.GameView.BattleViews.Text.TextBubble;

import java.io.IOException;

public class MessageEvent extends BattleEvent {
    String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        TextBubble bubble = BattleView.getTextBubble();
        bubble.setOnMessageComplete(() -> {
            try {
                onFinish();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        bubble.addMessage(message);
    }

    public String getMessage() {
        return message;
    }
}
