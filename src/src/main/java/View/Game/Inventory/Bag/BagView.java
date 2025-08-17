package View.Game.Inventory.Bag;

import Model.Person.NPC;
import Model.Person.Player;

import View.Game.Battle.Text.TextBubble;
import View.Game.Inventory.Bag.Component.CategoryMenu;
import View.Game.Inventory.Bag.Component.PokemonList;
import View.Game.SceneManager;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class BagView extends BorderPane {
    Player player;
    NPC npc;
    PokemonList pokemonList;
    CategoryMenu categoryMenu;
    TextBubble textBubble;

    public BagView(Player player, NPC npc, TextBubble textBubble, Runnable onClose) {
        this.player = player;
        this.npc = npc;
        this.textBubble = textBubble;

        pokemonList = new PokemonList(player, 10);
        categoryMenu = new CategoryMenu(player, 20, textBubble, npc);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onClose.run());

        setLeft(pokemonList);
        setPadding(new Insets(30, 40, 30, 50));
        setCenter(categoryMenu);

        BorderPane.setMargin(pokemonList, new Insets(0, 40, 0, 0));

        VBox bottomBox = new VBox();
        bottomBox.setSpacing(10);
        bottomBox.getChildren().addAll(categoryMenu.getCategories().getBagBubble(), backButton);

        setBottom(bottomBox);

        setAlignment(backButton, Pos.CENTER_RIGHT);
        setAlignment(pokemonList, Pos.CENTER_LEFT);

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
}
