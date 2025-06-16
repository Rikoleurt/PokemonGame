package Controller.Fight.Log;

import View.FightView.Text.TextBubble;

import java.util.ArrayList;
import java.util.List;

public class FightLogger implements Logger {
    private final TextBubble bubble;
    List<String> messages;

    public FightLogger(TextBubble bubble, List<String> messages) {
        this.bubble = bubble;
        this.messages = messages;
    }

    @Override
    public void printLog(String message) {
        bubble.showMessages(message);
    }

    @Override
    public void addLog(String message) {
        System.out.println("Adding " + message);
        messages.add(message);
    }

    @Override
    public String getLog() {
        if(messages.isEmpty()) return "";
        String nextMessage = messages.getFirst();
        int messageLength = messages.size();
        if(messageLength > 1) {
            System.out.println("Nb of messages: " + messageLength);
        }
        messages.removeFirst();
        return nextMessage;
    }
}
