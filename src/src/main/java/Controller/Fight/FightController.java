package Controller.Fight;

import View.FightView.Text.TextBubble;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class FightController {

    private final TextBubble textBubble;

    public FightController(TextBubble textBubble) {
        this.textBubble = textBubble;
    }

    /**
     * Registers the keyboard's space (or enter) key to skip fights dialogues
     */
    public void attachKeyHandlers(Scene scene) {
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.SPACE || code == KeyCode.ENTER) {
                textBubble.handleKeyPress(code);
            }
        });
    }
}

