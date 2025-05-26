package Controller.Turns;

import Model.Pokemon.Pokemon;

public class Turn {

    private boolean playerTurn;

    public Turn(Pokemon player, Pokemon enemy) {
        this.playerTurn = player.getSpeed() > enemy.getSpeed();
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void toggleTurn() {
        playerTurn = !playerTurn;
    }

    public void reset(Pokemon player, Pokemon enemy) {
        this.playerTurn = player.getSpeed() > enemy.getSpeed();
    }
}

