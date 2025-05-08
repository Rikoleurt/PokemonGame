package View.FightView;

import Pokemon.PokemonEnum.Status;
import View.FightView.Text.TextBubble;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import Pokemon.Pokemon;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

import static View.FightView.FightView.npc;

public class EnemyHPBar extends VBox {
    static Font font = Font.loadFont(EnemyHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon pokemon = npc.getFrontPokemon();
    TextBubble bubble;
    // NPC Variable
    int pokemonHP = pokemon.getHP();
    int pokemonLvl = pokemon.getLevel();
    int pokemonMaxHP = pokemon.getMaxHP();

    // NPC Label
    Label name = new Label(pokemon.getName());
    Label HP = new Label("HP :");
    Label level = new Label("Lvl : " + pokemonLvl);
    Label HPs = new Label(pokemonHP + "/" + pokemonMaxHP);
    Label status = new Label(pokemon.getStatus().toString());

    ProgressBar npcBar = new ProgressBar(1);

    public EnemyHPBar(double spacing, TextBubble bubble) {

        this.bubble = bubble;

        name.setFont(font);
        HP.setFont(font);
        level.setFont(font);
        HPs.setFont(font);

        npcBar.setPrefSize(150,17);
        npcBar.setStyle("-fx-accent: #709f5e;");

        HBox HBox1 = new HBox(name, level);
        HBox HBox2 = new HBox(HP, npcBar);
        HBox HBox3 = new HBox(HPs);

        HBox1.setSpacing(spacing * 10);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_LEFT);

        setPadding(new Insets(20));

        this.getChildren().addAll(HBox1, HBox2, HBox3);

        if(!pokemon.getStatus().equals(Status.normal)){
            this.getChildren().remove(HBox3);
            HBox statusHBox = new HBox(HPs, status);
            this.getChildren().add(statusHBox);
            statusHBox.setSpacing(spacing * 10);
        }
    }

    void updateHPBars(Runnable onFinish) {
        AtomicInteger currentHP = new AtomicInteger(Math.max(0, pokemon.getHP()));
        int maxHP = pokemon.getMaxHP();

        double startProgress = npcBar.getProgress();
        double endProgress = Math.max(0, (double) currentHP.get() / maxHP);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(50),
                e -> {
                    double progress = npcBar.getProgress();
                    if (progress > endProgress) {
                        double newProgress = Math.max(progress - 0.02, endProgress);
                        npcBar.setProgress(newProgress);

                        int displayedHP = (int) Math.round(newProgress * maxHP);
                        currentHP.set(Math.max(0, displayedHP));
                        HPs.setText(currentHP.get() + "/" + maxHP);

                        PlayerBars.ApplyColor(onFinish, currentHP, maxHP, endProgress, newProgress, npcBar);
                    }
                });

        int steps = (int) Math.ceil((startProgress - endProgress) / 0.02);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(steps);
        timeline.play();
    }

}
