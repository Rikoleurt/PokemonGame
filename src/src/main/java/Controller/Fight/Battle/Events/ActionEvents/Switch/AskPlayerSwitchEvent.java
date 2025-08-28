package Controller.Fight.Battle.Events.ActionEvents.Switch;

import Controller.Fight.Battle.BattleExecutor;
import Controller.Fight.Battle.Events.BattleEvent;
import Controller.Fight.Battle.Events.UIEvents.EndTurn;
import Controller.Fight.Battle.Events.UIEvents.MessageEvent;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;
import View.Game.Battle.BattleView;
import View.Game.SceneManager;
import View.Game.Switch.SwitchAfterAskView;
import View.Game.Switch.SwitchKOView;
import View.Game.Switch.SwitchView;


public class AskPlayerSwitchEvent extends BattleEvent {
    Player player;
    NPC npc;
    Pokemon otherNpcPokemon;
    BattleExecutor executor;
    BattleButtons battleButtons;

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
        executor.executeNext(() -> {
            SwitchAfterAskView sv = new SwitchAfterAskView(player, npc, BattleView.getTextBubble(), battleButtons, otherNpcPokemon, () -> {
                // Cancel button pressed
                SceneManager.switchStageTo(SceneManager.getFightView());
                executor.addEvent(new FoeSwitchEvent(npc, otherNpcPokemon, BattleView.getTerrain(), executor, battleButtons));
                executor.executeNext(()-> {
                    executor.addEvent(new EndTurn(battleButtons, executor));
                    executor.executeNext(null);
                });
            });
            BattleView.askClosedQuestion(question, SceneManager.getStage(),
                    () -> SceneManager.switchStageTo(sv),
                    () -> {
                        executor.addEvent(new FoeSwitchEvent(npc, otherNpcPokemon, BattleView.getTerrain(), executor, battleButtons));
                        executor.executeNext(()-> {
                            executor.addEvent(new EndTurn(battleButtons, executor));
                            executor.executeNext(null);
                        });
                    }
            );
        });
    }
}
