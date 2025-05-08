package View.FightView.Text;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;


public class TextBubble extends HBox implements Bubble {

    private final Queue<String> messageQueue = new LinkedList<>();
    private boolean isDisplayingQueue = false;

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
        for (String msg : messages) {
            messageQueue.offer(msg);
        }

        if (!isDisplayingQueue) {
            displayNextMessage();
        }
    }

    private void displayNextMessage() {
        String next = messageQueue.poll();
        if (next == null) {
            isDisplayingQueue = false;
            return;
        }

        isDisplayingQueue = true;
        showMessage(next);

        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> displayNextMessage());
        pause.play();
    }

}
