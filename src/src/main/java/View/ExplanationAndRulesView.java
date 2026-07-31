package View;

import Utils.SceneManager;
import View.GameView.SelectFightView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExplanationAndRulesView extends BorderPane implements View {

    List<String> paragraphs = new ArrayList<>();
    int currentIndex = 0;

    public ExplanationAndRulesView() {
        init();
    }

    private void init(){
        getStyleClass().add("explanation-and-rules-view");
        getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/style/explanation-and-rules-view.css"))
                        .toExternalForm()
        );

        VBox textContainer = new VBox();
        HBox nextBackContainer = new HBox();
        VBox textArea = new VBox();

        String introduction =
                "You will play a simulation of Pokémon battles against a neural network \n" +
                        "trained by Reinforcement Learning. The objective is to determine the agent's \n" +
                        "performances when fighting against human players. ";

        String explanations =
                "Here's some explanations about the simulator: \n" +
                        "- I implemented myself this Pokémon simulator \n " +
                        "- Bugs may arise, if you find any, please report them to me as soon as possible. \n" +
                        "- Keep in mind that this is the first test of the agent, it may be a bit weak. \n" +
                        "- You'll have to answer a few questions to determine the agent's performance at the end of simulation. \n";

        String infos =
                "Here's some informations about the simulator: \n" +
                        "- It implements a simple version of Pokémon.\n" +
                        "- No talents are implemented \n" +
                        "- Pokémons don't hold any items \n" +
                        "- Attack with specific behavior aren't supported (i.e. Light Screen or Mur Lumière)\n" +
                        "- There are only setup moves, status moves and physical/special attacks \n" +
                        "- Pokémon are all at level 50 with 15 IV stats.\n" +
                        "- All the fights are 6v6";

        paragraphs.add(introduction);
        paragraphs.add(explanations);
        paragraphs.add(infos);

        currentIndex = Math.clamp(currentIndex, 0, paragraphs.size() - 1);

        Text text = new Text(paragraphs.getFirst());
        text.setStyle("-fx-font-size: 24px;");
        text.setWrappingWidth(750);
        text.setTextAlignment(TextAlignment.LEFT);

        textArea.setPrefHeight(350);
        textArea.setMinHeight(350);
        textArea.setMaxHeight(350);
        textArea.setAlignment(javafx.geometry.Pos.CENTER);
        textArea.getChildren().add(text);

        Button nextButton = new Button(">");
        nextButton.setPrefSize(40, 40);
        nextButton.setStyle("-fx-font-size: 30px;");
        nextButton.setOnAction(e -> {
            if (currentIndex < paragraphs.size() - 1) {
                currentIndex++;
                text.setText(paragraphs.get(currentIndex));
            } else {
                onFinishedReading();
            }
        });

        Button backButton = new Button("<");
        backButton.setPrefSize(40, 40);
        backButton.setStyle("-fx-font-size: 30px;");
        backButton.setOnAction(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                text.setText(paragraphs.get(currentIndex));
            }
        });

        nextBackContainer.getChildren().addAll(backButton, nextButton);
        nextBackContainer.setAlignment(javafx.geometry.Pos.CENTER);
        nextBackContainer.setSpacing(700);

        textContainer.getChildren().addAll(textArea, nextBackContainer);
        textContainer.setSpacing(10);
        textContainer.setAlignment(javafx.geometry.Pos.CENTER);
        textContainer.setPadding(new javafx.geometry.Insets(50));

        Button playButton = new Button("Play");
        playButton.setPrefSize(100, 50);
        playButton.setOnAction(e -> onFinishedReading());
        playButton.setStyle("-fx-font-size: 20px;");

        setCenter(textContainer);
    }

    private void onFinishedReading() {
        SceneManager.switchStageTo(new SelectFightView());
    }
}
