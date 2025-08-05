package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import com.google.gson.*;

public class SocketServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson = new Gson();
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

    private JsonObject getInfo(Pokemon pokemon) {
        JsonObject jsonPokemon = new JsonObject();
        jsonPokemon.addProperty("name", pokemon.getName());
        jsonPokemon.addProperty("level", pokemon.getLevel());
        jsonPokemon.addProperty("HP", pokemon.getHP());
        jsonPokemon.addProperty("maxHP", pokemon.getMaxHP());
        jsonPokemon.addProperty("type", pokemon.getType().toString());

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

    private String buildGameStateJson(Pokemon self, Pokemon opponent, int turn) {
        JsonObject gameState = new JsonObject();
        gameState.add("self", getInfo(self));
        gameState.add("opponent", getInfo(opponent));

        JsonArray actions = new JsonArray();
        ArrayList<Move> moves = self.getAttacks();
        for (int i = 0; i < Math.min(4, moves != null ? moves.size() : 0); i++) {
            actions.add("MOVE_" + i);
        }

        gameState.add("available_actions", actions);
        gameState.addProperty("turn", turn);

        return gson.toJson(gameState);
    }
}
