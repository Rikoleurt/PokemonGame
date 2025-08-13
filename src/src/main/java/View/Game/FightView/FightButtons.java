package View.Game.FightView;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.AttackEvent;
import Controller.Fight.Battle.Events.MessageEvent;

import Model.Pokemon.Pokemon;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Meteo;

import View.Game.FightView.InfoBars.Bar;
import View.Game.FightView.Text.TextBubble;
import View.Game.InventoryView.Bag.BagView;
import View.Game.SceneManager;

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
import static View.Game.FightView.FightView.terrain;

public class FightButtons extends HBox {

    static Font font = Font.loadFont(FightButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon playerPokemon = player.getFrontPokemon();

    Move pAtk1 = getMove(0);
    Move pAtk2 = getMove(1);
    Move pAtk3 = getMove(2);
    Move pAtk4 = getMove(3);

    Pokemon npcPokemon = npc.getFrontPokemon();

    // Initial buttons
    Button runButton = createBaseButtons("#67b60b","Run");
    Button fightButton = createBaseButtons("#cc3434", "Fight");
    Button bagButton = createBaseButtons("#db8813", "Bag");
    Button pokemonButton = createBaseButtons("#1371f4","Pokemon");

    // Attack buttons
//    Button atk1Button = createButton(getAttackName(0));
//    Button atk2Button = createButton(getAttackName(1));
//    Button atk3Button = createButton(getAttackName(2));
//    Button atk4Button = createButton(getAttackName(3));

    Button atk1Button = createBaseButtons(getColorFromAttack(pAtk1), getAttackName(0));
    Button atk2Button = createBaseButtons(getColorFromAttack(pAtk2), getAttackName(1));
    Button atk3Button = createBaseButtons(getColorFromAttack(pAtk3), getAttackName(2));
    Button atk4Button = createBaseButtons(getColorFromAttack(pAtk4), getAttackName(3));

    private final Bar opponentBar;
    private final Bar playerBar;
    private final TextBubble textBubble;

    VBox vBox = new VBox();
    VBox vBox2 = new VBox();
    HBox HBox1 = new HBox(fightButton, bagButton);
    HBox HBox2 = new HBox(runButton, pokemonButton);

    BattleExecutor executor = BattleExecutor.getInstance();

    public FightButtons(TextBubble textBubble, Bar opponentBar, Bar playerBar) {
        this.textBubble = textBubble;
        this.opponentBar = opponentBar;
        this.playerBar = playerBar;

        ObservableList<Node> components = getChildren();

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
                SceneManager.switchStageTo(SceneManager.getFightView()); // back to fight
            }, textBubble, npc);
            SceneManager.switchStageTo(bagView); // go in the bag
        });


        pokemonButton.setOnAction(e -> {
        });

        runButton.setOnAction(e -> {
        });

        atkButton();

    }

    private void atkButton() {
        atk1Button.setOnAction(e -> {
            if(pAtk1 != null) {
                onAttackPressed(pAtk1, terrain);
            }
        });

        atk2Button.setOnAction(e -> {
            if(pAtk2 != null) {
                onAttackPressed(pAtk2, terrain);
            }

        });

        atk3Button.setOnAction(e -> {
            if(pAtk3 != null) {
                onAttackPressed(pAtk3, terrain);
            }
        });

        atk4Button.setOnAction(e -> {
//            if(pAtk4 != null) {
//                atkButtonAction(pAtk4, terrain);
//            }
        });
    }

    private void onAttackPressed(Move move, Terrain terrain) {
        HBox1.setVisible(false);
        HBox2.setVisible(false);

        executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, textBubble, opponentBar, executor));
        executor.executeNext(() -> {
            if (npcPokemon.getStatus() != Status.KO) {
                Move npcMove = npcPokemon.chooseMove();
                executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcMove, terrain, textBubble, playerBar, executor));
            } else {
                handleNpcPokemonKO();
            }
            executor.executeNext(() -> {
                Platform.runLater(this::resetFightButtons);
            });
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

    private Button createBaseButtons(String color, String name) {
        Button button = new Button(name);
        button.setFont(font);
        button.setPrefSize(175, 75);
        button.setStyle("-fx-background-color:" + color + "; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 6px; " +
                "-fx-background-radius: 9px; " +
                "-fx-font-weight: bold; " +
                "-fx-text-fill: black; ");
        if(name == null){
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

    private String getColorFromAttack(Move move) {
        try {
            return switch (move.getType()) {
                case fire -> "#FF5014";
                case water -> "#5096FF";
                case grass -> "#64C864";
                case poison -> "#B464C8";
                case bug -> "#96D232";
                case ice -> "#82DCE6";
                case dark -> "#5A505A";
                case rock -> "#BEAA78";
                case fairy -> "#FFB4F0";
                case ghost -> "#6E64B4";
                case steel -> "#7896AA";
                case dragon -> "#0050C8";
                case flying -> "#96B4FF";
                case ground -> "#D28232";
                case normal -> "#BEBEBE";
                case psychic -> "#FF64B4";
                case electric -> "#FFDC32";
                case fighting -> "#C83C3C";
                case null -> "white";
            };
        } catch (NullPointerException e) {
            return "white";
        }
    }
}
