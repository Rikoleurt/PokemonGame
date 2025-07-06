package Controller.Fight.Battle.Events;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.FightView.InfoBars.Bar;
import View.Game.FightView.Text.TextBubble;

public class DamageEvent extends BattleEvent {
    private final Pokemon launcher;
    private final Pokemon target;
    private final Move move;
    private final Terrain terrain;
    private final TextBubble bubble;
    private final Bar targetBar;


    public DamageEvent(Pokemon launcher, Pokemon target, Move move, Terrain terrain, TextBubble bubble, Bar targetBar) {
        this.launcher = launcher;
        this.target = target;
        this.move = move;
        this.terrain = terrain;
        this.bubble = bubble;
        this.targetBar = targetBar;

    }

    @Override
    public void execute() {
        launcher.attack(target, move, terrain, bubble, targetBar);
        onFinish(); // <- Creates bugs as we call attack method
    }
}
