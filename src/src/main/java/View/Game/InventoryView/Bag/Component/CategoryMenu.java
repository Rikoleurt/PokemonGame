package View.Game.InventoryView.Bag.Component;

import Model.Inventory.Category;
import Model.Person.NPC;
import Model.Person.Player;
import View.Game.FightView.Text.TextBubble;
import View.Game.InventoryView.Bag.Component.Categories;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CategoryMenu extends HBox {

    Player player;
    private final ToggleGroup group = new ToggleGroup();
    private final Map<ToggleButton, Category> map = new LinkedHashMap<>();
    private final ObjectProperty<Category> selectedCategory = new SimpleObjectProperty<>();
    private Consumer<Category> onCategorySelected;
    TextBubble textBubble;
    NPC npc;

    public CategoryMenu(Player player, int spacing, TextBubble textBubble, NPC npc) {
        this.player = player;
        this.npc = npc;
        this.textBubble = textBubble;
        setSpacing(spacing);
        setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> components = getChildren();

        VBox vWrapper = new VBox();
        vWrapper.setSpacing(spacing);

        HBox menuBox = new HBox();
        menuBox.setSpacing(spacing);
        ObservableList<Node> menuComponents = menuBox.getChildren();

        Button leftArrow = new Button("<");
        menuComponents.add(leftArrow);

        for (Category c : Category.values()) {
            ToggleButton tb = new ToggleButton(labelOf(c));
            tb.setUserData(c);
            tb.setToggleGroup(group);
            map.put(tb, c);
            menuComponents.add(tb);
        }

        Button rightArrow = new Button(">");
        menuComponents.add(rightArrow);

        leftArrow.setOnAction(e -> move(-1));
        rightArrow.setOnAction(e -> move(1));

        group.selectedToggleProperty().addListener((obs, o, n) -> {
            if (n != null) {
                Category c = (Category) n.getUserData();
                selectedCategory.set(c);
                if (onCategorySelected != null) onCategorySelected.accept(c);
            }
        });

        setFocusTraversable(true);
        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) move(-1);
            else if (e.getCode() == KeyCode.RIGHT) move(1);
        });

        Categories categories = new Categories(player, spacing, textBubble, npc);
        vWrapper.getChildren().addAll(menuBox, categories);
        components.add(vWrapper);

        setOnCategorySelected(categories::showCategory);
        select(Category.HEALTH);
        categories.showCategory(Category.HEALTH);
    }

    private String labelOf(Category c) {
        return switch (c) {
            case HEALTH -> "Health";
            case POKEBALL -> "Pokeball";
            case FIGHT -> "Fight";
            case BERRIES -> "Berries";
            case UTILITY -> "Utility";
            case CT_CS -> "CT/CS";
            case KEY -> "Key";
            default -> "Other";
        };
    }

    private void move(int delta) {
        Category current = getSelectedCategory();
        if (current == null) return;
        Category[] all = Category.values();
        int i = current.ordinal();
        int n = all.length;
        int j = (i + (delta % n) + n) % n;
        select(all[j]);
    }

    public void setOnCategorySelected(Consumer<Category> consumer) {
        this.onCategorySelected = consumer;
    }

    public Category getSelectedCategory() {
        return selectedCategory.get();
    }

    public ObjectProperty<Category> selectedCategoryProperty() {
        return selectedCategory;
    }

    public void select(Category category) {
        if (category == null) return;
        for (Map.Entry<ToggleButton, Category> e : map.entrySet()) {
            if (e.getValue() == category) {
                group.selectToggle(e.getKey());
                break;
            }
        }
    }
}
