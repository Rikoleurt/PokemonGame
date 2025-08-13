package View.Game.InventoryView.Bag;

import Model.Person.NPC;
import Model.Person.Player;
import View.Game.FightView.Text.TextBubble;
import View.Game.InventoryView.Bag.Component.CategoryMenu;
import View.Game.InventoryView.Bag.Component.PokemonList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BagView extends BorderPane {
    Player player;
    NPC npc;
    PokemonList pokemonList;
    CategoryMenu categoryMenu;
    TextBubble textBubble;

    public BagView(Player player, Runnable onClose, TextBubble textBubble, NPC npc) {
        this.player = player;
        this.textBubble = textBubble;
        pokemonList = new PokemonList(player, 10);
        categoryMenu = new CategoryMenu(player,20, textBubble, npc);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onClose.run());

        HBox wrapper = new HBox();
        wrapper.setAlignment(Pos.CENTER_RIGHT);
        wrapper.getChildren().add(categoryMenu);
        wrapper.setPadding(new Insets(10));

        setLeft(pokemonList);
        setPadding(new Insets(30, 40, 30, 50));
        setBottom(backButton);
        setTop(wrapper);

        setAlignment(backButton, Pos.CENTER_RIGHT);
        setAlignment(pokemonList, Pos.CENTER_LEFT);
    }
}
