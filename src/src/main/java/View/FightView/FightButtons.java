package View.FightView;

import Controller.Turns.Turn;
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

import static View.FightView.FightView.npc;
import static View.FightView.FightView.player;

public class FightButtons extends HBox {

    static Font font = Font.loadFont(FightButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon playerPokemon = player.getFrontPokemon();

    Move pAtk1 = playerPokemon.getAttacks().getFirst();
    Move pAtk2 = playerPokemon.getAttacks().get(1);
    Move pAtk3 = playerPokemon.getAttacks().get(2);
    //Move pAtk4 = playerPokemon.getAttacks().get(3);

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
        if(turn.isPlayerTurn()) {
            playerPokemon.attack(npcPokemon, move, terrain);
            opponentBar.updateHPBars(opponentBar.getHealth(), () -> {
                if (npcPokemon.isKO()) {
                    textBubble.showMessages(npcPokemon.getName() + " fainted!");
                    int totalExp = playerPokemon.calculateEXP(npcPokemon);
                    playerBar.updateExpBar(totalExp, playerBar.getLevel(), () -> textBubble.showMessages(playerPokemon.getName() + " earned " + totalExp));
                }
                turn.toggleTurn();
            });
        }

        if(!turn.isPlayerTurn()){
            Move npcMove = npcPokemon.chooseMove();
            npcPokemon.attack(playerPokemon, npcMove, terrain);
            playerBar.updateHPBars(playerBar.getHealth(), () -> {
                if(playerPokemon.isKO()){
                    textBubble.showMessages(
                            playerPokemon.getName() + " fainted!",
                            "fight lost");
                }
                turn.toggleTurn();
            });
        }
        HBox1.setVisible(true);
        HBox2.setVisible(true);
        requestFocus();
    }

    private String getAttackName(int index){
        if(index < playerPokemon.getAttacks().size()){
            return playerPokemon.getAttacks().get(index).getName();
        } else{
            return " - ";
        }
    }
}
