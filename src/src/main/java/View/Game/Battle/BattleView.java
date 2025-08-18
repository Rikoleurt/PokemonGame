package View.Game.Battle;


import Model.Pokemon.Pokemon;

import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Weather;
import Model.Person.NPC;
import Model.Person.Player;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.InfoBars.OpponentBar;
import View.Game.Battle.InfoBars.PlayerBar;
import View.Game.Battle.Text.StatBubble;
import View.Game.Battle.Text.TextBubble;

import View.Game.SceneManager;
import View.Game.Switch.SwitchView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import static Model.StaticObjects.NPCExample.initiateEnemy;
import static Model.StaticObjects.PlayerExample.initiatePlayer;

public class BattleView extends BorderPane {

    static TextBubble textBubble = new TextBubble();
    static StatBubble statBubble = new StatBubble();

    public static Player player = initiatePlayer();
    public static NPC npc = initiateEnemy();

    static Pokemon playerPokemon = player.getFrontPokemon();
    static Pokemon npcPokemon = npc.getFrontPokemon();

    static Bar opponentBar = new OpponentBar(5, npcPokemon);
    static Bar playerBar = new PlayerBar(5, playerPokemon);
    public static Terrain terrain = new Terrain(player.getTeam(), npc.getTeam(), Debris.normal, Weather.normal);

    static BattleButtons battleButtons = new BattleButtons(textBubble, opponentBar, playerBar);

    public BattleView() {
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(20);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);
        
        textBubble.setPrefHeight(150);
        textBubble.prefWidthProperty().bind(bottomBox.widthProperty().subtract(320));
        HBox.setHgrow(textBubble, Priority.ALWAYS);

        bottomBox.getChildren().addAll(textBubble, battleButtons);

        setTop(opponentBar);
        setRight(playerBar);
        setBottom(bottomBox);

        setAlignment(opponentBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);

        if(npc.getFrontPokemon().isKO()) {

        }
    }

    public TextBubble getTextBubble() {
        return textBubble;
    }

    public static Bar getOpponentBar() {
        return opponentBar;
    }

    public static Bar getPlayerBar() {
        return playerBar;
    }

    public static BattleButtons getFightButtons() {
        return battleButtons;
    }

    public static Model.Person.NPC getNpc() {
        return npc;
    }

    public static Model.Person.Player getPlayer() {
        return player;
    }

    public static Pokemon getNpcPokemon() {
        return npcPokemon;
    }

    public static Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public static StatBubble getStatBubble() {
        return statBubble;
    }

    public static Terrain getTerrain() {
        return terrain;
    }

    private void npcSwitch(){}
}
