package Controller.Fight.Battle.Events;

import View.FightView.Text.Bubble;
import View.FightView.Text.TextBubble;

public class MessageEvent extends BattleEvent {
    TextBubble bubble;
    String message;

    public MessageEvent(TextBubble bubble, String message) {
        this.bubble = bubble;
        this.message = message;
    }

    @Override
    public void execute() {
        bubble.addMessage(message);
    }
}
