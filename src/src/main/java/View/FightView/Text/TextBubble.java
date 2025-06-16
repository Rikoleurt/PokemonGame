package View.FightView.Text;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;


public class TextBubble extends HBox implements Bubble {

    private final Queue<String> messageQueue = new LinkedList<>();
    private volatile boolean isDisplayingQueue = false;

    private Runnable onMessageComplete;

    private static final int TYPING_SPEED_MS = 40;
    private boolean isTyping = false;
    private String fullMessage = "";  // Message complet en cours
    private Timeline typingTimeline;


    static Font font = Font.loadFont(TextBubble.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    // Text Label
    Label message = new Label(null);
    private boolean isMessageVisible = false;

    public TextBubble() {
        setAlignment(Pos.CENTER_LEFT);
        //setPadding(new Insets(10));
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);" + // Fond noir semi-transparent
                "-fx-border-color: darkgray;" +               // Bordure gris foncé
                "-fx-border-width: 4px;" +                    // Épaisseur de la bordure
                "-fx-border-radius: 15px;" +                  // Coins arrondis
                "-fx-background-radius: 15px;");              // Coins arrondis

        message.setFont(font);
        message.setStyle("-fx-text-fill: white;" +    // Couleur du texte en blanc
                "-fx-padding: 15px;" +       // Espacement interne
                "-fx-alignment: center-left;" + // Alignement du texte
                "-fx-wrap-text: true;");     // Gestion des retours à la ligne
        message.setVisible(false);
        message.setPrefSize(1000,150);
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

    public boolean isDisplayingQueue() {
        return isDisplayingQueue;
    }

    public void setDisplayingQueue(boolean displayingQueue) {
        isDisplayingQueue = displayingQueue;
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

    public void addMessage(String messageText) {
        messageQueue.add(messageText);
        if (!isDisplayingQueue) {
            displayNextMessage();
        }
    }

    private void displayNextMessage() {
        if (messageQueue.isEmpty()) {
            isDisplayingQueue = false;
            return;
        }

        isDisplayingQueue = true;
        fullMessage = messageQueue.poll();
        message.setText("");
        isTyping = true;

        typingTimeline = new Timeline();
        for (int i = 0; i <= fullMessage.length(); i++) {
            final int index = i;
            typingTimeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * TYPING_SPEED_MS), e -> {
                        message.setText(fullMessage.substring(0, index));
                    })
            );
        }

        typingTimeline.setOnFinished(e -> isTyping = false);
        typingTimeline.play();
    }

    public void handleKeyPress(KeyCode code) {
        if (code == KeyCode.SPACE || code == KeyCode.ENTER) {
            if (isTyping) {
                typingTimeline.stop();
                message.setText(fullMessage);
                isTyping = false;
            } else {
                if (onMessageComplete != null) {
                    Runnable callback = onMessageComplete;
                    onMessageComplete = null;
                    callback.run();
                }
                displayNextMessage();
            }
        }
    }



    public void setOnMessageComplete(Runnable onMessageComplete) {
        this.onMessageComplete = onMessageComplete;
    }

}
