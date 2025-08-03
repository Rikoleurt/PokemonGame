package View.Game.FightView.InfoBars;

import Model.Pokemon.Pokemon;
import View.Game.FightView.Text.TextBubble;
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

public class Bar extends VBox {
    static Font font = Font.loadFont(Bar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    TextBubble bubble;
    public Pokemon p; // Careful with "public"
    ProgressBar HPBar = new ProgressBar(1);
    ProgressBar expBar = new ProgressBar(1);

    Label name = new Label();
    Label HP = new Label();
    Label level = new Label();
    Label health = new Label();
    Label status = new Label();

    Bar(double spacing, Pokemon p) {

        this.p = p;
        this.setSpacing(spacing);

        String pName = p.getName();
        int pLevel = p.getLevel();
        int pHP = p.getHP();
        int pMaxHP = p.getMaxHP();
        String pStatus = p.getStatus().toString();

        name.setText(pName);
        HP.setText("HP : ");
        level.setText("Lvl : " + pLevel);
        health.setText(pHP + "/" + pMaxHP);
        status.setText(pStatus);

        name.setFont(font);
        HP.setFont(font);
        level.setFont(font);
        health.setFont(font);
        status.setFont(font);

        HPBar.setPrefSize(150,17);
        HPBar.setStyle("-fx-accent: #709f5e;");

        expBar.setPrefSize(175, 12);
        expBar.setStyle("-fx-accent: #994ee4;");

        HBox HBox1 = new HBox(name, level);
        HBox expBarBox = new HBox(expBar, HPBar);
        HBox HBox2 = new HBox(HP, HPBar);
        HBox HBox3 = new HBox(health);

        HBox1.setSpacing(spacing * 10);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_LEFT);

        setPadding(new Insets(20));
        // Opponent bar objects
        if(this instanceof OpponentBar) {
            this.getChildren().addAll(HBox1, HBox2, HBox3);
        }

        // Player bar objects
        if(this instanceof PlayerBar) {
            this.getChildren().addAll(HBox1,expBarBox, HBox2, HBox3);
        }
    }

    public Label getHealth() {
        return health;
    }

    public Label getStatus() {
        return status;
    }
    public Label getName() {
        return name;
    }
    public Label getHP() {
        return HP;
    }
    public Label getLevel() {
        return level;
    }

    public void updateHPBars(Label healthLabel, Runnable onFinish) {
        AtomicInteger currentHP = new AtomicInteger(Math.max(0, p.getHP()));
        int maxHP = p.getMaxHP();

        double startProgress = HPBar.getProgress();
        double endProgress = Math.max(0, (double) currentHP.get() / maxHP);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(50),
                e -> {
                    double progress = HPBar.getProgress();
                    if (progress > endProgress) {
                        double newProgress = Math.max(progress - 0.02, endProgress);
                        HPBar.setProgress(newProgress);

                        int displayedHP = (int) Math.round(newProgress * maxHP);
                        currentHP.set(Math.max(0, displayedHP));
                        healthLabel.setText(currentHP.get() + "/" + maxHP);

                        ApplyColor(onFinish, currentHP, maxHP, endProgress, newProgress, HPBar);
                    }
                });

        int steps = (int) Math.ceil((startProgress - endProgress) / 0.02);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(steps);
        timeline.play();
        timeline.setOnFinished(e -> {
            if (onFinish != null) {
                onFinish.run();
            }
        });
    }

    private static void ApplyColor(Runnable onFinish, AtomicInteger currentHP, int maxHP, double endProgress, double newProgress, ProgressBar playerBar) {
        if (currentHP.get() > maxHP / 2) {
            playerBar.setStyle("-fx-accent: #709f5e;");
        } else if (currentHP.get() > maxHP / 4) {
            playerBar.setStyle("-fx-accent: #f68524;");
        } else {
            playerBar.setStyle("-fx-accent: #d81e1e;");
        }
    }

    public void updateExpBar(int expGain, Label levelLabel, Runnable onFinish) {
        applyExpGain(expGain, levelLabel, onFinish);
    }

    private void applyExpGain(int remainingExp, Label levelLabel,  Runnable onFinish) {
        int currentExp = p.getExp();
        int currentMaxExp = p.calculateMaxExp();

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

                int shownExp = (int) Math.round(nextProgress * p.calculateMaxExp());
                displayedExp.set(shownExp);
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount((int) ((endProgress - startProgress) / 0.01));

        timeline.setOnFinished(e -> {
            expBar.setProgress(endProgress);

            if (finalExp >= currentMaxExp) {
                p.setExp(finalExp);

                p.levelUp();
                p.setExp(0);

                expBar.setProgress(0);
                levelLabel.setText("Lvl : " + p.getLevel());

                //textBubble.showMessage(pName + " levels up !");

                applyExpGain(remainingExp - appliedExp, levelLabel, onFinish);
            } else {
                p.setExp(finalExp);
                if (onFinish != null) {
//                    textBubble.showMessages(pokemon.getName() + " earned " + finalExp + " exp");
//                    statBubble.showMessage(statMessage(pokemon));
                    onFinish.run();
                }
            }
        });

        timeline.play();
    }
}

