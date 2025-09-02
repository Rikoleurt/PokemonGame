package Controller.Fight.Battle.Events.ActionEvents.Switch.PlayerSwitch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.ActionEvents.Switch.FoeSwitch.FoeSwitchEvent;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.GameEvents.EndTurn;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;
import View.Game.Switch.SwitchAfterAskView;


public class AskPlayerSwitchEvent extends BattleEvent {
    Player player;
    NPC npc;
    Pokemon otherNpcPokemon;
    BattleExecutor executor;
    BattleButtons battleButtons;
    static boolean isSwitching = false;

    public AskPlayerSwitchEvent(Player player, BattleExecutor executor, NPC npc, Pokemon otherNpcPokemon, BattleButtons battleButtons) {
        this.player = player;
        this.executor = executor;
        this.npc = npc;
        this.otherNpcPokemon = otherNpcPokemon;
        this.battleButtons = battleButtons;
    }

    public void execute() {
        executor.addEvent(new MessageEvent(npc.getName() + " will send " + otherNpcPokemon.getName() + "."));
        MessageEvent question = new MessageEvent("Would you like to recall " + player.getFrontPokemon().getName() + "?");
        executor.addEvent(question);
        executor.executeEvents(() -> {
            SwitchAfterAskView sv = new SwitchAfterAskView(player, npc, BattleView.getTextBubble(), battleButtons, otherNpcPokemon, () -> {
                // Cancel button pressed
                SceneManager.switchStageTo(SceneManager.getFightView());
                executor.addEvent(new FoeSwitchEvent(npc, otherNpcPokemon, BattleView.getTerrain()));
                executor.executeEvents(() -> {
                    executor.addEvent(new EndTurn( executor));
                    executor.executeEvents(null);
                });
            });
            BattleView.askClosedQuestion(question, SceneManager.getStage(), () -> {
                        SceneManager.switchStageTo(sv);
                        isSwitching = true;
                    }, () -> {
                        executor.addEvent(new FoeSwitchEvent(npc, otherNpcPokemon, BattleView.getTerrain()));
                        executor.executeEvents(() -> {
                            executor.addEvent(new EndTurn(executor));
                            executor.executeEvents(null);
                        });
                    });
        });
    }

    public static boolean isSwitching() {
        return isSwitching;
    }
    public static void setIsSwitching(boolean value) {
        isSwitching = value;
    }
}
