package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Model.Inventory.Category;
import Model.Inventory.Items.Item;
import Model.Person.Player;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import com.google.gson.*;

public class SocketServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private final Gson gson = new Gson();
    private Pokemon pokemon;
    private Pokemon pokemon2;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Java TCP server waits on port " + port + "...");
        clientSocket = serverSocket.accept();
        System.out.println("Client connected !");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
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

    private JsonObject getSelfInfos(Pokemon pokemon) {
        JsonObject jsonPokemon = new JsonObject();
        jsonPokemon.addProperty("name", pokemon.getName());
        jsonPokemon.addProperty("level", pokemon.getLevel());
        jsonPokemon.addProperty("HP", pokemon.getHP());
        jsonPokemon.addProperty("maxHP", pokemon.getMaxHP());
        jsonPokemon.addProperty("type", pokemon.getType().toString());
        jsonPokemon.addProperty("status", pokemon.getStatus() != null ? pokemon.getStatus().toString() : "NONE");

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

    private JsonObject getOpponentInfos(Pokemon opponent) {
        JsonObject jsonPokemon = new JsonObject();
        jsonPokemon.addProperty("name", opponent.getName());
        jsonPokemon.addProperty("level", opponent.getLevel());
        jsonPokemon.addProperty("HP", opponent.getHP());
        jsonPokemon.addProperty("maxHP", opponent.getMaxHP());
        jsonPokemon.addProperty("type", opponent.getType().toString());
        jsonPokemon.addProperty("status", opponent.getStatus() != null ? opponent.getStatus().toString() : "NONE");
        return jsonPokemon;
    }


    private String buildGameStateInJson(Player p, Pokemon self, Pokemon opponent, int turn) {
        JsonObject gameState = new JsonObject();
        gameState.add("self", getSelfInfos(self));
        gameState.add("opponent", getOpponentInfos(opponent));

        JsonObject choices = new JsonObject();
        JsonArray availableChoices = new JsonArray();

        ArrayList<Move> moves = self.getAttacks();
        JsonArray attack = new JsonArray();

        int m = moves != null ? Math.min(4, moves.size()) : 0;

        for (int i = 0; i < m; i++) attack.add("MOVE_" + i);
        if (!attack.isEmpty()) {
            choices.add("ATTACK", attack);
            availableChoices.add("ATTACK");
        }
        System.out.println(attack.getAsString());

        JsonArray sw = new JsonArray();
        int teamSize = p.getTeam().size();
        for (int i = 0; i < teamSize; i++) {
             sw.add("SLOT_" + i);
        }
        System.out.println(sw.getAsString());
        if (!sw.isEmpty()) {
            choices.add("SWITCH", sw);
            availableChoices.add("SWITCH");
        }

        JsonArray items = new JsonArray();
        for (Item item : p.getBag().getInventory().keySet()) items.add(item.getName());
        if (!items.isEmpty()) {
            choices.add("ITEM", items);
            availableChoices.add("ITEM");
        }
        System.out.println(items.getAsString());

        gameState.add("available_choices", availableChoices);
        gameState.add("choices", choices);
        gameState.addProperty("turn", turn);
        System.out.println(gson.toJson(gameState));
        return gson.toJson(gameState);
    }
}
