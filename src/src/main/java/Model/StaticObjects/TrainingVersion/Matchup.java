package Model.StaticObjects.TrainingVersion;

import Model.GameState;
import Model.Inventory.Bag;
import Model.Inventory.Items.Item;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static Model.StaticObjects.TrainingVersion.PokemonSample.*;

public record Matchup(LinkedList<Pokemon> playerTeam, LinkedList<Pokemon> opponentTeam) {

    public Trainer createPlayerTrainer() {
        Map<Item, Integer> inventory = new HashMap<>();
        Bag bag = new Bag(inventory);
        return new Trainer("player", bag, playerTeam);
    }

    public Trainer createOpponentTrainer() {
        Map<Item, Integer> inventory = new HashMap<>();
        Bag bag = new Bag(inventory);
        return new Trainer("opponent", bag, opponentTeam);
    }

    public GameState createGameState() {
        Trainer player = createPlayerTrainer();
        Trainer opponent = createOpponentTrainer();
        return new GameState(player, opponent, 0);
    }

    /*
     * =========================
     * Matchups d'entraînement
     * =========================
     */

    // Super effective x2 : Pikachu vs Carapuce
    public static Matchup pikachuVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiatePikachu());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Super effective x2 : Salamèche vs Bulbizarre
    public static Matchup salamecheVsBulbizarre() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBulbizarre());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Super effective x4 : Pikachu vs Léviator
    public static Matchup pikachuVsLeviator() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLeviator());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiatePikachu());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Super effective x4 : Salamèche vs Paras
    public static Matchup salamecheVsParas() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateParas());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam);
    }

    // STAB : Salamèche vs Évoli
    public static Matchup salamecheVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam);
    }

    // STAB : Carapuce vs Évoli
    public static Matchup carapuceVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateCarapuce());

        return new Matchup(playerTeam, opponentTeam);
    }

    // STAB : Bulbizarre vs Évoli
    public static Matchup bulbizarreVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam);
    }

    // SE + STAB : Bulbizarre vs Carapuce
    public static Matchup bulbizarreVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Switch : lead défavorable, switch évident possible
    public static Matchup switchSalamecheToBulbizarreVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Switch : triangle starter simple
    public static Matchup switchCoreStarters() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());
        playerTeam.add(initiateBulbizarre());
        playerTeam.add(initiateSalameche());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());
        opponentTeam.add(initiateBulbizarre());
        opponentTeam.add(initiateCarapuce());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Status simple : Ekans vs Évoli
    public static Matchup ekansVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateEkans());

        return new Matchup(playerTeam, opponentTeam);
    }

    // Status / double type : Butterfree vs Carapuce
    public static Matchup butterfreeVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateButterfree());

        return new Matchup(playerTeam, opponentTeam);
    }
}