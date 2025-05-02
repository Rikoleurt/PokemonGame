package View.FightView;

import Pokemon.Pokemon;
import Pokemon.Move;
import Pokemon.PokemonEnum.Status;
import Pokemon.Terrain;
import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;

import View.FightView.Text.StatBubble;
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
    Button atk1Button = createButton(playerPokemon.getAttacks().getFirst().getName());
    Button atk2Button = createButton(playerPokemon.getAttacks().get(1).getName());
    Button atk3Button = createButton(playerPokemon.getAttacks().get(2).getName());
    //Button atk4Button = createButton(playerPokemon.getAttacks().get(3).getName());
    Button atk4Button = createButton(" - ");

    private final EnemyHPBar enemyHPBar;
    private final PlayerHPBar playerHPBar;
    private TextBubble textBubble;

    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    HBox HBox1 = new HBox(fightButton, bagButton);
    HBox HBox2 = new HBox(runButton, pokemonButton);

    public FightButtons(EnemyHPBar enemyHPBar, PlayerHPBar playerHPBar, TextBubble textBubble) {
        this.enemyHPBar = enemyHPBar;
        this.playerHPBar = playerHPBar;
        this.textBubble = textBubble;

        ObservableList<Node> components = this.getChildren();

        vBox.getChildren().addAll(HBox1, HBox2);
        components.add(vBox);
        vBox.setAlignment(Pos.CENTER);

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
           vBox.getChildren().addAll(textBubble, HBox1, HBox2);
           vBox2.getChildren().add(textBubble);
           vBox2.setVisible(false);
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

    private boolean compareSpeed(){
        return playerPokemon.getSpeed() > npcPokemon.getSpeed();
    }

    private void AtkButtonAction(Move move, Terrain terrain) {
        boolean priority = compareSpeed();
        if(!playerPokemon.getStatus().equals(Status.KO) || !npcPokemon.getStatus().equals(Status.KO)) {
            if (priority) {
                vBox2.setVisible(true);
                HBox1.setVisible(false);
                HBox2.setVisible(false);
                playerPokemon.attack(npcPokemon, move, terrain);
                enemyHPBar.updateHPBars(() -> {
                    Move enemyMove = npcPokemon.chooseMove();
                    npcPokemon.attack(playerPokemon, enemyMove, terrain);

                    textBubble.showMessage(npcPokemon.getName() + " uses " + enemyMove.getName());

                    if (npcPokemon.isKO()) {

                        textBubble.showMessage(npcPokemon.getName() + " is K.O ");

                        int totalExp = playerPokemon.calculateEXP(npcPokemon);
                        playerHPBar.updateExpBars(totalExp, null);
                        textBubble.showMessage(playerPokemon.getName() + " earned " + totalExp + " exp");
                    }
                });

                textBubble.showMessage(playerPokemon.getName() + " uses " + move.getName());

                HBox1.setVisible(true);
                HBox2.setVisible(true);

                this.requestFocus();
            } else {
                vBox2.setVisible(true);
                HBox1.setVisible(false);
                HBox2.setVisible(false);

                Move enemyMove = npcPokemon.chooseMove();
                npcPokemon.attack(playerPokemon, enemyMove, terrain);


                textBubble.showMessage(npcPokemon.getName() + " uses " + enemyMove.getName());

                playerHPBar.updateHPBars(() -> {

                    playerPokemon.attack(npcPokemon, move, terrain);

                    textBubble.showMessage(playerPokemon.getName() + " uses " + move.getName());

                    enemyHPBar.updateHPBars(null);

                    if (playerPokemon.isKO()) {
                        textBubble.showMessage(playerPokemon.getName() + " is K.0 ");
                    }
                });

                HBox1.setVisible(true);
                HBox2.setVisible(true);
            }
        }
    }
}
