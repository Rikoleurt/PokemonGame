package View.Game.Battle.Text;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.Queue;

public class StatBubble extends HBox implements Bubble {
    static Font font = Font.loadFont(StatBubble.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);
    Label message = new Label(null);
    private boolean isMessageVisible = false;
    private final Queue<String> messageQueue = new LinkedList<>();
    private volatile boolean isDisplayingQueue = false;

    public StatBubble() {
        hideBubble();
        setAlignment(Pos.TOP_RIGHT);
        setSpacing(30);
        //setPadding(new Insets(10));
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);" +
                "-fx-border-color: darkgray;" +
                "-fx-border-width: 4px;" +
                "-fx-border-radius: 15px;" +
                "-fx-background-radius: 15px;");

        message.setFont(font);
        message.setStyle("-fx-text-fill: white;" +
                "-fx-padding: 5px;" +
                "-fx-wrap-text: true;" +
                "-fx-font-size: 20");
        message.setVisible(false);
        message.setPrefSize(200,225);
        this.getChildren().addAll(message);
    }

    @Override
    public void showMessage(String message) {
        showBubble();
        this.message.setText(message);
        this.message.setVisible(true);
        setMessageVisible(true);
    }

    @Override
    public void hideMessage() {
        this.message.setVisible(false);
        setMessageVisible(false);
        hideBubble();
    }

    @Override
    public boolean isMessageVisible() {
        return isMessageVisible;
    }

    @Override
    public void setMessageVisible(boolean b) {
        isMessageVisible = b;
    }

    @Override
    public void hideBubble() {
        this.setVisible(false);
    }

    @Override
    public void showBubble() {
        this.setVisible(true);
    }

    public void showMessages(String... messages) {
        Platform.runLater(() -> {
            for (String msg : messages) {
                System.out.println("New message : " + msg);
                messageQueue.offer(msg);
            }

            if (!isDisplayingQueue) {
                displayNextMessage();
            }
        });
    }

    private void displayNextMessage() {
        String next = messageQueue.poll();
        if (next == null) {
            isDisplayingQueue = false;
            return;
        }

        isDisplayingQueue = true;
        showMessage(next);

//        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
//        pause.setOnFinished(e -> displayNextMessage());
//        pause.play();
    }
}
