package View.Game.Battle.InfoBars;

import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.Text.TextBubble;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static View.Game.Battle.BattleView.player;

public class Bar extends VBox {

    static Font font = Font.loadFont(Objects.requireNonNull(Bar.class.getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);

    TextBubble bubble;
    public Pokemon pokemon; // /!\
    ProgressBar HPBar = new ProgressBar(1);
    ProgressBar expBar = new ProgressBar(1);

    Label name = new Label();
    Label HP = new Label();
    Label level = new Label();
    Label health = new Label();
    Label status = new Label();

    Bar(double spacing, Pokemon pokemon) {

        this.pokemon = pokemon;
        this.setSpacing(spacing);

        String pName = pokemon.getName();
        int pLevel = pokemon.getLevel();
        int pHP = pokemon.getHP();
        int pMaxHP = pokemon.getMaxHP();
        String pStatus = pokemon.getStatus().toString();

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

        setPadding(new Insets(15));
        Image image1 = new Image(Objects.requireNonNull(Bar.class.getResource("/images/pokeball.png")).toExternalForm());
        Image image2 = new Image(Objects.requireNonNull(Bar.class.getResource("/images/pokeballKO.png")).toExternalForm());


        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitWidth(20);
        imageView2.setPreserveRatio(true);


        // Opponent bar objects
        if(this instanceof OpponentBar) {
            this.getChildren().addAll(HBox1, HBox2, HBox3);
        }

        // Player bar objects
        if(this instanceof PlayerBar) {
            int size = player.getTeam().size();
            HBox pokeHBox = new HBox();
            for(int i = 0; i < size; i++) {
                if(player.getTeam().get(i).isKO()) {
                    Image image = new Image(Objects.requireNonNull(Bar.class.getResource("/images/pokeballKO.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(20);
                    imageView.setPreserveRatio(true);
                    pokeHBox.getChildren().add(imageView);
                } else {
                    Image image = new Image(Objects.requireNonNull(Bar.class.getResource("/images/pokeball.png")).toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(20);
                    imageView.setPreserveRatio(true);
                    pokeHBox.getChildren().add(imageView);
                }
            }
            this.getChildren().addAll(pokeHBox, HBox1, expBarBox, HBox2, HBox3);
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

    public void setPokemon(Pokemon p) {
        this.pokemon = p;
        String pName = p.getName();
        int pLevel = p.getLevel();
        int pHP = p.getHP();
        int pMaxHP = p.getMaxHP();
        String pStatus = p.getStatus().toString();

        name.setText(pName);
        level.setText("Lvl : " + pLevel);
        status.setText(pStatus);
        health.setText(pHP + "/" + pMaxHP);

        double hpProgress = pMaxHP == 0 ? 0 : Math.max(0.0, Math.min(1.0, (double) pHP / pMaxHP));
        HPBar.setProgress(hpProgress);
        ApplyColor(new java.util.concurrent.atomic.AtomicInteger(pHP), pMaxHP, HPBar);

        int maxExp = Math.max(1, p.calculateMaxExp());
        double expProgress = Math.max(0.0, Math.min(1.0, (double) p.getExp() / maxExp));
        expBar.setProgress(expProgress);
    }

    public void updateHPBars(Runnable onFinish) {
        AtomicInteger currentHP = new AtomicInteger(Math.max(0, pokemon.getHP()));
        int maxHP = pokemon.getMaxHP();

        double startProgress = HPBar.getProgress();
        double endProgress = Math.max(0.0, Math.min(1.0, (double) currentHP.get() / maxHP));
        double step = 0.02;

        int steps = (int) Math.ceil(Math.abs(endProgress - startProgress) / step);
        if (steps <= 0) {
            health.setText(currentHP.get() + "/" + maxHP);
            ApplyColor(currentHP, maxHP, HPBar);
            if (onFinish != null) onFinish.run();
            return;
        }

        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(50),
                e -> {
                    double progress = HPBar.getProgress();
                    double newProgress = progress < endProgress
                            ? Math.min(progress + step, endProgress)
                            : Math.max(progress - step, endProgress);
                    HPBar.setProgress(newProgress);
                    int displayedHP = (int) Math.round(newProgress * maxHP);
                    currentHP.set(Math.max(0, displayedHP));
                    health.setText(currentHP.get() + "/" + maxHP);
                    ApplyColor(currentHP, maxHP, HPBar);
                }
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(steps);
        timeline.setOnFinished(e -> { if (onFinish != null) onFinish.run(); });
        timeline.play();
    }


    private static void ApplyColor(AtomicInteger currentHP, int maxHP, ProgressBar playerBar) {
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
        int currentExp = pokemon.getExp();
        int currentMaxExp = pokemon.calculateMaxExp();

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
                levelLabel.setText("Lvl : " + pokemon.getLevel());

                //textBubble.showMessage(pName + " levels up !");

                applyExpGain(remainingExp - appliedExp, levelLabel, onFinish);
            } else {
                pokemon.setExp(finalExp);
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

