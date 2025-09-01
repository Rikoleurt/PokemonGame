package Controller.Fight.Battle.Events.ActionEvents.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.Fighter;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;
import View.Game.Switch.SwitchKOView;

import static View.Game.Battle.BattleView.*;
import static View.Game.Battle.BattleView.refreshSprites;

public class SwitchEvent extends BattleEvent {
    private final Fighter fighter;
    private final Pokemon other;
    private final Terrain terrain;
    private final BattleExecutor executor;

    public SwitchEvent(Fighter fighter, Pokemon other, Terrain terrain, BattleExecutor executor) {
        this.fighter = fighter;
        this.other = other;
        this.terrain = terrain;
        this.executor = executor;
    }

    public SwitchEvent(Fighter fighter, Terrain terrain, BattleExecutor executor) {
        this.fighter = fighter;
        this.executor = executor;
        this.other = null;
        this.terrain = terrain;
    }
    @Override
    public void execute() {
        if(fighter instanceof Player p) {
            SwitchKOView switchView = new SwitchKOView(p, npc, BattleView.getTextBubble(), BattleView.getFightButtons(), () -> SceneManager.switchStageTo(SceneManager.getFightView()));
            SceneManager.switchStageTo(switchView);
            switchView.setTurnDisable(true);
            refreshSprites();
//            BattleView.getPlayerBar().setVisible(false);
//            SceneManager.switchStageTo(SceneManager.getFightView());
//            executor.addEvent(new MessageEvent(p.getFrontPokemon().getName() + " stop!"));
//            p.setFront(other, terrain);
//            BattleView.refreshSprites();
//            executor.addEvent(new MessageEvent(p.getFrontPokemon().getName() + " go!"));
//            BattleView.getPlayerBar().setPokemon(p.getFrontPokemon());
//            BattleView.getPlayerBar().setVisible(true);
//            executor.executeNext(this::onFinish);

        }
        if(fighter instanceof NPC n) {
            n.setFront(other, terrain);
            BattleView.refreshSprites();
            BattleView.getOpponentBar().setPokemon(n.getFrontPokemon());
            BattleView.getFightButtons().resetFightButtons(getClass().getSimpleName());
            BattleView.getOpponentBar().refreshBar();
        }
        onFinish();
    }
}
