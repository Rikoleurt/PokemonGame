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
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import static View.Game.Battle.BattleView.*;

public class SwitchView extends BorderPane {
    Player player;
    TextBubble textBubble;
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

        root.getChildren().addAll(hBox, hBox2, hBox3, backButton);
        setCenter(root);

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
        button.setText(pokemon.getName());
        button.setDisable(player.getFrontPokemon() == pokemon);
        button.setOnAction(_ -> {
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
        });
        return button;
    }
}
