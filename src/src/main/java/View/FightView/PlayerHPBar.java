package View.FightView;

import Pokemon.Pokemon;
import Pokemon.PokemonEnum.Status;
import View.FightView.Text.StatBubble;
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
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

import static View.FightView.FightView.player;

public class PlayerHPBar extends VBox {

    static Font font = Font.loadFont(PlayerHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon pokemon = player.getFrontPokemon();
    TextBubble textBubble;
    StatBubble statBubble;

    // Player variable
    String pokemonName = pokemon.getName();
    int pokemonHP = pokemon.getHP();
    int pokemonLvl = pokemon.getLevel();
    int pokemonMaxHP = pokemon.getMaxHP();
    Status pokemonStatus = pokemon.getStatus();
    String statusString = pokemon.getStatus().toString();


    // Player Labels
    Label pokemonNameLabel = new Label(pokemonName);
    Label HPLabel = new Label("HP :");
    Label HPsLabel = new Label(pokemonHP + "/" + pokemonMaxHP);
    Label LvlLabel = new Label("Lvl : " + pokemonLvl);
    Label StatusLabel = new Label(statusString);

    ProgressBar playerBar = new ProgressBar(1);
    ProgressBar expBar = new ProgressBar(1);

    protected PlayerHPBar(double spacing, TextBubble textBubble, StatBubble statBubble) {

        this.textBubble = textBubble;
        this.statBubble = statBubble;

        pokemonNameLabel.setFont(font);
        HPLabel.setFont(font);
        HPsLabel.setFont(font);
        LvlLabel.setFont(font);

        playerBar.setPrefSize(150,17);
        playerBar.setStyle("-fx-accent: #709f5e;");

        expBar.setPrefSize(182,15);
        expBar.setStyle("-fx-accent: #9642c1;");
        expBar.setProgress(pokemon.getExp());
        HBox statBox = new HBox(statBubble);
        HBox HBox1 = new HBox(pokemonNameLabel, LvlLabel);
        HBox HBox1b = new HBox(expBar);
        HBox HBox2 = new HBox(HPLabel, playerBar);
        HBox HBox3 = new HBox(HPsLabel);


        HBox1.setSpacing(spacing * 10);
        HBox1b.setSpacing(spacing);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_RIGHT);

        setPadding(new Insets(20));

        getChildren().addAll(statBubble, HBox1,HBox1b, HBox2, HBox3);
        setAlignment(Pos.BOTTOM_RIGHT);

        if(!pokemonStatus.equals(Status.normal)){
            getChildren().remove(HBox3);
            HBox statusHBox = new HBox(HPsLabel, StatusLabel);
            getChildren().add(statusHBox);
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
                textBubble.showMessage(pokemon.getName() + " is K.O");

            }
            if (onFinish != null) {
                onFinish.run();
            }
        });

        timeline.play();
    }

    void updateExpBars(int expGain, Runnable onFinish) {
        applyExpGain(expGain, onFinish);
    }

    private void applyExpGain(int remainingExp, Runnable onFinish) {

        int currentExp = pokemon.getExp();
        int currentMaxExp = pokemon.calculateMaxExp();
        int expToNextLevel = currentMaxExp - currentExp;
        int appliedExp = Math.min(remainingExp, expToNextLevel);
        int finalExp = currentExp + appliedExp;

        double startProgress = (double) currentExp / currentMaxExp;
        double endProgress = (double) finalExp / currentMaxExp;

        pokemon.setExp(finalExp);

        AtomicInteger displayedExp = new AtomicInteger(currentExp);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            double progress = expBar.getProgress();
            if (progress < endProgress) {
                double nextProgress = Math.min(progress + 0.01, endProgress);
                expBar.setProgress(nextProgress);

                int shownExp = (int) Math.round(nextProgress * currentMaxExp);
                displayedExp.set(shownExp);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount((int) ((endProgress - startProgress) / 0.01));

        timeline.setOnFinished(e -> {
            expBar.setProgress(endProgress);

            if (finalExp >= currentMaxExp) {
                pokemon.levelUp();
                pokemon.setExp(0);
                expBar.setProgress(0);

                int newMaxExp = pokemon.getMaxExp();

                LvlLabel.setText("Lvl : " + pokemon.getLevel());
                textBubble.showMessage(pokemon.getName() + " upgrades to level " + pokemon.getLevel() + " !");

                applyExpGain(remainingExp - appliedExp, onFinish);

                statBubble.showMessage("test");
            } else {
                if (onFinish != null) {
                    onFinish.run();
                }
            }
        });

        timeline.play();
    }

}
