package Model;

import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.Objects;

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

        Pokemon p1 = player.getFrontPokemon();
        Pokemon p2 = getPokemonFromIndex(player, 1);
        Pokemon p3 = getPokemonFromIndex(player, 2);
        Pokemon p4 = getPokemonFromIndex(player, 3);
        Pokemon p5 = getPokemonFromIndex(player, 4);
        Pokemon p6 = getPokemonFromIndex(player, 5);

        Pokemon p7 = opponent.getFrontPokemon();
        Pokemon p8 = getPokemonFromIndex(opponent, 1);
        Pokemon p9 = getPokemonFromIndex(opponent, 2);
        Pokemon p10 = getPokemonFromIndex(opponent, 3);
        Pokemon p11 = getPokemonFromIndex(opponent, 4);
        Pokemon p12 = getPokemonFromIndex(opponent, 5);

        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonObject playerInfos = new JsonObject();
        playerInfos.addProperty("name", player.getName());

        JsonObject opponentInfos = new JsonObject();
        opponentInfos.addProperty("name", opponent.getName());

        JsonArray playerTeam = new JsonArray();
        addTeamInfos(p1, playerTeam);
        addTeamInfos(p2, playerTeam);
        addTeamInfos(p3, playerTeam);
        addTeamInfos(p4, playerTeam);
        addTeamInfos(p5, playerTeam);
        addTeamInfos(p6, playerTeam);

        JsonArray opponentTeam = new JsonArray();
        addTeamInfos(p7, opponentTeam);
        addTeamInfos(p8, opponentTeam);
        addTeamInfos(p9, opponentTeam);
        addTeamInfos(p10, opponentTeam);
        addTeamInfos(p11, opponentTeam);
        addTeamInfos(p12, opponentTeam);

        playerInfos.add("player_team", playerTeam);
        opponentInfos.add("opponent_team", opponentTeam);
        obj.add("player_infos", playerInfos);
        obj.add("opponent_infos", opponentInfos);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
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

    private Pokemon getPokemonFromIndex(Trainer t, int index){
        LinkedList<Pokemon> pokemons = t.getTeam();
        try {
            return pokemons.get(index);
        } catch (Exception e){
            System.out.println("No pokemon found at index : " + index + ", " + e.getMessage());
            return null;
        }
    }

    private void addTeamInfos(Pokemon p, JsonArray playerTeam){
        try {
            JsonObject pokemonData = new JsonObject();
            addPokemonInfos(p, pokemonData);
            playerTeam.add(pokemonData);
        } catch (Exception e){
            System.out.println("This pokemon can't be added to the team");
        }
    }

    private void addPokemonInfos(Pokemon p, JsonObject pokemonData){
        pokemonData.addProperty("name", p.getName());
        pokemonData.addProperty("HP", p.getHP());
        pokemonData.addProperty("maxHP", p.getMaxHP());
    }
}
