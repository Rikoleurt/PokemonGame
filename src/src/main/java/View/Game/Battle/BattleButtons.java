package View.Game.Battle;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.AttackEvent;
import Controller.Fight.Battle.Events.MessageEvent;

import Controller.Fight.Battle.Events.UseItemEvent;
import Model.Inventory.Items.Item;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Move;
import Model.Pokemon.PokemonEnum.Status;
import Model.Pokemon.Terrain;

import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.Text.TextBubble;
import View.Game.Inventory.Bag.BagView;
import View.Game.SceneManager;

import View.Game.Switch.SwitchView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static View.Game.Battle.BattleView.npc;
import static View.Game.Battle.BattleView.player;
import static View.Game.Battle.BattleView.terrain;

public class BattleButtons extends HBox {

    static Font font = Font.loadFont(BattleButtons.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    private Pokemon playerPokemon = player.getFrontPokemon();
    private Pokemon npcPokemon = npc.getFrontPokemon();

    Move pAtk1 = getMove(0);
    Move pAtk2 = getMove(1);
    Move pAtk3 = getMove(2);
    Move pAtk4 = getMove(3);

    // Initial buttons
    static Button runButton = createBaseButtons("#67b60b","Run");
    static Button attackButton = createBaseButtons("#cc3434", "Fight");
    static Button bagButton = createBaseButtons("#db8813", "Bag");
    static Button pokemonButton = createBaseButtons("#1371f4","Pokemon");

    // Attack buttons
    Button atk1Button = createBaseButtons(getColorFromAttack(pAtk1), getAttackName(0));
    Button atk2Button = createBaseButtons(getColorFromAttack(pAtk2), getAttackName(1));
    Button atk3Button = createBaseButtons(getColorFromAttack(pAtk3), getAttackName(2));
    Button atk4Button = createBaseButtons(getColorFromAttack(pAtk4), getAttackName(3));

    private final Bar opponentBar;
    private final Bar playerBar;
    private final TextBubble textBubble;

    VBox vBox = new VBox();
    VBox vBox2 = new VBox();

    private final static HBox HBox1 = new HBox(attackButton, bagButton);
    private final static HBox HBox2 = new HBox(runButton, pokemonButton);

    BattleExecutor executor = BattleExecutor.getInstance();

    public BattleButtons(TextBubble textBubble, Bar opponentBar, Bar playerBar) {
        this.textBubble = textBubble;
        this.opponentBar = opponentBar;
        this.playerBar = playerBar;

        ObservableList<Node> components = getChildren();

        refreshFromCurrentPokemon();

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

        // Button handler
        setFocusTraversable(true);
        setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        Platform.runLater(() -> {
            Scene scene = SceneManager.getStage().getScene();
            if (scene != null) {
                scene.addEventFilter(KeyEvent.KEY_PRESSED, ev -> handleKeyPress(ev.getCode()));
            }
            attackButton.requestFocus();
        });

        // Buttons action
        attackButton.setOnAction(e -> {
           components.clear();

           HBox1.getChildren().clear();
           HBox2.getChildren().clear();
           vBox.getChildren().clear();

           HBox1.getChildren().addAll(atk1Button, atk2Button);
           HBox2.getChildren().addAll(atk3Button, atk4Button);

           vBox.getChildren().addAll(HBox1, HBox2);

           components.addAll(vBox2, vBox);

        });
        atk1Button.requestFocus();

        bagButton.setOnAction(e -> {
            BagView bagView = new BagView(player, npc, textBubble, () -> {
                SceneManager.switchStageTo(SceneManager.getFightView()); // back to fight
            });
            SceneManager.switchStageTo(bagView); // go in the bag
        });


        pokemonButton.setOnAction(e -> {
            SwitchView switchView = new SwitchView(player, textBubble, () -> SceneManager.switchStageTo(SceneManager.getFightView()));
            SceneManager.switchStageTo(switchView);
        });

        runButton.setOnAction(e -> {
        });

        onMoveButton();

    }

    private void onMoveButton() {
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

        Move npcMove = npcPokemon.chooseMove();
        String npcChoice = npc.makeChoice();
        Item itemChoice = npc.itemChoice(npcPokemon);

        if (itemChoice != null) System.out.println(itemChoice.getName());
        System.out.println(npcChoice);

        // --- NPC utilise un objet ---
        if ("Item".equals(npcChoice) && itemChoice != null) {
            getHBox1().setVisible(false);
            getHBox2().setVisible(false);

            executor.addEvent(new UseItemEvent(npc, itemChoice, npcPokemon, textBubble, executor));
            executor.executeNext(() -> {
                if (playerPokemon.getStatus() != Status.KO) {
                    // Le joueur attaque le NPC -> MAJ de la barre adverse
                    executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, BattleView.getTerrain(), textBubble, opponentBar, executor));
                } else {
                    executor.addEvent(new MessageEvent(textBubble, playerPokemon.getName() + " fainted."));
                }
                executor.executeNext(() -> BattleView.getFightButtons().resetFightButtons());
            });

            requestFocus();
            return; // IMPORTANT: sinon on retombe dans la logique d'attaque/priorité => double attaque
        }

        // Si le NPC a choisi Item mais qu'aucun item pertinent n'est dispo, on retombe en attaque
        if ("Item".equals(npcChoice) && itemChoice == null) {
            npcChoice = "Attack";
        }

