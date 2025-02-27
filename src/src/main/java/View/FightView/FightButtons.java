package View.FightView;

import Pokemon.Pokemon;
import Pokemon.Move;
import Pokemon.PokemonEnum.Status;
import Pokemon.Terrain;

import Pokemon.TerrainEnum.Debris;
import Pokemon.TerrainEnum.Meteo;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    // Move pAtk4 = playerPokemon.getAttacks().get(3);

    Pokemon npcPokemon = npc.getFrontPokemon();

    Move nAtk1 = npcPokemon.getAttacks().getFirst();
    Move nAtk2 = npcPokemon.getAttacks().get(1);
    Move nAtk3 = npcPokemon.getAttacks().get(2);
    Move nAtk4 = npcPokemon.getAttacks().get(3);

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
    Button atk4Button = createButton(null);
    //Button atk4Button = createButton(playerPokemon.getAttacks().get(3).getName());

    private final EnemyHPBar enemyHPBar;
    private final PlayerHPBar playerHPBar;
    private TextBubble textBubble;

    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    HBox HBox1 = new HBox(fightButton, bagButton);
    HBox HBox2 = new HBox(runButton, pokemonButton);

    // Variables

    boolean hasFinishedTurn = false;

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

//        textBubble.sceneProperty().addListener((obs, oldScene, newScene) -> {
//            if (newScene != null) {
//                newScene.setOnKeyPressed(e -> {
//                    System.out.println(e.getCode());
//                    if (e.getCode() == KeyCode.SPACE && !isPriority()) {
//                        System.out.println(e.getCode() + " pressed");
//                        textBubble.hideMessage();
//                        hasFinishedTurn = true;
//                        System.out.println(hasFinishedTurn);
//                        e.consume(); // Empêche l'événement de se propager
//                        if(hasFinishedTurn) {
//                            setPriority(false);
//                            hasFinishedTurn = false;
//                        }
//                    }
//                });
//            }
//        });
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
        return button;
    }

    private void atkButtonAction() {
        atk1Button.setOnAction(e -> {
            AtkButtonAction(pAtk1, playerPokemon, npcPokemon, terrain);
        });

        atk2Button.setOnAction(e -> {
            AtkButtonAction(pAtk2, playerPokemon, npcPokemon, terrain);
        });

        atk3Button.setOnAction(e -> {
            AtkButtonAction(pAtk3, playerPokemon, npcPokemon, terrain);
        });

        atk4Button.setOnAction(e -> {
            System.out.println("Atk4 pressé");
            this.requestFocus();
        });
    }

    private boolean compareSpeed(){
        return playerPokemon.getSpeed() > npcPokemon.getSpeed();
    }

    private void AtkButtonAction(Move move, Pokemon playerPokemon, Pokemon npcPokemon, Terrain terrain) {
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
                    System.out.println("textBubble : " + textBubble.getParent());
                    playerHPBar.updateHPBars(null);
                    if (npcPokemon.isKO().equals(Status.KO)) {
                        textBubble.showMessage(npcPokemon.getName() + " is K.0 ");
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
                    System.out.println("textBubble : " + textBubble.getParent());
                    enemyHPBar.updateHPBars(null);
                    if (playerPokemon.isKO().equals(Status.KO)) {
                        textBubble.showMessage(playerPokemon.getName() + " is K.0 ");
                    }
                });
                HBox1.setVisible(true);
                HBox2.setVisible(true);
            }
        }
    }
}
