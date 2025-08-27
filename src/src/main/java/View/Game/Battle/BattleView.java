package View.Game.Battle;

import Model.Pokemon.Pokemon;
import Model.Pokemon.Terrain;
import Model.Pokemon.TerrainEnum.Debris;
import Model.Pokemon.TerrainEnum.Weather;
import Model.Person.NPC;
import Model.Person.Player;
import View.Game.Battle.InfoBars.Bar;
import View.Game.Battle.InfoBars.OpponentBar;
import View.Game.Battle.InfoBars.PlayerBar;
import View.Game.Battle.Text.StatBubble;
import View.Game.Battle.Text.TextBubble;
import View.Game.SceneManager;
import View.Game.Switch.SwitchView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static Model.StaticObjects.NPCExample.initiateEnemy;
import static Model.StaticObjects.PlayerExample.initiatePlayer;

public class BattleView extends BorderPane {
    static TextBubble textBubble = new TextBubble();
    static StatBubble statBubble = new StatBubble();

    public static Player player = initiatePlayer();
    public static NPC npc = initiateEnemy();

    static Pokemon playerPokemon = player.getFrontPokemon();
    static Pokemon npcPokemon = npc.getFrontPokemon();

    static Bar opponentBar = new OpponentBar(5, npcPokemon);
    static Bar playerBar = new PlayerBar(5, playerPokemon);
    public static Terrain terrain = new Terrain(player.getTeam(), npc.getTeam(), Debris.normal, Weather.normal);

     static BattleButtons battleButtons = new BattleButtons(textBubble, opponentBar, playerBar);

    static ImageView playerSprite;
    static ImageView opponentSprite;

    static final Map<String, String> spriteOverride = new HashMap<>();

    static {
        spriteOverride.put("bulbasaur", "bulbasaur-f");
        spriteOverride.put("pikachu", "pikachu");
        spriteOverride.put("squirtle", "squirtle-f");
        spriteOverride.put("charmander", "charmander");
        spriteOverride.put("pidgey", "pidgey");
        spriteOverride.put("butterfree", "butterfree-f");
        spriteOverride.put("ekans", "ekans");
    }

    public BattleView() {
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(20);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);

        textBubble.setPrefHeight(150);
        textBubble.prefWidthProperty().bind(bottomBox.widthProperty().subtract(320));

        HBox.setHgrow(textBubble, Priority.ALWAYS);
        bottomBox.getChildren().addAll(textBubble, battleButtons);

        setTop(opponentBar);
        setRight(playerBar);
        setBottom(bottomBox);

        setAlignment(opponentBar, Pos.TOP_LEFT);
        setAlignment(playerBar, Pos.BOTTOM_CENTER);

        HBox centerBox = new HBox();
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setSpacing(160);

        BorderPane centerPane = new BorderPane();

        playerSprite = new ImageView();
        opponentSprite = new ImageView();

        playerSprite.setPreserveRatio(true);
        opponentSprite.setPreserveRatio(true);
        playerSprite.setFitHeight(200);
        opponentSprite.setFitHeight(200);
        playerSprite.setSmooth(true);
        opponentSprite.setSmooth(true);

        BorderPane.setAlignment(playerSprite, Pos.BOTTOM_LEFT);
        centerPane.setPadding(new Insets(0,0,0,200));
        BorderPane.setAlignment(opponentSprite, Pos.TOP_RIGHT);

        centerPane.setBottom(playerSprite);
        centerPane.setTop(opponentSprite);

        setCenter(centerPane);

        refreshSprites();
    }

    private static String normalizeName(String raw, boolean back) {
        String n = Normalizer.normalize(raw, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        n = n.toLowerCase().replace("’", "'").replace("♀", "-f").replace("♂", "-m");
        n = n.replace(".", "").replace("'", "").replace(" ", "-");
        if (n.endsWith("-m")) n = n.substring(0, n.length() - 2);
        if (spriteOverride.containsKey(n)) n = spriteOverride.get(n);
        if (back) n = n + "b";
        return n;
    }

    private static Image spriteFor(Pokemon p, boolean back) {
        String base = normalizeName(p.getName(), back);
        String path = resolveSpritePath(base);
        if (path == null) return null;
        return new Image(Objects.requireNonNull(BattleView.class.getResource(path)).toExternalForm());
    }

    private static String resolveSpritePath(String base) {
        String a = "/sprites/ani/" + base + ".gif";
        String b = null;
        String c = null;
        String d = null;

        if (base.endsWith("b")) b = "/sprites/ani/" + base.substring(0, base.length() - 1) + ".gif";
        if (base.endsWith("-fb")) {
            c = "/sprites/ani/" + base.substring(0, base.length() - 1) + ".gif";
            d = "/sprites/ani/" + base.substring(0, base.length() - 3) + ".gif";
        } else if (base.endsWith("-f")) {
            c = "/sprites/ani/" + base.substring(0, base.length() - 2) + ".gif";
        }

        if (BattleView.class.getResource(a) != null) return a;
        if (b != null && BattleView.class.getResource(b) != null) return b;
        if (c != null && BattleView.class.getResource(c) != null) return c;
        if (d != null && BattleView.class.getResource(d) != null) return d;
        return null;
    }

    public static void refreshSprites() {
        Pokemon p = player.getFrontPokemon();
        Pokemon n = npc.getFrontPokemon();
        playerSprite.setImage(spriteFor(p, true));
        opponentSprite.setImage(spriteFor(n, false));
        System.out.println("Refreshing sprites in BattleView...");
    }

    public static TextBubble getTextBubble() {
        return textBubble;
    }

    public static Bar getOpponentBar() {
        return opponentBar;
    }

    public static Bar getPlayerBar() {
        return playerBar;
    }

    public static BattleButtons getFightButtons() {
        return battleButtons;
    }

    public static Model.Person.NPC getNpc() {
        return npc;
    }

    public static Model.Person.Player getPlayer() {
        return player;
    }

    public static Pokemon getNpcPokemon() {
        return npcPokemon;
    }

    public static Pokemon getPlayerPokemon() {
        return playerPokemon;
    }

    public static StatBubble getStatBubble() {
        return statBubble;
    }

    public static Terrain getTerrain() {
        return terrain;
    }

}
