package View.Game.Inventory.Bag.Component;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.AttackEvent;
import Controller.Fight.Battle.Events.MessageEvent;
import Controller.Fight.Battle.Events.UseItemEvent;
import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Set;

public class Categories extends VBox {

    Player player;
    NPC npc;
    HBox hBox;
    TextBubble textBubble;
    TextBubble bagBubble = new TextBubble();
    BattleExecutor executor = BattleExecutor.getInstance();

    public Categories(Player player, int spacing, TextBubble textBubble, NPC npc) {
        this.player = player;
        this.npc = npc;
        this.textBubble = textBubble;
        ObservableList<Node> components = getChildren();
        setSpacing(spacing);
        hBox = new HBox();
        bagBubble.hideBubble();
        bagBubble.setFocusTraversable(true);
        bagBubble.setOnKeyPressed(e -> bagBubble.handleKeyPress(e.getCode()));
        components.addAll(hBox);
    }

    public TextBubble getBagBubble() {
        return bagBubble;
    }

    public void showCategory(Category category) {
        Pokemon npcPokemon = npc.getFrontPokemon();
        Pokemon playerPokemon = player.getFrontPokemon();
        hBox.getChildren().clear();
        Bag bag = player.getBag();
        Set<Item> items = bag.getInventory().keySet();
        for (Item item : items) {
            if (item.getCategory() == category && bag.getQuantity(item) > 0) {
                int qty = bag.getQuantity(item);
                Button button = new Button();
                button.getStyleClass().add("item-button");
                Label nameLabel = new Label(item.getName());
                nameLabel.getStyleClass().add("item-name");
                Label qtyLabel = new Label("x" + qty);
                qtyLabel.getStyleClass().add("item-qty");
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                HBox row = new HBox(nameLabel, spacer, qtyLabel);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setSpacing(8);
                button.setGraphic(row);
                button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                button.setOnAction(e -> {
                    Pokemon chosenPokemon = player.getFrontPokemon();
                    if (chosenPokemon.getHP() < chosenPokemon.getMaxHP()) {
                        SceneManager.switchStageTo(SceneManager.getFightView());
                        BattleButtons.getHBox1().setVisible(false);
                        BattleButtons.getHBox2().setVisible(false);
                        executor.addEvent(new UseItemEvent(player, item, chosenPokemon, textBubble, executor));
                        executor.executeNext(() -> {
                            if (npcPokemon.getStatus() != Status.KO) {
                                Move npcMove = npcPokemon.chooseMove();
                                executor.addEvent(new AttackEvent(npcPokemon, playerPokemon, npcMove, BattleView.getTerrain(), textBubble, BattleView.getPlayerBar(), executor));
                            } else {
                                executor.addEvent(new MessageEvent(textBubble, npcPokemon.getName() + " fainted."));
                            }
                            executor.executeNext(() -> {
                                BattleView.getFightButtons().resetFightButtons();
                            });
                        });
                    } else {
                        bagBubble.showBubble();
                        bagBubble.showMessage("This Pokémon is already in top form!");
                        PauseTransition pt = new PauseTransition(Duration.seconds(2));
                        pt.setOnFinished(ev2 -> {
                            bagBubble.hideMessage();
                            bagBubble.hideBubble();
                        });
                        pt.play();
                        bagBubble.requestFocus();

                    }
                });
                hBox.getChildren().add(button);
            }
        }
    }
}
