package View.FightView;

import Pokemon.Pokemon;
import Pokemon.PokemonEnum.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static View.FightView.FightView.player;

public class PlayerHPBar extends VBox {

    static Font font = Font.loadFont(PlayerHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon pokemon = player.getFrontPokemon();

    // Player variable
    int pokemonHP = pokemon.getHP();
    int pokemonLvl = pokemon.getLevel();
    int pokemonMaxHP = pokemon.getMaxHP();
    Status status = pokemon.getStatus();

    // Player Labels
    Label pokemonNameLabel = new Label(pokemon.getName());
    Label HPLabel = new Label("HP :");
    Label HPsLabel = new Label(pokemon.getHP() + "/" + pokemon.getMaxHP());
    Label LvlLabel = new Label("Lvl : " + pokemon.getLevel());
    Label StatusLabel = new Label(status.toString());

    ProgressBar playerBar = new ProgressBar(10);

    // Text Label

    Label battleMessage = new Label("");


    protected PlayerHPBar(double spacing){

        pokemonNameLabel.setFont(font);
        HPLabel.setFont(font);
        HPsLabel.setFont(font);
        LvlLabel.setFont(font);

        playerBar.setPrefSize(150,20);
        playerBar.setStyle("-fx-accent: #709f5e;");

        pokemon.setHP(10);
        updateHPBars();

        HBox HBox1 = new HBox(pokemonNameLabel, LvlLabel);
        HBox HBox2 = new HBox(HPLabel, playerBar);
        HBox HBox3 = new HBox(HPsLabel);

        HBox1.setSpacing(spacing * 10);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_RIGHT);

        setPadding(new Insets(20));

        this.getChildren().addAll(HBox1, HBox2, HBox3);
        this.setAlignment(Pos.BOTTOM_RIGHT);

        if(!pokemon.getStatus().equals(Status.normal)){
            this.getChildren().remove(HBox3);
            HBox statusHBox = new HBox(HPsLabel, StatusLabel);
            this.getChildren().add(statusHBox);
            statusHBox.setSpacing(spacing * 10);
        }

        // Bubble
        battleMessage.setFont(font);
        battleMessage.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-text-fill: white; -fx-padding: 5px; -fx-border-radius: 5px;");
        battleMessage.setVisible(false);

    }

    private void updateHPBars() {
        int currentHP = pokemon.getHP();
        playerBar.setProgress((double) currentHP / pokemon.getMaxHP());
        HPsLabel.setText(currentHP + "/" + pokemon.getMaxHP());

        if(currentHP > pokemon.getMaxHP() / 2){
            playerBar.setStyle("-fx-accent: #709f5e;");
        } else if(currentHP > pokemon.getMaxHP() / 4){
            playerBar.setStyle("-fx-accent: #f68524;");
        } else {
            playerBar.setStyle("-fx-accent: #d81e1e;");
        }
    }

    // Not the right place
    void showBattleMessage(String message) {
        battleMessage.setText(message);
        battleMessage.setVisible(true);

        // Cache la bulle après 2 secondes
        new Timeline(new KeyFrame(Duration.seconds(2), e -> battleMessage.setVisible(false))).play();
    }

}
