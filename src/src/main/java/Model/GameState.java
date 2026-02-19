package Model;

import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.StaticObjects.MovesExample;
import Server.SocketServer;
import View.Training.Console.View.BattleConsole;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameState {
    Trainer player;
    Trainer opponent;
    int turn;

    BattleConsole console = BattleConsole.getInstance();
    SocketServer server = SocketServer.getInstance();

    public GameState(Trainer player, Trainer opponent, int turn) {
        this.player = player;
        this.opponent = opponent;
        this.turn = turn;

    }

    public String state(){
        Pokemon p1 = player.getFrontPokemon();
//        Pokemon p2 = getPokemonFromIndex(player, 1);
//        Pokemon p3 = getPokemonFromIndex(player, 2);
//        Pokemon p4 = getPokemonFromIndex(player, 3);
//        Pokemon p5 = getPokemonFromIndex(player, 4);
//        Pokemon p6 = getPokemonFromIndex(player, 5);

        Pokemon p7 = opponent.getFrontPokemon();
//        Pokemon p8 = getPokemonFromIndex(opponent, 1);
//        Pokemon p9 = getPokemonFromIndex(opponent, 2);
//        Pokemon p10 = getPokemonFromIndex(opponent, 3);
//        Pokemon p11 = getPokemonFromIndex(opponent, 4);
//        Pokemon p12 = getPokemonFromIndex(opponent, 5);

        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonObject playerInfos = new JsonObject();
        playerInfos.addProperty("name", player.getName());

        JsonObject opponentInfos = new JsonObject();
        opponentInfos.addProperty("name", opponent.getName());

        JsonArray playerTeam = new JsonArray();
        addTeamInfos(p1, playerTeam);
//        addTeamInfos(p2, playerTeam);
//        addTeamInfos(p3, playerTeam);
//        addTeamInfos(p4, playerTeam);
//        addTeamInfos(p5, playerTeam);
//        addTeamInfos(p6, playerTeam);

        JsonArray opponentTeam = new JsonArray();
        addTeamInfos(p7, opponentTeam);
//        addTeamInfos(p8, opponentTeam);
//        addTeamInfos(p9, opponentTeam);
//        addTeamInfos(p10, opponentTeam);
//        addTeamInfos(p11, opponentTeam);
//        addTeamInfos(p12, opponentTeam);

        JsonObject first = new JsonObject();
        first.addProperty("name", is_player_first());
        playerInfos.add("player_team", playerTeam);
        playerInfos.addProperty("healthy_pokemons", player.getHealthyPokemon());
        opponentInfos.add("opponent_team", opponentTeam);
        opponentInfos.addProperty("healthy_pokemons", opponent.getHealthyPokemon());
        obj.add("player_infos", playerInfos);
        obj.add("opponent_infos", opponentInfos);
        obj.add("Priority", first);
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public String pretty_state(){
        Pokemon p1 = player.getFrontPokemon();
//        Pokemon p2 = getPokemonFromIndex(player, 1);
//        Pokemon p3 = getPokemonFromIndex(player, 2);
//        Pokemon p4 = getPokemonFromIndex(player, 3);
//        Pokemon p5 = getPokemonFromIndex(player, 4);
//        Pokemon p6 = getPokemonFromIndex(player, 5);

        Pokemon p7 = opponent.getFrontPokemon();
//        Pokemon p8 = getPokemonFromIndex(opponent, 1);
//        Pokemon p9 = getPokemonFromIndex(opponent, 2);
//        Pokemon p10 = getPokemonFromIndex(opponent, 3);
//        Pokemon p11 = getPokemonFromIndex(opponent, 4);
//        Pokemon p12 = getPokemonFromIndex(opponent, 5);

        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonObject playerInfos = new JsonObject();
        playerInfos.addProperty("name", player.getName());

        JsonObject opponentInfos = new JsonObject();
        opponentInfos.addProperty("name", opponent.getName());

        JsonArray playerTeam = new JsonArray();
        addTeamInfos(p1, playerTeam);
//        addTeamInfos(p2, playerTeam);
//        addTeamInfos(p3, playerTeam);
//        addTeamInfos(p4, playerTeam);
//        addTeamInfos(p5, playerTeam);
//        addTeamInfos(p6, playerTeam);

        JsonArray opponentTeam = new JsonArray();
        addTeamInfos(p7, opponentTeam);
//        addTeamInfos(p8, opponentTeam);
//        addTeamInfos(p9, opponentTeam);
//        addTeamInfos(p10, opponentTeam);
//        addTeamInfos(p11, opponentTeam);
//        addTeamInfos(p12, opponentTeam);

        JsonObject first = new JsonObject();
        first.addProperty("name", is_player_first());
        playerInfos.add("player_team", playerTeam);
        playerInfos.addProperty("healthy_pokemons", player.getHealthyPokemon());
        opponentInfos.add("opponent_team", opponentTeam);
        opponentInfos.addProperty("healthy_pokemons", opponent.getHealthyPokemon());
        obj.add("player_infos", playerInfos);
        obj.add("opponent_infos", opponentInfos);
        obj.add("Priority", first);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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
    public void launchFight() throws IOException {

        String opponentName = opponent.getName();
        String playerName = player.getName();

        Pokemon p = player.getFrontPokemon();
        Pokemon op = opponent.getFrontPokemon();

        String pName = p.getName();
        String opName = op.getName();

        System.out.println(opponentName + " wants to battle!");
        System.out.println(pName + ", go!");
        System.out.println(opponentName + " sends " + opName + "!");
        fightLoop();
    }

    private void fightLoop() throws IOException {
        Pokemon p = player.getFrontPokemon();
        Pokemon op = opponent.getFrontPokemon();
        while(opponent.getHealthyPokemon() > 0 && player.getHealthyPokemon() > 0) {
            Move m1 = player.getFrontPokemon().chooseMove();
            Move m2 = opponent.getFrontPokemon().chooseMove();
            if(is_player_first() && !p.isKO()){
                p.attack(op, m1);
                if(!op.isKO()) op.attack(p, m2);

            } else if (is_player_first() && !op.isKO()){
                op.attack(p, m2);
                if(!p.isKO()) p.attack(op, m1);
            }
            turn++;
            console.log(pretty_state());
            server.sendState(state());
        }
    }

    private void fightLoop2() throws IOException {
        while(opponent.getHealthyPokemon() > 0 && player.getHealthyPokemon() > 0) {

            String actionLine = server.sendStateWaitForAction(state());
            int moveIndex;
            try {
                moveIndex = Integer.parseInt(actionLine.trim());
            } catch (Exception e) {
                moveIndex = 0;
            }

            Pokemon p = player.getFrontPokemon();
            Pokemon op = opponent.getFrontPokemon();

            Move m1 = p.chooseMove();

            Move m2 = null;
            if (op.getAttacks() != null && !op.getAttacks().isEmpty()) {
                int maxIdx = Math.min(3, op.getAttacks().size() - 1);
                int idx = Math.max(0, Math.min(moveIndex, maxIdx));
                m2 = op.getAttacks().get(idx);
            }
            if (m2 == null) m2 = op.chooseMove(); // fallback sécurité

            if(is_player_first() && !p.isKO()){
                p.attack(op, m1);
                if(!op.isKO()) op.attack(p, m2);
            } else if (!is_player_first() && !op.isKO()){
                op.attack(p, m2);
                if(!p.isKO()) p.attack(op, m1);
            }

            turn++;
            console.log(pretty_state());
        }

        server.sendState(state());
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
            System.out.println("This Pokémon can't be added to the team");
        }
    }

    private void addPokemonInfos(Pokemon p, JsonObject pokemonData){
        pokemonData.addProperty("name", p.getName());
        pokemonData.addProperty("HP", p.getHP());
        pokemonData.addProperty("maxHP", p.getMaxHP());
        pokemonData.addProperty("level", p.getLevel());
        pokemonData.addProperty("type", p.getType().toString());
        pokemonData.addProperty("status", p.getStatus().toString());

        JsonArray attacksData = new JsonArray();
        ArrayList<String> attacks = movesToList(p);
        for(int i = 0; i < attacks.size(); i++){
            JsonObject obj = new JsonObject();
            obj.addProperty("slot", i);
            obj.addProperty("id", MovesExample.getIdByName(attacks.get(i)));
            obj.addProperty("name", attacks.get(i));
            attacksData.add(obj);
        }

        pokemonData.add("attacks", attacksData);
    }

    public boolean is_player_first(){
        return computeOrder();
    }

    private boolean computeOrder(){
        Action playerAction = player.getAction();
        Action opponentAction = opponent.getAction();

        Pokemon playerPkmn = player.getFrontPokemon();
        Pokemon foe = opponent.getFrontPokemon();

        int playerPriority = priorityOf(playerAction);
        int npcPriority = priorityOf(opponentAction);

        if (playerPriority > npcPriority) return true;
        if (playerPriority < npcPriority) return false;

        int playerSpeed = playerPkmn.getEffectiveSpeed();
        int npcSpeed = foe.getEffectiveSpeed();

        if (playerSpeed > npcSpeed) {
//            System.out.println("Player speed is greater than npc speed");
            return true;
        } else if (playerSpeed < npcSpeed) {
//            System.out.println("Player speed is less than npc speed");
            return false;
        }

        return new Random().nextBoolean();
    }

    private int priorityOf(Action action) {
        if (action == Action.Switch) return 6;
        if (action == Action.Item) return 6;
        if (action == Action.Run) return 6;
        return 0;
    }

    private ArrayList<String> movesToList(Pokemon pk) {
        ArrayList<String> array = new ArrayList<>();
        if (pk.getAttacks() == null) return array;
        for (Move m : pk.getAttacks()) {
            if (m != null){
                array.add(m.getName());
            }
        }
        return array;
    }
}
