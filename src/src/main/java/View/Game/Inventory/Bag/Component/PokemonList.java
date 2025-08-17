package View.Game.Inventory.Bag.Component;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class PokemonList extends VBox {
    Player player;

    public PokemonList(Player player, int spacing) {
        this.player = player;
        setSpacing(spacing);
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
        HBox nameRow = new HBox(5);

        Label name = new Label(p.getName());
        Label gender = new Label(Objects.equals(p.getGender(), "male") ? "♂" : "♀");
        gender.getStyleClass().add(Objects.equals(p.getGender(), "male") ? "male" : "female");
        nameRow.getChildren().addAll(name, gender);

        ProgressBar hpBar = new ProgressBar((double) p.getHP() / p.getMaxHP());
        hpBar.setPrefWidth(120);
        hpBar.setPrefHeight(15);
        hpBar.getStyleClass().add("hp-bar");

        Label hpText = new Label(p.getHP() + "/" + p.getMaxHP());
        Label level = new Label("Lv. " + p.getLevel());

        content.getChildren().addAll(nameRow, hpBar, hpText, level);

        button.setGraphic(content);
        button.setPrefSize(200, 110);



        if(p.isKO()){
            button.setStyle("-fx-background-color: linear-gradient(to bottom, #ef900e, #ac740f);" +
                            "-fx-border-color: black;" +
                            "-fx-border-radius: 14px;" +
                            "-fx-border-width: 2px;");
        }
        return button;
    }
}
