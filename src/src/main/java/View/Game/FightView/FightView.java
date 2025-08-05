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
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

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

    public FightView(){

        setBottom(fightButtons);
        setRight(playerBar);
        setTop(opponentBar);

        setAlignment(opponentBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);
        setAlignment(fightButtons, Pos.BOTTOM_RIGHT);
    }

    public TextBubble getTextBubble() {
        return textBubble;
    }
}
