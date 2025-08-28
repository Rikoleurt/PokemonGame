package Controller.Fight.Battle.Events;

import Model.Person.NPC;
import Model.Person.Player;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleButtons;

public class FaintEvent extends BattleEvent {

    Player player;
    NPC npc;
    Pokemon pokemon;
    BattleButtons battleButtons;

    public FaintEvent(Player player, NPC npc, Pokemon pokemon, BattleButtons battleButtons) {
        this.player = player;
        this.npc = npc;
        this.pokemon = pokemon;
        this.battleButtons = battleButtons;
    }

    @Override
    public void execute(){
        if(player.getTeam().contains(pokemon)){
            battleButtons.handlePlayerPokemonKO(pokemon);
        }
        if(npc.getTeam().contains(pokemon)){
            battleButtons.handleNpcPokemonKO(pokemon);
        }
        onFinish();
    }
}
