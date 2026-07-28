package Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Controller.Fight.Battle.BattleExecutor;
import Model.GameState;
import Model.Inventory.Bag;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.SetUpMove;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.StaticObjects.TestVersion.MovesExample;
import View.Game.Battle.BattleView;
import com.google.gson.*;

public class SocketServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    private final Gson gson = new Gson();

    private final Trainer player = BattleView.getPlayer();
    private final Pokemon pokemon = player.getFrontPokemon();
    private final Trainer npc = BattleView.getAgent();
    private final Pokemon pokemon2 = npc.getFrontPokemon();
    private final BattleExecutor executor = BattleExecutor.getInstance();
    private static SocketServer instance;

    boolean lastOpponentActionInvalid = false;
    String lastOpponentInvalidReason = "";
    int turn;
    Boolean isPlayerFirst;

    public static SocketServer getInstance() {
        if (instance == null) {
            instance = new SocketServer();
        }
        return instance;
    }

    public void startAndTrain(int port, GameState gs) throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Java TCP server waits on port " + port + "...");
        clientSocket = serverSocket.accept();
        System.out.println("Client connected !");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        gs.launchTrainingFight();
    }

    public void start(int port, GameState gs) throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Java TCP server waits on port " + port + "...");
        clientSocket = serverSocket.accept();
        System.out.println("Client connected !");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
    }


    public synchronized void send(String json) throws IOException {
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

    public synchronized String readMessage() throws IOException {
        return in.readLine();
    }

    public String sendAndRead(String jsonState) throws IOException {
        System.out.println("Sending: " + jsonState);
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

    public String state(Trainer player, Trainer agent, int turn) {
        Pokemon p1 = player.getFrontPokemon();
        Pokemon p2 = getPokemonFromIndex(player, 1);
        Pokemon p3 = getPokemonFromIndex(player, 2);
        Pokemon p4 = getPokemonFromIndex(player, 3);
        Pokemon p5 = getPokemonFromIndex(player, 4);
        Pokemon p6 = getPokemonFromIndex(player, 5);

        Pokemon p7 = agent.getFrontPokemon();
        Pokemon p8 = getPokemonFromIndex(agent, 1);
        Pokemon p9 = getPokemonFromIndex(agent, 2);
        Pokemon p10 = getPokemonFromIndex(agent, 3);
        Pokemon p11 = getPokemonFromIndex(agent, 4);
        Pokemon p12 = getPokemonFromIndex(agent, 5);

        Bag playerBag = player.getBag();
        Bag opponentBag = agent.getBag();

        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonObject playerInfos = new JsonObject();
        playerInfos.addProperty("name", player.getName());

        JsonObject opponentInfos = new JsonObject();
        opponentInfos.addProperty("name", agent.getName());

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

        JsonArray playerBagItems = new JsonArray();
        JsonArray opponentBagItems = new JsonArray();

        JsonObject playerBagData = new JsonObject();
        JsonObject opponentBagData = new JsonObject();

        playerBagData.add("Items", playerBagItems);
        opponentBagData.add("Items", opponentBagItems);


        JsonObject first = new JsonObject();
        first.addProperty("name", starterName(agent));

        playerInfos.add("player_team", playerTeam);
        playerInfos.addProperty("healthy_pokemons", player.getHealthyPokemon());

        opponentInfos.add("opponent_team", opponentTeam);
        opponentInfos.addProperty("healthy_pokemons", agent.getHealthyPokemon());

        obj.add("player_infos", playerInfos);
        obj.add("opponent_infos", opponentInfos);
        obj.add("Priority", first);

        JsonObject actionFeedback = new JsonObject();
        actionFeedback.addProperty("opponent_invalid", lastOpponentActionInvalid);
        actionFeedback.addProperty("opponent_invalid_reason", lastOpponentInvalidReason);
        obj.add("action_feedback", actionFeedback);

        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private Pokemon getPokemonFromIndex(Trainer t, int index) {
        LinkedList<Pokemon> pokemons = t.getTeam();
        if(index < 0 || index >= pokemons.size()) {
            // System.out.println("No pokemon found at index : " + index);
            return null;
        }
        return pokemons.get(index);
    }
    private void addTeamInfos(Pokemon p, JsonArray team) {
        if(p != null){
            JsonObject pokemonData = new JsonObject();
            addPokemonInfos(p, pokemonData);
            team.add(pokemonData);
        }
    }

    private void addPokemonInfos(Pokemon p, JsonObject pokemonData) {
        pokemonData.addProperty("name", p.getName());

        pokemonData.addProperty("HP", p.getHP());
        pokemonData.addProperty("maxHP", p.getMaxHP());
        pokemonData.addProperty("hp_ratio", (double) p.getHP() / p.getMaxHP());
        pokemonData.addProperty("level", p.getLevel());
        pokemonData.addProperty("type", p.getType().toString());
        if (p.getType2() != null) pokemonData.addProperty("type2", p.getType2().toString());
        pokemonData.addProperty("status", p.getStatus().toString());

        JsonObject statsData = new JsonObject();
        statsData.addProperty("atk", p.getAtk());
        statsData.addProperty("def", p.getDef());
        statsData.addProperty("atkSpe", p.getAtkSpe());
        statsData.addProperty("defSpe", p.getDefSpe());
        statsData.addProperty("speed", p.getSpeed());

        JsonObject statisticsStages = new JsonObject();
        statisticsStages.addProperty("atk", p.getAtkRaise());
        statisticsStages.addProperty("def",p.getDefRaise());
        statisticsStages.addProperty("atkSpe", p.getAtkSpeRaise());
        statisticsStages.addProperty("defSpe", p.getDefSpeRaise());
        statisticsStages.addProperty("speed", p.getSpeedRaise());

        statsData.add("statisticsStages", statisticsStages);

        pokemonData.add("stats", statsData);

        JsonArray attacksData = new JsonArray();
        ArrayList<String> attacks = movesToList(p);
        ArrayList<Move> attacks1 = p.getAttacks();

        for (int i = 0; i < attacks.size(); i++) {
            JsonObject obj = new JsonObject();
            Move m1 = attacks1.get(i);

            obj.addProperty("slot", i);
            obj.addProperty("id", MovesExample.getIdByName(attacks.get(i)));
            obj.addProperty("name", attacks.get(i));
            obj.addProperty("type", m1.getType().toString());
            obj.addProperty("Mode", m1.getMode().toString());
            obj.addProperty("PP", m1.getPP());
            obj.addProperty("maxPP", m1.getMaxPP());
            obj.addProperty("isSTAB", m1.isStab(p));

            if (m1 instanceof Attack) {
                obj.addProperty("Power", ((Attack) m1).getPower());
                obj.addProperty("Precision", ((Attack) m1).getPrecision());
                if(((Attack) m1).getStatus() != null){
                    obj.addProperty("Status", ((Attack) m1).getStatus().toString());
                    obj.addProperty("ChanceOfApplyingStatus", ((Attack) m1).getStatusChance());
                }
                if(((Attack) m1).isTargetSelf()){
                    obj.addProperty("Target", ((Attack) m1).isTargetSelf() ? "self" : "opponent");

                    JsonArray stats = new JsonArray();
                    for(String stat : ((Attack) m1).getAllStat()){
                        JsonObject actualStat = new JsonObject();
                        actualStat.addProperty("Statistic", stat);
                        actualStat.addProperty("StageDelta", ((Attack) m1).getDeltaStage(stat));
                        stats.add(actualStat);
                    }

                    obj.add("StatisticsChange", stats);
                    obj.add("StatisticsChange", stats);
                }
            }

            if(m1 instanceof StatusAttack){
                obj.addProperty("Status", ((StatusAttack) m1).getStatus().toString());
                obj.addProperty("Precision", ((StatusAttack) m1).getPrecision());
            }

            if(m1 instanceof SetUpMove){
                obj.addProperty("Target", ((SetUpMove) m1).isTargetSelf() ? "self" : "opponent");

                JsonArray stats = new JsonArray();

                for(String stat : ((SetUpMove) m1).getAllStat()){
                    JsonObject actualStat = new JsonObject();
                    actualStat.addProperty("Statistic", stat);
                    actualStat.addProperty("StageDelta", ((SetUpMove) m1).getDeltaStage(stat));
                    stats.add(actualStat);
                }

                obj.add("StatisticsChange", stats);
            }
            attacksData.add(obj);
        }

        pokemonData.add("attacks", attacksData);
    }

    private ArrayList<String> movesToList(Pokemon p) {
        ArrayList<String> array = new ArrayList<>();
        if (p.getAttacks() == null) return array;

        for (Move m : p.getAttacks()) {
            if (m != null) {
                array.add(m.getName());
            }
        }
        return array;
    }

    public String refreshState() throws IOException{
        Trainer refreshedPlayer = BattleView.getPlayer();
        Pokemon refreshedOpponent = refreshedPlayer.getFrontPokemon();
        Trainer refreshedNPC = BattleView.getAgent();
        Pokemon refreshedSelf = refreshedNPC.getFrontPokemon();
        return state(refreshedPlayer, refreshedNPC, executor.getTurn());
    }

    public void step(Trainer agent) throws IOException {
        String protocolMessage = readMessage();
        String actionMessage = readMessage();
        System.out.println("Received: " + protocolMessage);
        System.out.println("Received: " + actionMessage);
        if (player.getHealthyPokemon() <= 0 || agent.getHealthyPokemon() <= 0) return;
        if (protocolMessage.startsWith("RESET")) {
            System.out.println("Reset, ignore");
        }
        if (protocolMessage.startsWith("DONE")) return;
        int actionIndex = Integer.parseInt(actionMessage);

        turn++;
    }

    public String getActionMessage(Trainer agent) throws IOException {
        String protocolMessage = readMessage();
        String actionMessage = readMessage();
        System.out.println("Received: " + protocolMessage);
        System.out.println("Received: " + actionMessage);

        if (player.getHealthyPokemon() <= 0 || agent.getHealthyPokemon() <= 0) return null;
        if (protocolMessage.startsWith("RESET")) {
            System.out.println("Reset, ignore");
        }
        if (protocolMessage.startsWith("DONE")){
            System.out.println("Done, ignore");
        }
        return actionMessage;
    }

    private void markOpponentInvalidAction(String reason) {
        lastOpponentActionInvalid = true;
        lastOpponentInvalidReason = reason;
    }

    private String starterName(Trainer agent) {
        if (isPlayerFirst == null) return player.getName();
        return isPlayerFirst ? player.getName() : agent.getName();
    }

    public Action resolveActionByActionIndex(int actionIndex) {
        if(actionIndex <= 3) return Action.Attack;
        else if (actionIndex == 4) return Action.Switch;
        else if (actionIndex == 5) return Action.Item;
        else {
            System.out.println("Unknown Action" + actionIndex);
            return null;
        }
    }
}
