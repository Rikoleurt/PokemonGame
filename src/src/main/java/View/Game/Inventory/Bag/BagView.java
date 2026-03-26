package View.Game.Inventory.Bag;

import Model.Person.NPC;
import Model.Person.Player;

import View.Game.Battle.Text.TextBubble;
import View.Game.Inventory.Bag.Component.CategoryMenu;
import View.Game.Inventory.Bag.Component.PokemonList;
import Utils.SceneManager;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

        pokemonList = new PokemonList(player, 10); // Initiate the Pokémon list
        categoryMenu = new CategoryMenu(player, 20, textBubble, npc); // Initiate the category menu

        Button backLabel = new Button("Back");
        backLabel.getStyleClass().add("back-label");

        Button backButton = makeBackButton(onClose);
        onBackPressed(backButton); // If the button is pressed, the fight view will be shown


        BorderPane.setMargin(pokemonList, new Insets(0, 40, 0, 0));


        // Make the menu in the bag view
        HBox commandBox = new HBox();
        commandBox.getStyleClass().add("command-box");
        commandBox.getChildren().addAll(backButton, backLabel);
        commandBox.setPadding(new Insets(5));
        commandBox.setPrefHeight(60);
        commandBox.setPrefWidth(60);
        commandBox.setSpacing(10);
        commandBox.setAlignment(Pos.CENTER_RIGHT);

        VBox bottomBox = new VBox();
        bottomBox.setSpacing(10);
        bottomBox.getChildren().addAll(categoryMenu.getCategories().getBagBubble(), commandBox);

        // Placing elements in the bag view
        setLeft(pokemonList);
        setPadding(new Insets(30, 40, 30, 50));
        setCenter(categoryMenu);
        setBottom(bottomBox);

        setAlignment(backButton, Pos.CENTER_RIGHT);
        setAlignment(pokemonList, Pos.CENTER_LEFT);

        setFocusTraversable(true);

    }

    private Button makeBackButton(Runnable onClose) {
        Button backButton = new Button("B");
        backButton.getStyleClass().add("back-button");
        backButton.setOnAction(e -> {
            onClose.run();
        });
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
