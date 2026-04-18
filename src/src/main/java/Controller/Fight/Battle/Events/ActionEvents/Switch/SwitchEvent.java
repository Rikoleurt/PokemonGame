package Controller.Fight.Battle.Events.ActionEvents.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Model.Person.Fighter;
import Model.Pokemon.Field;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import Utils.SceneManager;
import View.Game.Switch.SwitchFaintedView;

import static View.Game.Battle.BattleView.*;
import static View.Game.Battle.BattleView.refreshSprites;

public class SwitchEvent extends BattleEvent {
    private final Fighter fighter;
    private final Pokemon other;
    private final Field field;
    private final BattleExecutor executor;

    public SwitchEvent(Fighter fighter, Pokemon other, Field field, BattleExecutor executor) {
        this.fighter = fighter;
        this.other = other;
        this.field = field;
        this.executor = executor;
    }

    @Override
    public void execute() {
        if (fighter == player) {
            SwitchFaintedView switchView = new SwitchFaintedView(player, npc, BattleView.getTextBubble(), BattleView.getFightButtons(), () -> SceneManager.switchStageTo(SceneManager.getFightView()));
            SceneManager.switchStageTo(switchView);
            switchView.setTurnDisable(true);
            refreshSprites();
            return;
        }

        if (fighter == npc) {
            npc.setFront(other, field);
            BattleView.refreshSprites();
            BattleView.getOpponentBar().setPokemon(npc.getFrontPokemon());
            BattleView.getFightButtons().resetFightButtons(getClass().getSimpleName());
            BattleView.getOpponentBar().refreshBar();
        }
    }
}
