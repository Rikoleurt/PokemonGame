package Controller.Fight.Battle.Events;


import Model.Pokemon.Pokemon;

public class StatusEvent extends BattleEvent {

    Pokemon p1;
    Pokemon p2;

    public StatusEvent(Pokemon p1, Pokemon p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void execute(){
        if(p1.getStatus() != Model.Pokemon.PokemonEnum.Status.normal) p1.registerStatusEffect();
        if(p2.getStatus() != Model.Pokemon.PokemonEnum.Status.normal) p2.registerStatusEffect();
        onFinish();
    }
}
