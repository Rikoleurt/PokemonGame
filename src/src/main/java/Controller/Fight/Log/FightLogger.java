package Controller.Fight.Log;

import View.FightView.Text.TextBubble;

public class FightLogger implements Logger {
    private final TextBubble bubble;

    public FightLogger(TextBubble bubble) {
        this.bubble = bubble;
    }

    @Override
    public void printLog(String message) {
        bubble.showMessages(message);
    }
}