        // --- NPC switch ---
        if ("Switch".equals(npcChoice)) {
            Pokemon next = npc.chooseSwitchTarget();
            if (next != null) {
                getHBox1().setVisible(false);
                getHBox2().setVisible(false);
                executor.addEvent(new MessageEvent(textBubble, npc.getFrontPokemon().getName() + " stop!"));
                npc.setFront(next, terrain);
                executor.addEvent(new MessageEvent(textBubble, npc.getFrontPokemon().getName() + " go!"));
                opponentBar.setPokemon(npc.getFrontPokemon());
                executor.executeNext(() -> {
                    Pokemon freshNpc = BattleView.getNpc().getFrontPokemon();
                    Pokemon freshPlayer = player.getFrontPokemon();
                    if (freshPlayer.getStatus() != Status.KO) {
                        executor.addEvent(new AttackEvent(freshPlayer, freshNpc, move, BattleView.getTerrain(), textBubble, opponentBar, executor));
                    } else {
                        executor.addEvent(new MessageEvent(textBubble, freshPlayer.getName() + " fainted."));
                    }
                    executor.executeNext(() -> BattleView.getFightButtons().resetFightButtons());
                });
                requestFocus();
            }
            return;
        }

        // --- Attaques (priorité) ---
        if (npcPokemon.hasPriority(playerPokemon)) {
            executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcMove, terrain, textBubble, playerBar, executor));
            executor.executeNext(() -> {
                if (playerPokemon.getStatus() != Status.KO) {
                    executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, textBubble, opponentBar, executor));
                } else {
                    handlePlayerPokemonKO();
                }
                executor.executeNext(() -> Platform.runLater(this::resetFightButtons));
            });
        } else {
            executor.addEvent(new AttackEvent(playerPokemon, npcPokemon, move, terrain, textBubble, opponentBar, executor));
            executor.executeNext(() -> {
                if (npcPokemon.getStatus() != Status.KO) {
                    executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcMove, terrain, textBubble, playerBar, executor));
                } else {
                    handleNpcPokemonKO();
                }
                executor.executeNext(() -> Platform.runLater(this::resetFightButtons));
            });
        }
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

    public void resetFightButtons() {
        refreshFromCurrentPokemon();
        ObservableList<Node> components = this.getChildren();
        HBox1.getChildren().clear();
        HBox2.getChildren().clear();
        vBox.getChildren().clear();
        components.clear();

        HBox1.getChildren().addAll(attackButton, bagButton);
        HBox2.getChildren().addAll(runButton, pokemonButton);
        vBox.getChildren().addAll(HBox1, HBox2);

        components.addAll(vBox2, vBox);

        HBox1.setVisible(true);
        HBox2.setVisible(true);
        vBox.setVisible(true);

        textBubble.showMessage("What will " + playerPokemon.getName() + " do?");
//        executor.addEvent(new MessageEvent(textBubble, "What will " + playerPokemon.getName() + " do?"));
//        executor.executeNext(null);
        attackButton.requestFocus();
    }

    static private Button createBaseButtons(String color, String name) {
        Button button = new Button(name);
        button.getStyleClass().add("battle-button");
        button.setPrefSize(175, 75);
        button.setStyle("-fx-background-color:" + color + "; " +
                "-fx-border-color: black; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 6px; " +
                "-fx-background-radius: 9px; " +
                "-fx-text-fill: black; ");
        if (name == null) {
            button.setText(" - ");
            button.getStyleClass().add("battle-button");
            button.setPrefSize(175, 75);
            button.setStyle("-fx-background-color: white; " +
                    "-fx-border-color: black; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 5px; " +
                    "-fx-background-radius: 0px; " +
                    "-fx-text-fill: black; ");
        }
        return button;
    }



    private static String getColorFromAttack(Move move) {
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

    private void handleKeyPress(KeyCode code){
        if (code == KeyCode.A){
            Scene scene = SceneManager.getStage().getScene();
            Node focused = scene != null ? scene.getFocusOwner() : null;
            if (focused instanceof Button b){
                b.fire();
            }
        }
        if (code == KeyCode.B){
            boolean onMainMenu = HBox1.getChildren().contains(attackButton) && HBox2.getChildren().contains(pokemonButton);
            if (!onMainMenu){
                resetFightButtons();
                attackButton.requestFocus();
            }
        }
    }

    private void refreshFromCurrentPokemon() {
        playerPokemon = player.getFrontPokemon();
        npcPokemon = npc.getFrontPokemon();

        pAtk1 = getMove(0);
        pAtk2 = getMove(1);
        pAtk3 = getMove(2);
        pAtk4 = getMove(3);

        atk1Button = createBaseButtons(getColorFromAttack(pAtk1), getAttackName(0));
        atk2Button = createBaseButtons(getColorFromAttack(pAtk2), getAttackName(1));
        atk3Button = createBaseButtons(getColorFromAttack(pAtk3), getAttackName(2));
        atk4Button = createBaseButtons(getColorFromAttack(pAtk4), getAttackName(3));

        onMoveButton();
    }

    public static HBox getHBox1(){
        return HBox1;
    }

    public static HBox getHBox2(){
        return HBox2;
    }
}
