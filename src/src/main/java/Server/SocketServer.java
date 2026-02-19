package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Controller.Fight.Battle.BattleExecutor;
import Model.GameState;
import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.NPC;
import Model.Person.Player;
import Model.Person.Trainer;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import View.Game.Battle.BattleView;
import com.google.gson.*;

public class SocketServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    private final Gson gson = new Gson();

    private final Player player = BattleView.getPlayer();
    private final Pokemon pokemon = player.getFrontPokemon();
    private final NPC npc = BattleView.getNpc();
    private final Pokemon pokemon2 = npc.getFrontPokemon();
    private final BattleExecutor executor = BattleExecutor.getInstance();
    private static SocketServer instance;


    public static SocketServer getInstance() {
        if (instance == null) {
            instance = new SocketServer();
        }
        return instance;
    }

    public void start(int port, GameState gs) throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Java TCP server waits on port " + port + "...");
        clientSocket = serverSocket.accept();
        System.out.println("Client connected !");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        String initialState = gs.state();
        sendState(initialState);
        gs.launchFight();
    }


    public synchronized void sendState(String json) throws IOException {
        try {
            if(out != null) {
                System.out.println("sending this : " + json);
                out.write(json + "\n");
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }


    public String sendStateWaitForAction(String jsonState) throws IOException {
        out.write(jsonState + "\n");
        out.flush();
        return in.readLine(); // wait for an answer
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    private JsonObject getSelfPokemonInfos(Pokemon pokemon) {
        JsonObject jsonPokemon = new JsonObject();

        jsonPokemon.addProperty("name", pokemon.getName());
        jsonPokemon.addProperty("level", pokemon.getLevel());
        jsonPokemon.addProperty("HP", pokemon.getHP());
        jsonPokemon.addProperty("maxHP", pokemon.getMaxHP());
        jsonPokemon.addProperty("type", pokemon.getType().toString());
        jsonPokemon.addProperty("status", pokemon.getStatus() != null ? pokemon.getStatus().toString() : "none");

        JsonArray jsonMoves = new JsonArray();
        ArrayList<Move> moves = pokemon.getAttacks();
        if (moves != null) {
            for (int i = 0; i < Math.min(4, moves.size()); i++) {
                Move move = moves.get(i);
                if (move != null) {
                    JsonObject jsonMove = new JsonObject();
                    jsonMove.addProperty("name", move.getName());
                    jsonMove.addProperty("type", move.getType().toString());
                    jsonMove.addProperty("mode", move.getMode().toString());
                    jsonMove.addProperty("PP", move.getPP());
                    jsonMoves.add(jsonMove);
                }
            }
        }
        jsonPokemon.add("moves", jsonMoves);
        return jsonPokemon;
    }

    private JsonObject getOpponentPokemonInfos(Pokemon opponent) {
        JsonObject jsonPokemon = new JsonObject();
        jsonPokemon.addProperty("name", opponent.getName());
        jsonPokemon.addProperty("level", opponent.getLevel());
        jsonPokemon.addProperty("HP", opponent.getHP());
        jsonPokemon.addProperty("maxHP", opponent.getMaxHP());
        jsonPokemon.addProperty("type", opponent.getType().toString());
        jsonPokemon.addProperty("status", opponent.getStatus() != null ? opponent.getStatus().toString() : "NONE");
        return jsonPokemon;
    }

    private String jsonGameState(GameState gameState) {
        Trainer p = gameState.getPlayer();
        Trainer o = gameState.getOpponent();

        Pokemon p1 = p.getFrontPokemon();
        Pokemon p2 = o.getFrontPokemon();

        int turn = gameState.getTurn();

        JsonObject pState = new JsonObject();
        pState.addProperty("name", p.getName());
        pState.addProperty("pokemonNb", p.getHealthyPokemon());

        JsonObject oState = new JsonObject();
        oState.addProperty("name", o.getName());
        oState.addProperty("pokemonNb", o.getHealthyPokemon());

        JsonObject pokemonState = new JsonObject();
        pokemonState.add("player", pState);
        pokemonState.add("enemy", oState);
        pokemonState.add("player_pokemon", getSelfPokemonInfos(p1));
        pokemonState.add("enemy_pokemon", getOpponentPokemonInfos(p2));
        pokemonState.addProperty("turn", turn);
        System.out.println(gson.toJson(pokemonState));
        return gson.toJson(pokemonState);
    }

    private String jsonGameState(Player p, Pokemon opponent, NPC n, Pokemon self, int turn) {
        JsonObject nState = new JsonObject();
        nState.addProperty("name", n.getName());
        nState.addProperty("pokemonNb", n.getHealthyPokemon());

        JsonObject pState = new JsonObject();
        pState.addProperty("name", p.getName());
        pState.addProperty("pokemonNb", p.getHealthyPokemon());

        JsonObject pokemonState = new JsonObject();

        pokemonState.add("npc", nState);
        pokemonState.add("player", pState);
        pokemonState.add("opponent", getOpponentPokemonInfos(opponent));
        pokemonState.add("self", getSelfPokemonInfos(self));

        JsonArray actions = new JsonArray();
        actions.add(Action.Switch.toString());
        actions.add(Action.Attack.toString());
        actions.add(Action.Item.toString());

        JsonObject choices = new JsonObject();
        // Moves
        ArrayList<Move> moves = self.getAttacks();
        JsonArray attack = new JsonArray();
        int m = moves != null ? Math.min(4, moves.size()) : 0;
        for (int i = 0; i < m; i++) attack.add(moves.get(i).getName());
        if (!attack.isEmpty()) {
            choices.add("Attack", attack);
        }

        // Switches
        JsonArray switches = new JsonArray();
        LinkedList<Pokemon> pokemons = n.getTeam();
        int teamSize = n.getTeam().size();
        for (int i = 1; i < teamSize; i++) {
             switches.add(pokemons.get(i).getName() + " " + i);
        }
        if (!switches.isEmpty()) {
            choices.add("Switch", switches);
        }

        // Items
        JsonArray items = new JsonArray();
        for (Item item : n.getBag().getInventory().keySet()) items.add(item.getName());
        if (!items.isEmpty()) {
            choices.add("Item", items);
        }

        pokemonState.add("actions", actions);
        pokemonState.add("action_choices", choices);
        pokemonState.addProperty("turn", turn);
        System.out.println(gsonPretty.toJson(pokemonState));
        return gson.toJson(pokemonState);
    }

    public String refreshState() throws IOException{
        Player refreshedPlayer = BattleView.getPlayer();
        Pokemon refreshedOpponent = refreshedPlayer.getFrontPokemon();
        NPC refreshedNPC = BattleView.getNpc();
        Pokemon refreshedSelf = refreshedNPC.getFrontPokemon();
        return jsonGameState(refreshedPlayer, refreshedOpponent, refreshedNPC, refreshedSelf, executor.getTurn());
    }

    public BufferedWriter getBufferedWriter() {
        return out;
    }
}
