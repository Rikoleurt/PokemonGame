package View.FightView;

import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
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

public class PlayerBars extends VBox {

    static Font font = Font.loadFont(PlayerBars.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

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

    protected PlayerBars(double spacing, TextBubble textBubble, StatBubble statBubble) {

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
                        double newProgress = Math.max(progress - 0.02, endProgress);
                        playerBar.setProgress(newProgress);

                        int displayedHP = (int) Math.round(newProgress * maxHP);
                        currentHP.set(Math.max(0, displayedHP));
                        HPsLabel.setText(currentHP.get() + "/" + maxHP);

                        ApplyColor(onFinish, currentHP, maxHP, endProgress, newProgress, playerBar);
                    }
                });

        int steps = (int) Math.ceil((startProgress - endProgress) / 0.02);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(steps);
        timeline.play();
    }

    static void ApplyColor(Runnable onFinish, AtomicInteger currentHP, int maxHP, double endProgress, double newProgress, ProgressBar playerBar) {
        if (currentHP.get() > maxHP / 2) {
            playerBar.setStyle("-fx-accent: #709f5e;");
        } else if (currentHP.get() > maxHP / 4) {
            playerBar.setStyle("-fx-accent: #f68524;");
        } else {
            playerBar.setStyle("-fx-accent: #d81e1e;");
        }

        if (Math.abs(newProgress - endProgress) < 0.01 && onFinish != null) {
            onFinish.run();
        }
    }

    void updateExpBar(int expGain) {
        applyExpGain(expGain, null);
    }

    private void applyExpGain(int remainingExp, Runnable onFinish) {
        int currentExp = pokemon.getExp();
        int currentMaxExp = pokemon.calculateMaxExp();
        System.out.println(currentMaxExp);
        int expToNextLevel = currentMaxExp - currentExp;
        int appliedExp = Math.min(remainingExp, expToNextLevel);
        int finalExp = currentExp + appliedExp;

        double startProgress = (double) currentExp / currentMaxExp;
        double endProgress = (double) finalExp / currentMaxExp;

        AtomicInteger displayedExp = new AtomicInteger(currentExp);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            double progress = expBar.getProgress();
            if (progress < endProgress) {
                double nextProgress = Math.min(progress + 0.01, endProgress);
                expBar.setProgress(nextProgress);

                int shownExp = (int) Math.round(nextProgress * pokemon.calculateMaxExp());
                displayedExp.set(shownExp);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount((int) ((endProgress - startProgress) / 0.01));

        timeline.setOnFinished(e -> {
            expBar.setProgress(endProgress);

            if (finalExp >= currentMaxExp) {
                pokemon.setExp(finalExp);

                pokemon.levelUp();
                pokemon.setExp(0);

                expBar.setProgress(0);
                LvlLabel.setText("Lvl : " + pokemon.getLevel());

                textBubble.showMessage(pokemon.getName() + " levels up !");
                statBubble.showMessage(statMessage(pokemon));

                applyExpGain(remainingExp - appliedExp, onFinish);
            } else {
                pokemon.setExp(finalExp);
                if (onFinish != null) {
                    onFinish.run();
                }
            }
        });

        timeline.play();
    }

    private String statMessage(Pokemon p) {
        return String.format(
                "%s levels up %d !\nHP: %d/%d\nAtk: %d\nDef: %d\nAtkSpe: %d\nDefSpe: %d\nSpeed: %d",
                p.getName(), p.getLevel(), p.getHP(), p.getMaxHP(),
                p.getAtk(), p.getDef(), p.getAtkSpe(), p.getDefSpe(), p.getSpeed()
        );
    }

}
