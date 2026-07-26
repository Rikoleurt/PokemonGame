package Utils;

import Model.Pokemon.Pokemon;
import Model.StaticObjects.TrainingVersion.Matchup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MatchupRandomizer {

    private final List<Matchup> allMatchups;
    private final long seed;
    private final boolean printOrderAtEachCycle;

    private List<Matchup> currentCycle;
    private int index;
    private int cycle;

    public MatchupRandomizer(List<Matchup> matchups, long seed) {
        this(matchups, seed, true);
    }

    public MatchupRandomizer(List<Matchup> matchups, long seed, boolean printOrderAtEachCycle) {
        this.allMatchups = new ArrayList<>(matchups);
        this.seed = seed;
        this.printOrderAtEachCycle = printOrderAtEachCycle;
        this.currentCycle = new ArrayList<>();
        this.index = 0;
        this.cycle = 0;

        printDistribution();
        reshuffle();
    }

    public Matchup nextMatchup() {
        if (index >= currentCycle.size()) {
            reshuffle();
        }

        Matchup matchup = currentCycle.get(index);
        index++;

        System.out.printf(
                "Matchup sélectionné %d/%d du cycle %d : %s%n",
                index,
                currentCycle.size(),
                cycle,
                describeMatchup(matchup)
        );

        return matchup;
    }

    private void reshuffle() {
        cycle++;
        currentCycle = new ArrayList<>(allMatchups);

        Random random = new Random(seed + cycle);
        Collections.shuffle(currentCycle, random);

        index = 0;

        if (printOrderAtEachCycle) {
            printCurrentCycle();
        }
    }

    public void printDistribution() {
        Map<MatchupCategory, Integer> counts = new EnumMap<>(MatchupCategory.class);

        for (MatchupCategory category : MatchupCategory.values()) {
            counts.put(category, 0);
        }

        for (Matchup matchup : allMatchups) {
            counts.merge(matchup.category(), 1, Integer::sum);
        }

        int total = allMatchups.size();

        System.out.println("=== Distribution des matchups ===");
        System.out.println("Total : " + total);

        for (MatchupCategory category : MatchupCategory.values()) {
            int count = counts.get(category);
            double percentage = 100.0 * count / total;

            System.out.printf(
                    "%-10s : %2d matchups (%5.1f%%)%n",
                    category,
                    count,
                    percentage
            );
        }
    }

    public void printCurrentCycle() {
        System.out.println("=== Ordre du cycle " + cycle + " | seed " + (seed + cycle) + " ===");

        for (int i = 0; i < currentCycle.size(); i++) {
            Matchup matchup = currentCycle.get(i);

            System.out.printf(
                    "%2d. %-10s | %s%n",
                    i + 1,
                    matchup.category(),
                    describeMatchup(matchup)
            );
        }
    }

    private String describeMatchup(Matchup matchup) {
        return teamToString(matchup.opponentTeam())
                + " VS "
                + teamToString(matchup.playerTeam());
    }

    private String teamToString(List<Pokemon> team) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < team.size(); i++) {
            if (i > 0) {
                builder.append(" / ");
            }

            builder.append(team.get(i).getName());
        }

        return builder.toString();
    }
}