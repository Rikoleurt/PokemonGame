package View.FightView;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.DamageEvent;
import Controller.Fight.Battle.Events.MessageEvent;
import Controller.Fight.Battle.Events.UpdateBarEvent;
import Controller.Fight.Turns.Turn;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Move;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Meteo;

import View.FightView.InfoBars.Bar;
import View.FightView.Text.TextBubble;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.LinkedList;
import java.util.Queue;

import static View.FightView.FightView.npc;
import static View.FightView.FightView.player;

public class FightButtons extends HBox {

    static Font font = Font.loadFont(FightButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon playerPokemon = player.getFrontPokemon();

    Move pAtk1 = getMove(0);
    Move pAtk2 = getMove(1);
    Move pAtk3 = getMove(2);
    Move pAtk4 = getMove(3);

    Pokemon npcPokemon = npc.getFrontPokemon();

    Terrain terrain = new Terrain(player.getTeam(), npc.getTeam(), Debris.normal, Meteo.normal);

    // Initial buttons
    Button runButton = createButton("Run");
    Button fightButton = createButton("Fight");
    Button bagButton = createButton("Bag");
    Button pokemonButton = createButton("Pokemon");

    // Attack buttons
    Button atk1Button = createButton(getAttackName(0));
    Button atk2Button = createButton(getAttackName(1));
    Button atk3Button = createButton(getAttackName(2));
    Button atk4Button = createButton(getAttackName(3));

    private Bar opponentBar;
    private final Bar playerBar;
    private final TextBubble textBubble;

    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    HBox HBox1 = new HBox(fightButton, bagButton);
    HBox HBox2 = new HBox(runButton, pokemonButton);

    Turn turn = new Turn(playerPokemon, npcPokemon);
    Queue<BattleEvent> queue = new LinkedList<>();
    BattleExecutor executor = BattleExecutor.getInstance();

    public FightButtons(TextBubble textBubble, Bar opponentBar, Bar playerBar) {
        this.textBubble = textBubble;
        this.opponentBar = opponentBar;
        this.playerBar = playerBar;

        ObservableList<Node> components = this.getChildren();

        vBox.getChildren().addAll(HBox1, HBox2);
        vBox.setAlignment(Pos.CENTER);
        vBox2.getChildren().add(textBubble);

        components.addAll(vBox2, vBox);

        textBubble.showMessage("What will " + playerPokemon.getName() + " do?");

        vBox2.setVisible(true);
        HBox1.setSpacing(5);
        HBox2.setSpacing(5);
        vBox.setSpacing(5);

        setPadding(new Insets(20));
        setAlignment(Pos.BOTTOM_RIGHT);
        // Buttons action

        fightButton.setOnAction(e -> {
           components.clear();

           HBox1.getChildren().clear();
           HBox2.getChildren().clear();
           vBox.getChildren().clear();

           HBox1.getChildren().addAll(atk1Button, atk2Button);
           HBox2.getChildren().addAll(atk3Button, atk4Button);

           vBox.getChildren().addAll(HBox1, HBox2);

           components.addAll(vBox2, vBox);

        });

        bagButton.setOnAction(e -> {
        });

        pokemonButton.setOnAction(e -> {
        });

        runButton.setOnAction(e -> {
        });

        atkButtonAction();
    }



    private Button createButton(String text) {
        Button button = new Button(text);
        button.setFont(font);
        button.setPrefSize(175, 75);
        button.setStyle("-fx-background-color: white; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-background-radius: 0px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: black; ");
        if(text == null){
            button.setText(" - ");
            button.setFont(font);
            button.setPrefSize(175, 75);
            button.setStyle("-fx-background-color: white; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 5px; " +
                    "-fx-background-radius: 0px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-text-fill: black; ");
        }
        return button;
    }

    private void atkButtonAction() {
        atk1Button.setOnAction(e -> {
            if(pAtk1 != null) {
                AtkButtonAction(pAtk1, terrain);
            }
        });

        atk2Button.setOnAction(e -> {
            if(pAtk2 != null) {
                AtkButtonAction(pAtk2, terrain);
            }

        });

        atk3Button.setOnAction(e -> {
            if(pAtk3 != null) {
                AtkButtonAction(pAtk3, terrain);
            }


        });

        atk4Button.setOnAction(e -> {
            //if(pAtk4 != null) {

            //}
        });
    }

    private void AtkButtonAction(Move move, Terrain terrain) {
        HBox1.setVisible(false);
        HBox2.setVisible(false);
        boolean isPlayerTurnFinished = false;
        boolean isNpcTurnFinished = false;
        if(turn.isPlayerTurn()) {
            executor.addEvent(new DamageEvent(playerPokemon, npcPokemon, move, terrain, textBubble));
            executor.addEvent(new UpdateBarEvent(opponentBar, opponentBar.getHealth()));
            turn.toggleTurn();
            isPlayerTurnFinished = true;
        }
        if(!turn.isPlayerTurn()) {
            Move enemyMove = npcPokemon.chooseMove();
            executor.addEvent(new DamageEvent(npcPokemon, playerPokemon, enemyMove, terrain, textBubble));
            executor.addEvent(new UpdateBarEvent(playerBar, playerBar.getHealth()));
            turn.toggleTurn();
            isNpcTurnFinished = true;
        }
        if(isNpcTurnFinished && isPlayerTurnFinished) {
            executor.executeNext();
            textBubble.showMessage("What will " + playerPokemon.getName() + " do ?");
        }

        HBox1.setVisible(true);
        HBox2.setVisible(true);
        requestFocus();
    }

    private String getAttackName(int index){
        if(index < playerPokemon.getAttacks().size()){
            return playerPokemon.getAttacks().get(index).getName();
        } else {
            return " - ";
        }
    }

    private Move getMove(int index){
        if(index < playerPokemon.getAttacks().size()){
            return playerPokemon.getAttacks().get(index);
        } else {
            return null;
        }
    }
}
