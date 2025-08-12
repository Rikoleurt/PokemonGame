package View.Game.InventoryView.Bag.Component;

import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Person.Player;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;
import java.util.Set;

public class Categories extends VBox {

    Player player;
    HBox hBox;

    public Categories(Player player, int spacing) {
        this.player = player;
        ObservableList<Node> components = getChildren();
        setSpacing(spacing);
        hBox = new HBox();
        components.add(hBox);
    }

    public void showCategory(Category category) {
        hBox.getChildren().clear();
        Bag bag = player.getBag();
        Set<Item> items = bag.getInventory().keySet();
        for (Item item : items) {
            if (item.getCategory() == category) {
                Button button = new Button(item.getName());
                hBox.getChildren().add(button);
            }
        }
    }

    private void addButtons(HBox hbox, Category category) {
        ObservableList<Node> components = hbox.getChildren();
        Bag bag = player.getBag();
        Set<Item> items = bag.getInventory().keySet();
        for (Item item : items) {
            Category itemCategory = item.getCategory();
            if(itemCategory == category) {
                Button button = new Button(item.getName());
                components.add(button);
            }
        }
    }
}
