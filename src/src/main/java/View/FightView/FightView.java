package View.FightView;

import Controller.Log.FightLogger;

import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;

import View.FightView.InfoBars.Bar;
import View.FightView.InfoBars.OpponentBar;
import View.FightView.InfoBars.PlayerBar;
import View.FightView.Text.StatBubble;
import View.FightView.Text.TextBubble;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

import static Model.StaticObjects.NPC.initiateEnemy;
import static Model.StaticObjects.Player.initiatePlayer;

public class FightView extends BorderPane {

    TextBubble textBubble = new TextBubble();
    StatBubble statBubble = new StatBubble();;
    FightLogger logger = new FightLogger(textBubble);

    public static Player player = initiatePlayer();
    public static NPC npc = initiateEnemy();

    public static Pokemon playerPokemon = player.getFrontPokemon();
    public static Pokemon npcPokemon = npc.getFrontPokemon();

    Bar opponentBar = new OpponentBar(5, npcPokemon);
    Bar playerBar = new PlayerBar(5, playerPokemon );

    FightButtons fightButtons = new FightButtons(textBubble, opponentBar, playerBar);

    public FightView(){
        player.getFrontPokemon().setLogger(logger);
        npc.getFrontPokemon().setLogger(logger);

        setBottom(fightButtons);
        setRight(playerBar);
        setTop(opponentBar);

        setAlignment(opponentBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);
        setAlignment(fightButtons, Pos.BOTTOM_RIGHT);
    }
}
