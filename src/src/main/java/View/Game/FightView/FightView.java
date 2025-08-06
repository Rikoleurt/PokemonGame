package View.Game.FightView;


import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;

import View.Game.FightView.InfoBars.Bar;
import View.Game.FightView.InfoBars.OpponentBar;
import View.Game.FightView.InfoBars.PlayerBar;
import View.Game.FightView.Text.StatBubble;
import View.Game.FightView.Text.TextBubble;

import View.Game.InventoryView.Bag.BagView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

import static Model.StaticObjects.NPC.initiateEnemy;
import static Model.StaticObjects.Player.initiatePlayer;

public class FightView extends BorderPane {

    TextBubble textBubble = new TextBubble();
    StatBubble statBubble = new StatBubble();

    public static Player player = initiatePlayer();
    public static NPC npc = initiateEnemy();

    public static Pokemon playerPokemon = player.getFrontPokemon();
    public static Pokemon npcPokemon = npc.getFrontPokemon();

    Bar opponentBar = new OpponentBar(5, npcPokemon);
    Bar playerBar = new PlayerBar(5, playerPokemon);

    FightButtons fightButtons = new FightButtons(textBubble, opponentBar, playerBar);

    public FightView() {
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(20);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);
        
        textBubble.setPrefHeight(150);
        textBubble.prefWidthProperty().bind(bottomBox.widthProperty().subtract(320));
        HBox.setHgrow(textBubble, Priority.ALWAYS);

        bottomBox.getChildren().addAll(textBubble, fightButtons);

        setTop(opponentBar);
        setRight(playerBar);
        setBottom(bottomBox);

        setAlignment(opponentBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);
    }

    public TextBubble getTextBubble() {
        return textBubble;
    }
}
