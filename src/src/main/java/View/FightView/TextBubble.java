package View.FightView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import javafx.scene.input.KeyEvent;

public class TextBubble extends HBox {

    static Font font = Font.loadFont(TextBubble.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    // Text Label
    Label message = new Label(null);
    private boolean isMessageVisible = false;

    public TextBubble() {
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10));
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


    void showMessage(String message) {
        this.message.setText(message);
        this.message.setVisible(true);
        setMessageVisible(true);
    }

    public void hideMessage() {
        this.message.setVisible(false);
        setMessageVisible(false);
    }

    public boolean isMessageVisible() {
        return isMessageVisible;
    }

    public void setMessageVisible(boolean b) {
        isMessageVisible = b;
    }
}
