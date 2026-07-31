package View;

import Utils.SceneManager;
import View.GameView.SelectFightView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExplanationAndRulesView extends BorderPane implements View {

    List<String> paragraphs = new ArrayList<>();

    public ExplanationAndRulesView() {
        init();
    }

    private void init(){
        VBox textContainer = new VBox();
        HBox buttonContainer = new HBox();

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
                        "- Attack with specific behavior aren't supported (i.e. Light Screen or Mur Lumière \n" +
                        "- There are only setup moves, status moves and physical/special attacks \n" +
                        "- Pokémon are all at level 50 with 15 IV stats." +
                        "- All the fights are 6v6";

        paragraphs.add(introduction);
        paragraphs.add(explanations);
        paragraphs.add(infos);

        Text text = new Text(introduction);
        textContainer.getChildren().add(text);
        text.setStyle("-fx-font-size: 30px;");
        text.setWrappingWidth(500);

        Button nextButton = new Button(">");
        textContainer.getChildren().add(nextButton);
        nextButton.setPrefSize(100, 50);
        nextButton.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        nextButton.setOnAction( e -> {
            text.setText("Showing new text");
        });

        Button backButton = new Button("<");
        textContainer.getChildren().add(backButton);
        backButton.setPrefSize(100, 50);
        backButton.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT);
        backButton.setOnAction( e -> {
            System.out.println("test");
        });

        Button playButton = new Button("Play");
        buttonContainer.getChildren().add(playButton);
        playButton.setPrefSize(100, 50);
        playButton.setOnAction( e -> onPlayPressed());
        playButton.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        playButton.setStyle("-fx-font-size: 20px;");

        setCenter(textContainer);
        setBottom(buttonContainer);

    }

    private void onPlayPressed() {
        SceneManager.switchStageTo(new SelectFightView());
    }
}
