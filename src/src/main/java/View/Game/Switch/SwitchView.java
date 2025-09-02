package View.Game.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.GameEvents.StartTurn;
import Model.Person.Action;
import Model.Person.NPC;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.Person.Player;
import View.Game.Battle.BattleView;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

public class SwitchView extends BorderPane {

    static Font font = Font.loadFont(Objects.requireNonNull(Bar.class.getResource("/font/pokemonFont.ttf")).toExternalForm(), 18);

    Player player;
    NPC npc;
    TextBubble textBubble;
    TextBubble switchBubble = new TextBubble();
    VBox root = new VBox();
    HBox hBox = new HBox();
    HBox hBox2 = new HBox();
    HBox hBox3 = new HBox();
    BattleExecutor executor = BattleExecutor.getInstance();
    boolean isTurnDisable = false;
    Button cancelButton;
    HBox wrapper;

    public SwitchView(Player player, NPC npc, TextBubble textBubble, Runnable onClose) {
        this.player = player;
        this.npc = npc;
        this.textBubble = textBubble;

        ObservableList<Node> components = getChildren();
        setButtons();

        cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add("cancel-button");

        cancelButton.setOnAction(e -> onClose.run());

        setCenter(root);
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setPadding(new Insets(20));

        wrapper = new HBox();
        wrapper.setSpacing(10);
        wrapper.setAlignment(Pos.CENTER_LEFT);
        wrapper.getChildren().addAll(switchBubble, cancelButton);
        HBox.setHgrow(switchBubble, Priority.ALWAYS);
        setBottom(wrapper);

        switchBubble.setVisible(true);
        switchBubble.showMessage("Choose a Pokémon.");
        switchBubble.setPrefHeight(100);
        switchBubble.prefWidthProperty().bind(wrapper.widthProperty().subtract(200));


        setPadding(new Insets(20));

        setFocusTraversable(true);
        Platform.runLater(() -> {
            Scene scene = SceneManager.getStage().getScene();
            if (scene != null) {
                scene.addEventFilter(KeyEvent.KEY_PRESSED, ev -> {
                    if (ev.getCode() == KeyCode.B) {
                        cancelButton.fire();
                    }
                });
            }
        });
    }

    private void setButtons() {
        root.getChildren().clear();

        HBox columns = new HBox();
        columns.setSpacing(32);
        columns.setAlignment(Pos.TOP_LEFT);

        VBox colLeft = new VBox();
        colLeft.setSpacing(24);
        colLeft.setPadding(new Insets(0,0,75,0));

        VBox colRight = new VBox();
        colRight.setSpacing(24);
        colRight.setAlignment(Pos.CENTER_RIGHT);

        List<Pokemon> team = player.getTeam();
        for (int i = 0; i < team.size(); i++) {
            Button b = createButtons(team.get(i));
            if (i < 3) colLeft.getChildren().add(b);
            else colRight.getChildren().add(b);
        }

        columns.getChildren().addAll(colLeft, colRight);
        HBox.setHgrow(colLeft, Priority.NEVER);
        HBox.setHgrow(colRight, Priority.NEVER);

        root.getChildren().add(columns);
    }

    private Button createButtons(Pokemon pokemon) {
        Button button = new Button();
        button.setGraphic(buildSwitchContent(pokemon));
        button.getStyleClass().add("switch-button");
        button.setMinSize(420, 160);
        button.setPrefSize(420, 160);
        button.setMaxSize(420, 160);
        HBox.setMargin(button, new Insets(6));
        if(pokemon.isKO()){
            button.setGraphic(buildSwitchContent(pokemon));
        }
        if(pokemon == player.getFrontPokemon()){
            button.setOnMousePressed(e -> {
                switchBubble.showMessage("This Pokémon is already fighting!");
                PauseTransition pause = new PauseTransition(new Duration(2));
                pause.play();
                pause.setOnFinished(event -> { switchBubble.setMessageVisible(false); });
                switchBubble.requestFocus();
            });
        }
        if(!(pokemon == player.getFrontPokemon())){
            button.setOnAction(e -> handleSwitch(pokemon));
        }
        return button;
    }

    private Node buildSwitchContent(Pokemon p) {

        int hp = p.getHP();
        int max = p.getMaxHP();
        int lvl = p.getLevel();
        Status st = p.getStatus();

        Label name = new Label(p.getName());
        Label text = new Label("HP");
        Label gender = new Label(Objects.equals(p.getGender(), "male") ? "♂" : "♀");
        Label hpText = new Label(hp + " / " + max);
        Label status = new Label(st.toString());
        Label level = new Label("Lv." + lvl);

        if(st == Status.normal) status.setText(null);

        gender.getStyleClass().add(Objects.equals(p.getGender(), "male") ? "male" : "female");

        ProgressBar bar = makeProgressBar(p);

        HBox top = new HBox(name, new HBox());
        top.setAlignment(Pos.CENTER_LEFT);
        top.setSpacing(8);
        HBox.setHgrow(top.getChildren().get(1), Priority.ALWAYS);
        top.getChildren().addAll(gender);

        HBox center = new HBox(text, bar, new HBox());
        center.setAlignment(Pos.CENTER_LEFT);
        center.setSpacing(10);
        HBox.setHgrow(center.getChildren().get(1), Priority.ALWAYS);

        HBox bottom = new HBox(level, status, hpText);
        hpText.setPadding(new Insets(0,0,0,250));
        bottom.setSpacing(10);

        VBox box = new VBox(top, center, bottom);
        box.setSpacing(12);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private ProgressBar makeProgressBar(Pokemon p){
        int hp = p.getHP();
        int max = p.getMaxHP();
        ProgressBar bar = new ProgressBar();
        double ratio = max <= 0 ? 0 : Math.max(0, Math.min(1.0, (double) hp / max));
        bar.setProgress(ratio);
        bar.setPrefWidth(360);
        bar.setPrefHeight(16);
        bar.getStyleClass().add("hp-bar");
        if (ratio > 0.5) {
            bar.setStyle("-fx-accent: #00C853;");
        } else if (ratio > 0.25) {
            bar.setStyle("-fx-accent: #F9A825;");
        } else {
            bar.setStyle("-fx-accent: #D32F2F;");
        }
        return bar;
    }

    protected void handleSwitch(Pokemon pokemon) {
        BattleView.refreshSprites();
        if (pokemon.isKO()) {
            switchBubble.setVisible(true);
            switchBubble.showMessage(pokemon.getName() + " is not it its best shape... You can not switch.");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(ev -> switchBubble.setVisible(false));
            delay.play();
            return;
        }

        player.setAction(Action.Switch);
        executor.addEvent(new StartTurn(npc, player, pokemon, executor));
        executor.executeEvents(null);
    }

    public void setTurnDisable(boolean disable){
        isTurnDisable = disable;
    }

    public HBox getWrapper() {
        return wrapper;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}
