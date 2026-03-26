package View.SettingView;

import Utils.SceneManager;
import Utils.SongManager;
import View.Game.Battle.InfoBars.Bar;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

public class AudioView extends BorderPane {

    Slider volumeSlider;
    static Font font = Font.loadFont(Objects.requireNonNull(Bar.class.getResource("/font/pokemonFont.ttf")).toExternalForm(), 32);

    public AudioView(Runnable onClose) {
        init(onClose);
    }

    public void init(Runnable onClose) {
        setStyle("-fx-font-family: '" + font.getName() + "'; -fx-font-size: 18px;");

        Button backButton = makeBackButton(onClose);
        onBackPressed(backButton);

        Label label = new Label("Audio :");

        volumeSlider = new Slider(0, 100, SongManager.getInstance().getVolume() * 100);
        volumeSlider.setPrefWidth(250);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setMajorTickUnit(25);
        volumeSlider.setMinorTickCount(4);
        volumeSlider.setBlockIncrement(1);

        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            SongManager.getInstance().setVolume(newVal.doubleValue() / 100.0);
        });

        HBox audioBarBox = new HBox(15);
        audioBarBox.setAlignment(Pos.CENTER);
        audioBarBox.getChildren().addAll(label, volumeSlider);

        VBox globalBox = new VBox(audioBarBox);
        globalBox.setAlignment(Pos.CENTER);

        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        setRight(topBar);
        setLeft(globalBox);
        setPadding(new Insets(20));
    }

    private Button makeBackButton(Runnable onClose) {
        Button backButton = new Button("Exit");
        backButton.getStyleClass().add("exit-audio-button");
        backButton.setOnAction(e -> onClose.run());
        return backButton;
    }

    private void onBackPressed(Button backButton) {
        Platform.runLater(() -> {
            Scene scene = SceneManager.getStage().getScene();
            if (scene != null) {
                scene.addEventFilter(KeyEvent.KEY_PRESSED, ev -> {
                    if (ev.getCode() == KeyCode.B) {
                        backButton.fire();
                    }
                });
            }
        });
    }
}