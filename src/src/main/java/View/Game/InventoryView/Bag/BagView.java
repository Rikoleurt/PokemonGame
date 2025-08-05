package View.Game.InventoryView.Bag;

import Model.Person.Player;
import View.Game.InventoryView.Bag.Component.PokemonList;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class BagView extends BorderPane {
    Player player;
    PokemonList pokemonList;
    public BagView(Player player, Runnable onClose) {
        this.player = player;
        pokemonList = new PokemonList(player);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> { onClose.run(); });

        setLeft(pokemonList);
        setTop(backButton);

        setAlignment(backButton,Pos.CENTER_RIGHT);
        setAlignment(pokemonList,Pos.CENTER_LEFT);

    }
}
