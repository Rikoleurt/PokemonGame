package View.FightView;

import Person.NPC;
import Person.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

import static StaticObjects.NPC.initiateEnemy;
import static StaticObjects.Player.initiatePlayer;

public class FightView extends BorderPane {

    PlayerHPBar playerBar = new PlayerHPBar(10);
    EnemyHPBar enemyHPBar = new EnemyHPBar(10);
    FightButtons fightButtons = new FightButtons(enemyHPBar, playerBar);

    protected static Player player = initiatePlayer();
    protected static NPC npc = initiateEnemy();

    public FightView() {
        setBottom(fightButtons);
        setRight(playerBar);
        setTop(enemyHPBar);

        setAlignment(enemyHPBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);
        setAlignment(fightButtons, Pos.BOTTOM_RIGHT);
    }
}
