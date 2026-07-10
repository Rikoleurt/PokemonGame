package Utils;

import Model.Pokemon.Pokemon;
import Model.StaticObjects.TrainingVersion.Matchup;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchupRandomizer {

    List<Matchup> matchups;
    long seed;

    public MatchupRandomizer(List<Matchup> matchups, long seed) {
        this.matchups = matchups;
        this.seed = seed;
    }

    public void shuffleAndPrintMatchups() {
        Random rand = new Random(seed);
        Collections.shuffle(matchups, rand);
        printMatchups();
    }

    private void printMatchups() {
        for (Matchup matchup : matchups) {
            for (Pokemon pokemon : matchup.playerTeam()){
                System.out.print(pokemon.getName() + " ");
            }
            System.out.println(" VS ");
            for(Pokemon pokemon : matchup.opponentTeam()){
                System.out.println(pokemon.getName() + " ");
            }
        }
    }
}
