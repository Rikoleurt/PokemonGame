package View.Game.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.AttackEvent;
import Controller.Fight.Battle.Events.MessageEvent;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Person.Player;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

import static View.Game.Battle.BattleView.*;

public class SwitchView extends BorderPane {

    static Font font = Font.loadFont(Objects.requireNonNull(Bar.class.getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);

    Player player;
    TextBubble textBubble;
    TextBubble switchBubble = new TextBubble();
    VBox root = new VBox();
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    HBox hBox3 = new HBox();
    BattleExecutor executor = BattleExecutor.getInstance();

    public SwitchView(Player player, TextBubble textBubble, Runnable onClose) {
        this.player = player;
        this.textBubble = textBubble;

        ObservableList<Node> components = this.getChildren();
        setButtons();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onClose.run());

        root.getChildren().addAll(hBox, hBox2, hBox3);
        setCenter(root);
        root.setPadding(new Insets(10, 10, 10, 10));
        HBox wrapper = new HBox();
        wrapper.setSpacing(10);
        wrapper.setAlignment(Pos.TOP_RIGHT);
        wrapper.getChildren().add(backButton);
        setTop(wrapper);
        switchBubble.setVisible(false);
        setBottom(switchBubble);
        setPadding(new Insets(20));
        root.setPadding(new Insets(20));

        setFocusTraversable(true);
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

    private void setButtons() {
        hBox.getChildren().clear();
        hBox2.getChildren().clear();
        hBox3.getChildren().clear();

        hBox.setSpacing(24);
        hBox2.setSpacing(24);
        hBox3.setSpacing(24);

        List<Pokemon> team = player.getTeam();
        for (int i = 0; i < team.size(); i++) {
            Button button = createButtons(team.get(i));
            if (i <= 1) hBox.getChildren().add(button);
            if (i > 1 && i <= 3) hBox2.getChildren().add(button);
            if (i > 3 && i <= 5) hBox3.getChildren().add(button);
        }
    }


    private Button createButtons(Pokemon pokemon) {
        Button button = new Button();
        button.setText(null);
        button.setGraphic(buildSwitchContent(pokemon));
        button.setDisable(player.getFrontPokemon() == pokemon);
        button.getStyleClass().add("switch-button");

        button.setOnAction(_ -> { onButtonPressed(pokemon); });

        HBox.setMargin(button, new Insets(12)); // espace autour de chaque bouton
        return button;
    }

    private Node buildSwitchContent(Pokemon p) {
        Label name = new Label(p.getName());
        name.getStyleClass().add("switch-name");

        int hp = p.getHP();
        int max = p.getMaxHP();
        Label hpText = new Label(hp + " / " + max);
        hpText.getStyleClass().add("switch-hp-text");

        ProgressBar bar = new ProgressBar();
        double ratio = max <= 0 ? 0 : Math.max(0, Math.min(1.0, (double) hp / max));
        bar.setProgress(ratio);
        bar.setPrefWidth(220);
        bar.getStyleClass().add("hp-bar");
        if (ratio > 0.5) {
            bar.setStyle("-fx-accent: #00C853;");
        } else if (ratio > 0.25) {
            bar.setStyle("-fx-accent: #F9A825;");
        } else {
            bar.setStyle("-fx-accent: #D32F2F;");
        }

        VBox box = new VBox(name, hpText, bar);
        box.setSpacing(6);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }
    private void onButtonPressed(Pokemon pokemon){
        if (pokemon.isKO()) {
            switchBubble.setVisible(true);
            switchBubble.showMessage(pokemon.getName() + " is not it its best shape... You can not switch.");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(ev -> switchBubble.setVisible(false));
            delay.play();
            return;
        }

        BattleButtons.getHBox1().setVisible(false);
        BattleButtons.getHBox2().setVisible(false);
        BattleView.getPlayerBar().setVisible(false);

        SceneManager.switchStageTo(SceneManager.getFightView());
        executor.addEvent(new MessageEvent(textBubble, player.getFrontPokemon().getName() + " stop!"));

        player.setFront(pokemon, terrain);
        executor.addEvent(new MessageEvent(textBubble, player.getFrontPokemon().getName() + " go!"));
        BattleView.getPlayerBar().setPokemon(player.getFrontPokemon());

        executor.executeNext(() -> {
            BattleView.getPlayerBar().setVisible(true);
            Pokemon npcPokemon = BattleView.getNpc().getFrontPokemon();
            Pokemon playerPokemon = player.getFrontPokemon();
            if (npcPokemon.getStatus() != Status.KO) {
                Move npcMove = npcPokemon.chooseMove();
                executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcMove, BattleView.getTerrain(), textBubble, BattleView.getPlayerBar(), executor));
            } else {
                executor.addEvent(new MessageEvent(textBubble, npcPokemon.getName() + " fainted."));
            }
            executor.executeNext(() -> {
                BattleView.getPlayerBar().setVisible(true);
                BattleView.getFightButtons().resetFightButtons();
            });
        });
    }
}
