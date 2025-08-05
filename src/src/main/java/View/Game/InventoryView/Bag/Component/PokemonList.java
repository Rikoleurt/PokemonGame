package View.Game.InventoryView.Bag.Component;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.util.Objects;

public class PokemonList extends VBox {
    Player player;

    public PokemonList(Player player) {
        this.player = player;
        ObservableList<Node> components = getChildren();
        Button firstPokemon = createPokemonButton(player.getFrontPokemon());
        Button secondPokemon = createPokemonButton(player.getTeam().get(1));
        Button thirdPokemon = createPokemonButton(player.getTeam().get(2));
        Button fourthPokemon = createPokemonButton(player.getTeam().get(3));
        Button fifthPokemon = createPokemonButton(player.getTeam().get(4));
        Button sixthPokemon = createPokemonButton(player.getTeam().getLast());

        components.addAll(firstPokemon,secondPokemon,thirdPokemon,fourthPokemon,fifthPokemon, sixthPokemon);
    }

    private Button createPokemonButton(Pokemon pokemon) {
        Button button = new Button();
        button.getStyleClass().add("pokemon-button");

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);

       // ImageView sprite = new ImageView((Element) new Image(getClass().getResourceAsStream("/images/pokemon/" + pokemon.getName().toLowerCase() + ".png")));
        if(pokemon != null) {
            VBox textBox = new VBox(2);
            Label name = new Label(pokemon.getName());
            Label hp = new Label(pokemon.getHP() + "/" + pokemon.getMaxHP());
            Label level = new Label("Lv. " + pokemon.getLevel());

            String genderSymbol = Objects.equals(pokemon.getGender(), "male") ? "♂" : "♀";
            Label gender = new Label(genderSymbol);
            gender.getStyleClass().add(Objects.equals(pokemon.getGender(), "male") ? "male" : "female");

            HBox topRow = new HBox(name, gender);
            topRow.setSpacing(5);
            textBox.getChildren().addAll(topRow, hp, level);

            hbox.getChildren().addAll(textBox);
            button.setGraphic(hbox);
        }
        return button;
    }
}
