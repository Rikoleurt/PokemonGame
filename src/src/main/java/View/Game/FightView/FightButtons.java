package View.Game.FightView;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.MessageEvent;
import Controller.Fight.Turns.Turn;

import Model.Pokemon.Pokemon;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Meteo;

import View.Game.FightView.InfoBars.Bar;
import View.Game.FightView.Text.TextBubble;
import View.Game.InventoryView.Bag.BagView;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static View.Game.FightView.FightView.npc;
import static View.Game.FightView.FightView.player;
import View.Game.SceneManager;
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
            BagView bagView = new BagView(player, () -> {
                SceneManager.switchStageTo(new FightView()); // Retour au combat
            });

            SceneManager.switchStageTo(bagView); // Aller dans le sac
        });


        pokemonButton.setOnAction(e -> {
        });

        runButton.setOnAction(e -> {
        });

        atkButton();

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

    private void atkButton() {
        atk1Button.setOnAction(e -> {
            if(pAtk1 != null) {
                atkButtonAction(pAtk1, terrain);
            }
        });

        atk2Button.setOnAction(e -> {
            if(pAtk2 != null) {
                atkButtonAction(pAtk2, terrain);
            }

        });

        atk3Button.setOnAction(e -> {
            if(pAtk3 != null) {
                atkButtonAction(pAtk3, terrain);
            }
        });

        atk4Button.setOnAction(e -> {
//            if(pAtk4 != null) {
//                atkButtonAction(pAtk4, terrain);
//            }
        });
    }

    private void atkButtonAction(Move move, Terrain terrain) {
        HBox1.setVisible(false);
        HBox2.setVisible(false);
        Move npcMove = npcPokemon.chooseMove();
        // Problem : If one of the Pokémon are KO within the turn, then it'll still attack

        if(playerPokemon.getStatus() != Status.KO){
            playerPokemon.attack(npcPokemon, move, terrain, textBubble, opponentBar);
        } else if (playerPokemon.getStatus() == Status.KO){
            handlePlayerPokemonKO();
        }
        if(npcPokemon.getStatus() != Status.KO){
            npcPokemon.attack(playerPokemon, npcMove , terrain, textBubble, playerBar);
        } else if (npcPokemon.getStatus() == Status.KO){
            handleNpcPokemonKO();
        }
        executor.executeNext(() -> {
            Platform.runLater(this::resetFightButtons);
        });
        requestFocus();
    }

    private void handlePlayerPokemonKO(){
        executor.addEvent(new MessageEvent(textBubble, playerPokemon.getName() + " fainted."));
        // Open another window to let the player chose its next Pokémon
    }
    private void handleNpcPokemonKO(){
        executor.addEvent(new MessageEvent(textBubble, npcPokemon.getName() + " fainted."));
        // Let the enemy switch
        // To make it easier for the AI, we do not allow switches.
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

    private void resetFightButtons() {
        ObservableList<Node> components = this.getChildren();

        HBox1.getChildren().clear();
        HBox2.getChildren().clear();
        vBox.getChildren().clear();
        components.clear();

        HBox1.getChildren().addAll(fightButton, bagButton);
        HBox2.getChildren().addAll(runButton, pokemonButton);
        vBox.getChildren().addAll(HBox1, HBox2);

        components.addAll(vBox2, vBox);

        HBox1.setVisible(true);
        HBox2.setVisible(true);
        vBox.setVisible(true);

        textBubble.showMessage("What will " + playerPokemon.getName() + " do?");
    }
}
