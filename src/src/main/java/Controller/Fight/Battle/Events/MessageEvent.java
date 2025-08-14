package Controller.Fight.Battle.Events;

import View.Game.Battle.Text.TextBubble;

public class MessageEvent extends BattleEvent {
    TextBubble bubble;
    String message;

    public MessageEvent(TextBubble bubble, String message) {
        this.bubble = bubble;
        this.message = message;
    }

    @Override
    public void execute() {
        bubble.setOnMessageComplete(this::onFinish);
        bubble.addMessage(message);
    }
}
