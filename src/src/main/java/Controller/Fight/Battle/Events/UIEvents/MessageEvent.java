package Controller.Fight.Battle.Events.UIEvents;

import Controller.Fight.Battle.Events.BattleEvent;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;

public class MessageEvent extends BattleEvent {
    String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        TextBubble bubble = BattleView.getTextBubble();
        bubble.setOnMessageComplete(this::onFinish);
        bubble.addMessage(message);
    }

    public String getMessage() {
        return message;
    }
}
