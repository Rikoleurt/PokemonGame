package Model.StaticObjects.TrainingVersion;

import Model.GameState;
import Model.Inventory.Bag;
import Model.Inventory.Category;
import Model.Inventory.Items.Heal.Heal;
import Model.Inventory.Items.Item;
import Model.Person.Trainer;
import Model.Pokemon.Pokemon;
import Utils.MatchupCategory;
import Utils.SeedManager;

import java.util.*;

import static Model.StaticObjects.TrainingVersion.PokemonSample.*;

public record Matchup(LinkedList<Pokemon> playerTeam, LinkedList<Pokemon> opponentTeam, MatchupCategory category) {

    public Trainer createPlayerTrainer() {
        Map<Item, Integer> inventory = new HashMap<>();
        Bag bag = new Bag(inventory);
        // bag.setItem(new Heal(Category.HEALTH, "Potion", "Heals 20 HP to a Pokémon", 20), 1);
        return new Trainer("player", bag, playerTeam);
    }

    public Trainer createOpponentTrainer() {
        Map<Item, Integer> inventory = new HashMap<>();
        Bag bag = new Bag(inventory);
        bag.setItem(new Heal(Category.HEALTH, "Hyper Potion", "Heals 200 HP to a Pokémon", 200), 1);
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

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Super effective x2 : Salamèche vs Bulbizarre
    public static Matchup salamecheVsBulbizarre() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBulbizarre());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Super effective x4 : Pikachu vs Léviator
    public static Matchup pikachuVsLeviator() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLeviator());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiatePikachu());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Super effective x4 : Salamèche vs Paras
    public static Matchup salamecheVsParas() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateParas());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // STAB : Salamèche vs Évoli
    public static Matchup salamecheVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

    return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // STAB : Carapuce vs Évoli
    public static Matchup carapuceVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateCarapuce());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // STAB : Bulbizarre vs Évoli
    public static Matchup bulbizarreVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // SE + STAB : Bulbizarre vs Carapuce
    public static Matchup bulbizarreVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Switch : lead défavorable, switch évident possible
    public static Matchup switchMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());
        opponentTeam.add(initiateBulbizarre());

    return new Matchup(playerTeam, opponentTeam, MatchupCategory.SWITCH);
    }

    public static Matchup switchCoreStarters() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());
        playerTeam.add(initiateBulbizarre());
        playerTeam.add(initiateSalameche());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());
        opponentTeam.add(initiateBulbizarre());
        opponentTeam.add(initiateCarapuce());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SWITCH);
    }

    // Status simple : Ekans vs Évoli
    public static Matchup ekansVsEvoli() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateEkans());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Status / double type : Butterfree vs Carapuce
    public static Matchup butterfreeVsCarapuce() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateButterfree());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    public static Matchup lucarioVsBlisseyThenAvalugg() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBlissey());
        playerTeam.add(initiateAvalugg());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateLucario());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    public static Matchup lucarioVsAvaluggThenBlissey() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateAvalugg());
        playerTeam.add(initiateBlissey());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateLucario());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    /*
     * =========================
     * Matchups de test des statuts
     * =========================
     */

    // Paralysie : Wattouat ralentit un adversaire plus rapide qui ne le one-shot pas
    public static Matchup paralysisMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateSalameche());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateMareep());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Poison classique : Gaz Toxik applique poisoned de manière déterministe
    public static Matchup poisonMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateShuckle());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateCrobat());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Gravement empoisonné : Toxic applique badlyPoisoned de manière déterministe
    public static Matchup badlyPoisonedMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateShuckle());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateNostenferToxic());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Brûlure utile : Feu-Follet contre attaquant physique
    public static Matchup burnAndPhysicalMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateMachop());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateNinetales());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Brûlure moins utile : Feu-Follet contre attaquant spécial
    public static Matchup burnAndSpecialMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateGardevoir());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateNinetales());

    return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Confusion utile : Onde Folie contre attaquant physique
    public static Matchup confuseAndPhysicalMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBrasegali());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirConfuseRay());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Confusion moins utile : Onde Folie contre attaquant spécial
    public static Matchup confuseAndSpecialMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateAlakazam());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirConfuseRay());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Sommeil : Poudre Dodo contre un Pokémon de force globalement équivalente
    public static Matchup sleepMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateRaflesia());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Attaque : Lucario doit utiliser Danse-Lame pour rentabiliser le boost sur 3 adversaires.
    public static Matchup setupAttackLucarioSwordsDance() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();

        playerTeam.add(initiateSnorlaxSetupTarget());
        playerTeam.add(initiateHariyamaSetupTarget());
        playerTeam.add(initiateDonphanSetupTarget());

        Collections.shuffle(playerTeam, SeedManager.getRng());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateLucarioSwordsDance());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SETUP);
    }

    // Défense : Altaria doit utiliser Cotogarde pour encaisser des attaquants physiques.
    public static Matchup setupDefenseAltariaCottonGuard() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateMachampPhysicalTarget());
        playerTeam.add(initiateRhydonPhysicalTarget());
        playerTeam.add(initiateArmaldoPhysicalTarget());

        Collections.shuffle(playerTeam, SeedManager.getRng());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateAltariaCottonGuard());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SETUP);
    }

    // Attaque spéciale : Gardevoir doit utiliser Plénitude pour sweep 3 Pokémon.
    public static Matchup setupSpecialAttackGardevoirCalmMind() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateMukSpecialSetupTarget());
        playerTeam.add(initiateMiloticSpecialSetupTarget());
        playerTeam.add(initiateClaydolSpecialSetupTarget());

        Collections.shuffle(playerTeam, SeedManager.getRng());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirCalmMind());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SETUP);
    }

    public static Matchup setupSpecialDefenseMiloticAmnesia() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateKadabraSpecialTargetBalanced());
        playerTeam.add(initiateVenomothSpecialTargetBalanced());
        playerTeam.add(initiateMisdreavusSpecialTargetBalanced());

        Collections.shuffle(playerTeam, SeedManager.getRng());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateMiloticAmnesiaBalanced());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SETUP);
    }

    public static Matchup setupSpeedBrasegaliTurbo() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateJolteonSpeedTargetBalanced());
        playerTeam.add(initiateElectrodeSpeedTargetBalanced());
        playerTeam.add(initiatePersianSpeedTargetBalanced());

        Collections.shuffle(playerTeam, SeedManager.getRng());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBrasegaliTurboBalanced());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SETUP);
    }
}