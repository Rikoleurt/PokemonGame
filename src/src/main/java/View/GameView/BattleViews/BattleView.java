package View.GameView.BattleViews;

import Model.GameState;
import Model.Person.Trainer;
import Model.Pokemon.Field;
import Model.Pokemon.Pokemon;
import Model.StaticObjects.PokemonSpriteMap;
import Model.StaticObjects.TrainingVersion.Matchup;
import Server.SocketServer;
import Utils.SceneManager;
import View.GameView.BattleViews.InfoBars.Bar;
import View.GameView.BattleViews.InfoBars.OpponentBar;
import View.GameView.BattleViews.InfoBars.PlayerBar;
import View.GameView.BattleViews.Text.StatBubble;
import View.GameView.BattleViews.Text.TextBubble;
import View.SettingView.AudioView;
import View.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class BattleView extends BorderPane implements View {
    static TextBubble textBubble = new TextBubble();
    static StatBubble statBubble = new StatBubble();

    public static Trainer player;
    public static Trainer agent;

    static BattleButtons battleButtons;
    static Bar opponentBar;
    static Bar playerBar;

    public static Field field;

    static ImageView playerSprite;
    static ImageView opponentSprite;

    private static final String FRONT_SPRITE_DIRECTORY = "/sprites/gen5ani/";
    private static final String BACK_SPRITE_DIRECTORY = "/sprites/gen5ani-back/";
    private static final String SPRITE_EXTENSION = ".gif";

    private static GameState gs;

    public BattleView(Trainer player, Trainer agent, Field field) {
        BattleView.player = player;
        BattleView.agent = agent;
        BattleView.field = field;

        battleButtons = new BattleButtons(player, agent, textBubble);

        playerBar = new PlayerBar(10, player.getFrontPokemon());
        opponentBar = new OpponentBar(10, agent.getFrontPokemon());

        // Set up player and opponent bars

        HBox bottomBox = new HBox();
        bottomBox.setSpacing(20);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setAlignment(Pos.BOTTOM_LEFT);

        textBubble.setPrefHeight(150);
        textBubble.prefWidthProperty().bind(bottomBox.widthProperty().subtract(320));

        HBox.setHgrow(textBubble, Priority.ALWAYS);
        bottomBox.getChildren().addAll(textBubble, battleButtons);

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
        playerSprite.setSmooth(false);
        opponentSprite.setSmooth(false);

        BorderPane.setAlignment(playerSprite, Pos.BOTTOM_LEFT);
        centerPane.setPadding(new Insets(0,0,0,200));
        BorderPane.setAlignment(opponentSprite, Pos.TOP_RIGHT);

        centerPane.setBottom(playerSprite);
        centerPane.setTop(opponentSprite);

        setCenter(centerPane);

        MenuBar menuBar = makeMenuBar();

        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(menuBar, opponentBar);
        setTop(topContainer);

        refreshSprites();
        setupServer(player, agent);
    }

    public MenuBar makeMenuBar() {
        // Set up the menu
        MenuBar menuBar = new MenuBar();
        Menu settingsMenu = new Menu("Settings");

        MenuItem audioItem = new MenuItem("Audio");
        MenuItem videoItem = new MenuItem("Video");
        MenuItem controlsItem = new MenuItem("Controls");

        audioItem.setOnAction(e -> onAudioPressed());
        videoItem.setOnAction(e -> System.out.println("Video"));
        controlsItem.setOnAction(e -> System.out.println("Controls"));

        settingsMenu.getItems().addAll(audioItem, videoItem, controlsItem);

        Menu helpMenu = new Menu("Help");
        MenuItem helpItem = new MenuItem("Help");

        helpMenu.getItems().addAll(helpItem);
        helpItem.setOnAction(e -> System.out.println("Help"));

        Menu exitMenu = new Menu("Exit");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));

        exitMenu.getItems().addAll(exitItem);
        menuBar.getMenus().addAll(settingsMenu, helpMenu, exitMenu);
        return menuBar;
    }

    public void onAudioPressed() {
        SceneManager.switchStageTo(new AudioView(() -> SceneManager.switchStageTo(SceneManager.getFightView())));
    }

    private static Image spriteFor(Pokemon pokemon, boolean back) {
        if (pokemon == null) {
            return null;
        }

        String spriteName = PokemonSpriteMap.getSpriteName(
                pokemon.getId()
        );

        if (spriteName == null) {
            System.err.println(
                    "No sprite name registered for "
                            + pokemon.getName()
                            + " (Pokédex ID : "
                            + pokemon.getId()
                            + ")"
            );

            return null;
        }

        String directory = back
                ? BACK_SPRITE_DIRECTORY
                : FRONT_SPRITE_DIRECTORY;

        String spritePath =
                directory
                        + spriteName
                        + SPRITE_EXTENSION;

        var spriteResource =
                BattleView.class.getResource(spritePath);

        if (spriteResource == null) {
            System.err.println(
                    "Can't find sprite : "
                            + spritePath
                            + " for "
                            + pokemon.getName()
            );

            return null;
        }

        return new Image(
                spriteResource.toExternalForm(),
                200,
                200,
                true,
                false
        );
    }

    public static void refreshSprites() {
        if (player == null || agent == null || playerSprite == null || opponentSprite == null) {
            return;
        }

        Pokemon playerPokemon = player.getFrontPokemon();
        Pokemon opponentPokemon = agent.getFrontPokemon();

        playerSprite.setImage(
                spriteFor(playerPokemon, true)
        );

        opponentSprite.setImage(
                spriteFor(opponentPokemon, false)
        );
    }

    private static void setupServer(Trainer player, Trainer agent) {
        gs = Matchup.createGameState(player, agent);
        System.out.println(gs.pretty_state());
        SocketServer server = SocketServer.getInstance();

        new Thread(() -> {
            try {
                server.start(5001);
            } catch (IOException e) {
                System.out.println("Client connect failed " + e.getMessage());
            }
        }).start();
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
    public static Trainer getAgent() {
        return agent;
    }
    public static Trainer getPlayer() {
        return player;
    }
    public static Field getTerrain() {
        return field;
    }
    public static void setPlayer(Trainer player) {
        BattleView.player = player;
        BattleView.refreshSprites();
    }
    public static void setAgent(Trainer agent) {
        BattleView.agent = agent;
        BattleView.refreshSprites();
    }
}
