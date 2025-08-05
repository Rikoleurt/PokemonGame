package View.Game.InventoryView.Bag.Component;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
//        Button fourthPokemon = createPokemonButton(player.getTeam().get(3));
//        Button fifthPokemon = createPokemonButton(player.getTeam().get(4));
//        Button sixthPokemon = createPokemonButton(player.getTeam().getLast());

        components.addAll(firstPokemon,secondPokemon,thirdPokemon);
    }

    private Button createPokemonButton(Pokemon p) {
        Button button = new Button();
        button.getStyleClass().add("pokemon-button");

        VBox content = new VBox(4);
        content.setAlignment(Pos.CENTER_LEFT);

        // Nom + genre
        HBox nameRow = new HBox(5);
        Label name = new Label(p.getName());
        Label gender = new Label(Objects.equals(p.getGender(), "male") ? "♂" : "♀");
        gender.getStyleClass().add(Objects.equals(p.getGender(), "male") ? "male" : "female");
        nameRow.getChildren().addAll(name, gender);

        // HP ProgressBar
        ProgressBar hpBar = new ProgressBar((double) p.getHP() / p.getMaxHP());
        hpBar.setPrefWidth(120);
        hpBar.getStyleClass().add("hp-bar");

        // HP text et niveau
        Label hpText = new Label(p.getHP() + "/" + p.getMaxHP());
        Label level = new Label("Lv. " + p.getLevel());

        content.getChildren().addAll(nameRow, hpBar, hpText, level);

        button.setGraphic(content);
        button.setPrefSize(160, 80);
        button.setMinSize(160, 80);
        button.setMaxSize(160, 80);

        return button;
    }

}
