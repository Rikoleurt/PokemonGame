package Controller.Fight.Battle.Events.ComputeEvents;


import Controller.Fight.Battle.Events.BattleEvent;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;

public class StatusEvent extends BattleEvent {

    Pokemon p1;
    Pokemon p2;

    public StatusEvent(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void execute(){
        if(p1.getStatus() != Status.normal) p1.registerStatusEffect();
        if(p2.getStatus() != Status.normal) p2.registerStatusEffect();
        onFinish();
    }
}
