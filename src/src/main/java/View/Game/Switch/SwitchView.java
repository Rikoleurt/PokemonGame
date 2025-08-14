package View.Game.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.MessageEvent;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import static View.Game.Battle.BattleView.getStatBubble;
import static View.Game.Battle.BattleView.terrain;

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
            if (player.getFrontPokemon() == pokemon) return;
            SceneManager.switchStageTo(SceneManager.getFightView());
            executor.addEvent(new MessageEvent(textBubble, player.getFrontPokemon().getName() + " stop!"));
            player.setFront(pokemon, terrain);
            executor.addEvent(new MessageEvent(textBubble, player.getFrontPokemon().getName() + " go!"));
            BattleView.getPlayerBar().setPokemon(player.getFrontPokemon());
            executor.executeNext(()-> {
                BattleView.getFightButtons().resetFightButtons();
                BattleView.getPlayerBar().setVisible(true);
            });
        });
        return button;
    }
}
