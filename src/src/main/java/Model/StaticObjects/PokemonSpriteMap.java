package Model.StaticObjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PokemonSpriteMap {

    private static final Map<Integer, String> ID_TO_SPRITE_NAME;

    static {
        Map<Integer, String> names = new HashMap<>();

        // Génération 1
        names.put(1, "bulbasaur");
        names.put(4, "charmander");
        names.put(7, "squirtle");
        names.put(26, "raichu");
        names.put(28, "sandslash");
        names.put(38, "ninetales");
        names.put(45, "vileplume");
        names.put(49, "venomoth");
        names.put(53, "persian");
        names.put(59, "arcanine");
        names.put(64, "kadabra");
        names.put(65, "alakazam");
        names.put(67, "machoke");
        names.put(68, "machamp");
        names.put(82, "magneton");
        names.put(89, "muk");
        names.put(94, "gengar");
        names.put(101, "electrode");
        names.put(112, "rhydon");
        names.put(130, "gyarados");
        names.put(131, "lapras");
        names.put(133, "eevee");
        names.put(135, "jolteon");
        names.put(142, "aerodactyl");
        names.put(143, "snorlax");
        names.put(149, "dragonite");

        // Génération 2
        names.put(169, "crobat");
        names.put(179, "mareep");
        names.put(200, "misdreavus");
        names.put(213, "shuckle");
        names.put(227, "skarmory");
        names.put(230, "kingdra");
        names.put(232, "donphan");

        // Génération 3
        names.put(254, "sceptile");
        names.put(257, "blaziken");
        names.put(260, "swampert");
        names.put(282, "gardevoir");
        names.put(297, "hariyama");
        names.put(302, "sableye");
        names.put(306, "aggron");
        names.put(334, "altaria");
        names.put(340, "whiscash");
        names.put(342, "crawdaunt");
        names.put(344, "claydol");
        names.put(346, "cradily");
        names.put(348, "armaldo");
        names.put(350, "milotic");
        names.put(354, "banette");
        names.put(356, "dusclops");
        names.put(359, "absol");
        names.put(364, "sealeo");
        names.put(370, "luvdisc");
        names.put(376, "metagross");

        // Génération 4
        names.put(445, "garchomp");
        names.put(448, "lucario");
        names.put(473, "mamoswine");
        names.put(478, "froslass");

        // Génération 5
        names.put(598, "ferrothorn");
        names.put(609, "chandelure");
        names.put(612, "haxorus");

        ID_TO_SPRITE_NAME = Collections.unmodifiableMap(names);
    }

    private PokemonSpriteMap() {}

    public static String getSpriteName(int pokedexId) {
        return ID_TO_SPRITE_NAME.get(pokedexId);
    }

    public static boolean containsId(int pokedexId) {
        return ID_TO_SPRITE_NAME.containsKey(pokedexId);
    }

    public static Map<Integer, String> getAllSpriteNames() {
        return ID_TO_SPRITE_NAME;
    }
}