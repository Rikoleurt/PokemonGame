package Utils;

import java.util.Random;

public class SeedManager {
    private static Random rng = new Random(1L);
    private static long currentSeed = 1L;

    public static void setSeed(long seed) {
        currentSeed = seed;
        rng = new Random(seed);
        System.out.println("RandomSeedManager: Global seed set to " + seed);
    }

    public static long getSeed() {
        return currentSeed;
    }

    public static Random getRng() {
        return rng;
    }
    public static void incrementSeed(){
        currentSeed++;
        System.out.println("Seed: " + currentSeed);
    }
}