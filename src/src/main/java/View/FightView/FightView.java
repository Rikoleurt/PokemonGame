package View.FightView;

import Controller.Log.FightLogger;
import Model.Person.NPC;
import Model.Person.Player;
import View.FightView.Text.StatBubble;
import View.FightView.Text.TextBubble;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

import static Model.StaticObjects.NPC.initiateEnemy;
import static Model.StaticObjects.Player.initiatePlayer;

public class FightView extends BorderPane {

    TextBubble textBubble = new TextBubble();
    StatBubble statBubble = new StatBubble();
    PlayerBars playerBar = new PlayerBars(10, textBubble, statBubble);
    EnemyHPBar enemyHPBar = new EnemyHPBar(10, textBubble);
    FightButtons fightButtons = new FightButtons(enemyHPBar, playerBar, textBubble);
    FightLogger logger = new FightLogger(textBubble);


    protected static Player player = initiatePlayer();
    protected static NPC npc = initiateEnemy();

    public FightView() {

        player.getFrontPokemon().setLogger(logger);
        setBottom(fightButtons);
        setRight(playerBar);
        setTop(enemyHPBar);

        setAlignment(enemyHPBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);
        setAlignment(fightButtons, Pos.BOTTOM_RIGHT);
    }
}
