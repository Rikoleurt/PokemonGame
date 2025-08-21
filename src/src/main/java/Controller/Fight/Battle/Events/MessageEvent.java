package Controller.Fight.Battle.Events;

import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import org.w3c.dom.Text;

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
}
