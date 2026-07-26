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
        bag.setItem(new Heal(Category.HEALTH, "Hyper Potion", "Heals 200 HP to a Pokémon", 200), 1);
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

    public static GameState createGameState(Trainer player, Trainer opponent) {
        return new GameState(player, opponent, 0);
    }

    public static List<Matchup> allTrainingMatchups() {
        return List.of(
                salamecheVsBulbizarre(),
                gardevoirVsLeviator(),
                brasegaliVsLugulabre(),
                raichuVsSablaireau(),
                offensiveCore(),

                paralysisMatchup(),
                poisonMatchup(),
                badlyPoisonedMatchup(),
                burnAndPhysicalMatchup(),
                burnAndSpecialMatchup(),
                confuseAndPhysicalMatchup(),
                confuseAndSpecialMatchup(),
                sleepMatchup(),

                switchMatchup(),
                switchCoreStarters(),

                setupAttackLucarioSwordsDance(),
                setupDefenseAltariaCottonGuard(),
                setupSpecialAttackGardevoirCalmMind(),
                setupSpecialDefenseMiloticAmnesia(),
                setupSpeedBrasegaliTurbo(),

                peter(),
                blue(),
                pierreRochard(),
                cynthia(),
                iris()
        );
    }

    // region offensive

    // STAB + avantage x2 : Salamèche doit privilégier Flammèche contre Bulbizarre.
    public static Matchup salamecheVsBulbizarre() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBulbizarre());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Avantage x4 : Gardevoir doit privilégier Tonnerre contre Léviator.
    public static Matchup gardevoirVsLeviator() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLeviator());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirOffensive());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Immunité : Lugulabre est immunisé à Pied Sauté, Séisme est la bonne réponse.
    public static Matchup brasegaliVsLugulabre() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLugulabre());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateBrasegaliOffensive());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Immunité x0 : Sablaireau est immunisé à Tonnerre, Surf est la bonne réponse.
    public static Matchup raichuVsSablaireau() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateSablaireau());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateRaichuOffensive());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // Synthèse offensive : l'agent doit recombiner STAB, x4, immunités et résistances.
    public static Matchup offensiveCore() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLeviator());
        playerTeam.add(initiateLugulabre());
        playerTeam.add(initiateSablaireau());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirOffensive());
        opponentTeam.add(initiateBrasegaliOffensive());
        opponentTeam.add(initiateRaichuOffensive());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.OFFENSIVE);
    }

    // endregion

    // region status

    // Paralysie : Wattouat ralentit Salamèche avec Cage-Éclair.
    public static Matchup paralysisMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateSalameche());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateWattouatStatus());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Poison classique : Nostenfer applique Gaz Toxik contre Caratroc.
    public static Matchup poisonMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCaratrocSetupTarget());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateNostenferPoisonGas());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Gravement empoisonné : Nostenfer applique Toxik contre Caratroc.
    public static Matchup badlyPoisonedMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCaratrocSetupTarget());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateNostenferToxic());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Brûlure utile : Feunard brûle un attaquant physique.
    public static Matchup burnAndPhysicalMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateMachopeurPhysical());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateFeunardStatus());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Brûlure moins utile : Feunard brûle un attaquant spécial.
    public static Matchup burnAndSpecialMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateGardevoirSpecialTarget());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateFeunardStatus());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Confusion utile : Gardevoir perturbe un attaquant physique.
    public static Matchup confuseAndPhysicalMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateDracolossePhysical());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirConfuseRay());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Confusion moins utile : Gardevoir perturbe un attaquant spécial.
    public static Matchup confuseAndSpecialMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateAlakazamSpecial());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateGardevoirConfuseRay());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // Sommeil : Raflesia endort Évoli avec Poudre Dodo.
    public static Matchup sleepMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateEvoli());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateRaflesiaSleep());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.STATUS);
    }

    // endregion

    // region switch

    // Switch : lead défavorable, Bulbizarre est disponible en switch.
    public static Matchup switchMatchup() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateCarapuce());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateSalameche());
        opponentTeam.add(initiateBulbizarre());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.SWITCH);
    }

    // Switch : noyau starter complet des deux côtés.
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

    // endregion

    // region setup

    // Attaque : Lucario doit utiliser Danse-Lames pour rentabiliser le boost sur 3 adversaires.
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

    // Défense spéciale : Milobellus doit utiliser Amnésie pour encaisser des attaquants spéciaux.
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

    // Vitesse : Braségali doit utiliser Turbo pour dépasser des adversaires rapides.
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

    // endregion

    // region mixed

    // Peter : équipe Dragon/Vol avec menaces électriques et glace en face.
    public static Matchup peter() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateMagnetonMixed());
        playerTeam.add(initiateGardevoirSpecialTarget());
        playerTeam.add(initiateLaggronMixed());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateDracolossePeter());
        opponentTeam.add(initiateLeviatorPeter());
        opponentTeam.add(initiatePteraPeter());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.MIXED);
    }

    // Blue : équipe généraliste qui demande d'alterner attaque, switch et couverture.
    public static Matchup blue() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateLeviator());
        playerTeam.add(initiateNoacierMixed());
        playerTeam.add(initiateLucarioMixed());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateAlakazamBlue());
        opponentTeam.add(initiateArcaninBlue());
        opponentTeam.add(initiateRhinoférosBlue());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.MIXED);
    }

    // Pierre Rochard : résistances Acier/Roche, mais menaces Feu/Combat/Eau/Électrik.
    public static Matchup pierreRochard() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateBrasegaliOffensive());
        playerTeam.add(initiateMilobellusMixed());
        playerTeam.add(initiateMagnetonMixed());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateMetalossePierreRochard());
        opponentTeam.add(initiateAirmurePierreRochard());
        opponentTeam.add(initiateVacilysPierreRochard());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.MIXED);
    }

    // Cynthia : équipe équilibrée avec setup, couverture et adaptation défensive.
    public static Matchup cynthia() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateGardevoirSpecialTarget());
        playerTeam.add(initiateMammochonMixed());
        playerTeam.add(initiateDracolosseMixedTarget());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateCarchacrokCynthia());
        opponentTeam.add(initiateLucarioCynthia());
        opponentTeam.add(initiateMilobellusMixed());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.MIXED);
    }

    // Iris : dragons offensifs accompagnés de Lokhlass pour couvrir les menaces Glace.
    public static Matchup iris() {
        LinkedList<Pokemon> playerTeam = new LinkedList<>();
        playerTeam.add(initiateGardevoirSpecialTarget());
        playerTeam.add(initiateMagnetonMixed());
        playerTeam.add(initiateMammochonMixed());

        LinkedList<Pokemon> opponentTeam = new LinkedList<>();
        opponentTeam.add(initiateTranchodonIris());
        opponentTeam.add(initiateDracolosseIris());
        opponentTeam.add(initiateLokhlassIris());

        return new Matchup(playerTeam, opponentTeam, MatchupCategory.MIXED);
    }

    // endregion
}
