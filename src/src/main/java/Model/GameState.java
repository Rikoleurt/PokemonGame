package Model;

import Model.Person.Trainer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GameState {
    Trainer player;
    Trainer opponent;
    int turn;

    public GameState(Trainer player, Trainer opponent, int turn) {
        this.player = player;
        this.opponent = opponent;
        this.turn = turn;
    }

    public String state(){
        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonArray playerInfos = new JsonArray();
        playerInfos.add(player.getName());
        playerInfos.add(player.getTeam().getFirst().getName());

        JsonArray opponentInfos = new JsonArray();
        opponentInfos.add(opponent.getName());
        opponentInfos.add(opponent.getTeam().getFirst().getName());
        obj.add("playerInfos", playerInfos);
        obj.add("opponentInfos", opponentInfos);
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public int getTurn() {
        return turn;
    }
    public Trainer getOpponent() {
        return opponent;
    }
    public Trainer getPlayer() {
        return player;
    }
}
