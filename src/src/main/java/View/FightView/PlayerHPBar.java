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

import java.util.concurrent.atomic.AtomicInteger;

import static View.FightView.FightView.player;

public class PlayerHPBar extends VBox {

    static Font font = Font.loadFont(PlayerHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon pokemon = player.getFrontPokemon();
    TextBubble bubble;

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

    ProgressBar playerBar = new ProgressBar(1);

    protected PlayerHPBar(double spacing, TextBubble bubble){

        this.bubble = bubble;

        pokemonNameLabel.setFont(font);
        HPLabel.setFont(font);
        HPsLabel.setFont(font);
        LvlLabel.setFont(font);

        playerBar.setPrefSize(150,20);
        playerBar.setStyle("-fx-accent: #709f5e;");

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
    }

    void updateHPBars(Runnable onFinish) {
        AtomicInteger currentHP = new AtomicInteger(Math.max(0, pokemon.getHP()));
        int maxHP = pokemon.getMaxHP();

        double startProgress = playerBar.getProgress();
        double endProgress = Math.max(0, (double) currentHP.get() / maxHP);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(50),
                e -> {
                    double progress = playerBar.getProgress();
                    if (progress > endProgress) {
                        playerBar.setProgress(Math.max(progress - 0.02, endProgress));

                        int displayedHP = (int) Math.round(playerBar.getProgress() * maxHP);
                        currentHP.set(Math.max(0, displayedHP));

                        HPsLabel.setText(currentHP.get() + "/" + maxHP);

                        if (currentHP.get() > maxHP / 2) {
                            playerBar.setStyle("-fx-accent: #709f5e;");
                        } else if (currentHP.get() > maxHP / 4) {
                            playerBar.setStyle("-fx-accent: #f68524;");
                        } else {
                            playerBar.setStyle("-fx-accent: #d81e1e;");
                        }
                    }
                });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount((int) ((startProgress - endProgress) / 0.02));

        timeline.setOnFinished(e -> {
            playerBar.setProgress(endProgress);
            HPsLabel.setText(currentHP.get() + "/" + maxHP);

            if (currentHP.get() <= 0) {
                bubble.showMessage(pokemon.getName() + " is K.O");
                System.out.println("Bubble : " + bubble.getParent());
            }
            if (onFinish != null) {
                onFinish.run();
            }
        });

        timeline.play();
    }
}
