package Model;

import Model.Inventory.Items.Item;
import Model.Person.Action;
import Model.Person.Trainer;
import Model.Pokemon.Attacks.Attack;
import Model.Pokemon.Attacks.SetUpMove;
import Model.Pokemon.Attacks.StatusAttack;
import Model.Pokemon.Move;
import Model.Pokemon.Pokemon;
import Model.Pokemon.PokemonEnum.Status;
import Model.StaticObjects.TestVersion.MovesExample;
import Server.SocketServer;
import Utils.SeedManager;
import View.Training.Console.View.BattleConsole;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameState {
    Trainer player;
    Trainer opponent;
    int turn;
    Boolean isPlayerFirst = null;

    boolean lastOpponentActionInvalid = false;
    String lastOpponentInvalidReason = "";

    BattleConsole console = BattleConsole.getInstance();
    SocketServer server = SocketServer.getInstance();

    public GameState(Trainer player, Trainer opponent, int turn) {
        this.player = player;
        this.opponent = opponent;
        this.turn = turn;
    }

    public String state() {
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

        JsonObject first = new JsonObject();
        first.addProperty("name", starterName());

        playerInfos.add("player_team", playerTeam);
        playerInfos.addProperty("healthy_pokemons", player.getHealthyPokemon());

        opponentInfos.add("opponent_team", opponentTeam);
        opponentInfos.addProperty("healthy_pokemons", opponent.getHealthyPokemon());

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

    public String pretty_state() {
        Pokemon p1 = player.getFrontPokemon();
        Pokemon p7 = opponent.getFrontPokemon();
        Pokemon p8 = getPokemonFromIndex(opponent, 1);

        JsonObject obj = new JsonObject();
        obj.addProperty("turn", turn);

        JsonObject playerInfos = new JsonObject();
        playerInfos.addProperty("name", player.getName());

        JsonObject opponentInfos = new JsonObject();
        opponentInfos.addProperty("name", opponent.getName());

        JsonArray playerTeam = new JsonArray();
        addTeamInfos(p1, playerTeam);

        JsonArray opponentTeam = new JsonArray();
        addTeamInfos(p7, opponentTeam);
        addTeamInfos(p8, opponentTeam);

        JsonObject first = new JsonObject();
        first.addProperty("name", starterName());

        playerInfos.add("player_team", playerTeam);
        playerInfos.addProperty("healthy_pokemons", player.getHealthyPokemon());

        opponentInfos.add("opponent_team", opponentTeam);
        opponentInfos.addProperty("healthy_pokemons", opponent.getHealthyPokemon());

        obj.add("player_infos", playerInfos);
        obj.add("opponent_infos", opponentInfos);
        obj.add("Priority", first);

        JsonObject actionFeedback = new JsonObject();
        actionFeedback.addProperty("opponent_invalid", lastOpponentActionInvalid);
        actionFeedback.addProperty("opponent_invalid_reason", lastOpponentInvalidReason);
        obj.add("action_feedback", actionFeedback);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

    public void launchFight() throws IOException {
        System.out.println("---------------- Fight begins ----------------");
        fightLoop();
    }

    private void fightLoop() throws IOException {
        int episodeCount = 0;
        String pendingMsg = null;

        while (true) {
            String msg;

            if (pendingMsg != null) {
                msg = pendingMsg;
                pendingMsg = null;
            } else {
                msg = server.readMessage();
            }

            System.out.println("Received: " + msg);

            if (msg == null) {
                System.out.println("Client disconnected.");
                break;
            }

            msg = msg.trim();

            if ("DONE".equalsIgnoreCase(msg)) {
                System.out.println("Training complete.");
                break;
            }

            if (!msg.startsWith("RESET")) {
                System.out.println("Protocol error: expected RESET, got: " + msg);
                continue;
            }

            long seed = SeedManager.getSeed();
            String[] parts = msg.split("\\s+");
            if (parts.length == 2) {
                try {
                    seed = Long.parseLong(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid seed format: " + parts[1] + ", using default seed 123");
                }
            }

            resetState();
            turn = 0;

            System.out.println("Episode: " + episodeCount + " with seed " + seed);
            server.send(state());

            while (true) {
                if (player.getHealthyPokemon() <= 0 || opponent.getHealthyPokemon() <= 0) {
                    break;
                }

                String actionMsg = server.readMessage();

                if (actionMsg == null) {
                    System.out.println("Client disconnected (2nd while loop).");
                    return;
                }

                actionMsg = actionMsg.trim();

                if ("DONE".equalsIgnoreCase(actionMsg)) {
                    System.out.println("Training complete");
                    return;
                }

                if (actionMsg.startsWith("RESET")) {
                    pendingMsg = actionMsg;
                    break;
                }

                int actionIndex = Integer.parseInt(actionMsg);

                step(actionIndex);
                turn++;
                server.send(state());
            }

            episodeCount++;
        }

        System.out.println("Total episodes done: " + episodeCount);
    }

    private void step(int actionIndex) {
        clearLastActionFlags();

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon opponentPokemon = opponent.getFrontPokemon();

        if (playerPokemon == null || opponentPokemon == null) return;
        if (playerPokemon.isKO() || opponentPokemon.isKO()) return;

        Action playerAction = player.makeChoiceAction();
        player.setAction(playerAction);

        Move playerMove = null;
        Pokemon playerSwitchTarget = null;
        Item playerItem = null;

        switch (playerAction) {
            case Attack -> playerMove = playerPokemon.chooseMove();
            case Switch -> playerSwitchTarget = player.chooseSwitchTarget();
            case Item -> playerItem = player.itemChoice(playerPokemon);
            default -> {
            }
        }

        // 0..3 = attaques, 4 = switch, 5 = item
        Action opponentAction = decodeOpponentAction(actionIndex);
        opponent.setAction(opponentAction);

        Move opponentMove = null;
        Pokemon opponentSwitchTarget = null;
        Item opponentItem = null;

        switch (opponentAction) {
            case Attack -> opponentMove = chooseMoveFromActionIndex(opponentPokemon, actionIndex);
            case Switch -> opponentSwitchTarget = opponent.chooseSwitchTarget();
            case Item -> opponentItem = opponent.itemChoice(opponentPokemon);
            default -> {
            }
        }

        isPlayerFirst = is_player_first();

        if (isPlayerFirst) {
            executeTrainerAction(player, opponent, playerAction, playerMove, playerSwitchTarget, playerItem, false);
            if (opponent.getFrontPokemon() != null && !opponent.getFrontPokemon().isKO()) {
                executeTrainerAction(opponent, player, opponentAction, opponentMove, opponentSwitchTarget, opponentItem, true);
            }
        } else {
            executeTrainerAction(opponent, player, opponentAction, opponentMove, opponentSwitchTarget, opponentItem, true);
            if (player.getFrontPokemon() != null && !player.getFrontPokemon().isKO()) {
                executeTrainerAction(player, opponent, playerAction, playerMove, playerSwitchTarget, playerItem, false);
            }
        }

        if (player.getFrontPokemon() != null && player.getFrontPokemon().isKO()) {
            Pokemon nextPokemon = getNextPokemon(player);
            if (nextPokemon != null) player.setFront(nextPokemon);
        }

        if (opponent.getFrontPokemon() != null && opponent.getFrontPokemon().isKO()) {
            Pokemon nextPokemon = getNextPokemon(opponent);
            if (nextPokemon != null) opponent.setFront(nextPokemon);
        }
    }

    private Action decodeOpponentAction(int actionIndex) {
        if (actionIndex >= 0 && actionIndex <= 3) return Action.Attack;
        if (actionIndex == 4) return Action.Switch;
        if (actionIndex == 5) return Action.Item;
        return Action.Attack;
    }

    private Move chooseMoveFromActionIndex(Pokemon pokemon, int actionIndex) {
        if (pokemon == null || pokemon.getAttacks() == null || pokemon.getAttacks().isEmpty()) {
            return null;
        }

        if (actionIndex < 0 || actionIndex > 3) {
            return null;
        }

        if (actionIndex >= pokemon.getAttacks().size()) {
            return null;
        }

        Move move = pokemon.getAttacks().get(actionIndex);
        if (move == null) {
            return null;
        }

        if (move.getPP() <= 0) {
            return null;
        }

        return move;
    }

    private void executeTrainerAction(
            Trainer attackerTrainer,
            Trainer defenderTrainer,
            Action action,
            Move move,
            Pokemon switchTarget,
            Item item,
            boolean markInvalidForOpponent
    ) {
        Pokemon attacker = attackerTrainer.getFrontPokemon();
        Pokemon defender = defenderTrainer.getFrontPokemon();

        if (attacker == null || attacker.isKO()) return;

        switch (action) {
            case Attack -> {
                if (defender == null || defender.isKO()) return;

                if (move == null) {
                    if (markInvalidForOpponent) {
                        markOpponentInvalidAction("invalid_attack_choice");
                    }
                    return;
                }

                attacker.attack(defender, move);
            }

            case Switch -> {
                if (switchTarget == null || switchTarget == attacker || switchTarget.getStatus() == Status.KO) {
                    if (markInvalidForOpponent) {
                        markOpponentInvalidAction("invalid_switch_choice");
                    }
                    return;
                }
                attackerTrainer.setFront(switchTarget);
            }

            case Item -> {
                if (item == null) {
                    if (markInvalidForOpponent) {
                        markOpponentInvalidAction("invalid_item_choice");
                    }
                    return;
                }
                attackerTrainer.use(item, attacker);
            }

            default -> {
                if (markInvalidForOpponent) {
                    markOpponentInvalidAction("invalid_action");
                }
            }
        }
    }

    private Pokemon getNextPokemon(Trainer trainer) {
        for (Pokemon p : trainer.getTeam()) {
            if (p != null && p.getStatus() != Status.KO && p != trainer.getFrontPokemon()) {
                return p;
            }
        }
        return null;
    }

    private String starterName() {
        if (isPlayerFirst == null) return player.getName();
        return isPlayerFirst ? player.getName() : opponent.getName();
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
            }

            if(m1 instanceof StatusAttack){
                obj.addProperty("Precision", ((StatusAttack) m1).getPrecision());
                obj.addProperty("Status", ((StatusAttack) m1).getStatus().toString());
            }

            if(m1 instanceof SetUpMove){
                obj.addProperty("Statistic", ((SetUpMove) m1).getStat());
                obj.addProperty("RaiseLevel", ((SetUpMove) m1).getRaiseLevel());
            }
            attacksData.add(obj);
        }

        pokemonData.add("attacks", attacksData);
    }

    public boolean is_player_first() {
        return computeOrder();
    }

    private boolean computeOrder() {
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

        if (playerSpeed > npcSpeed) return true;
        if (playerSpeed < npcSpeed) return false;

        return SeedManager.getRng().nextBoolean();
    }

    private int priorityOf(Action action) {
        if (action == Action.Switch) return 6;
        if (action == Action.Item) return 6;
        if (action == Action.Run) return 6;
        return 0;
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

    private void clearLastActionFlags() {
        lastOpponentActionInvalid = false;
        lastOpponentInvalidReason = "";
    }

    private void markOpponentInvalidAction(String reason) {
        lastOpponentActionInvalid = true;
        lastOpponentInvalidReason = reason;
    }

    private void resetState() {
        turn = 0;
        isPlayerFirst = null;
        clearLastActionFlags();

        for (Pokemon p : player.getTeam()) {
            p.heal();
        }
        for (Pokemon p : opponent.getTeam()) {
            p.heal();
        }

        SeedManager.incrementSeed();
        SeedManager.setSeed(SeedManager.getSeed());
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