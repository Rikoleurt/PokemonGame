package View.FightView;

import Person.Player;
import Pokemon.Pokemon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static StaticObjects.Player.initiatePlayer;


public class PlayerHPBar extends VBox {

    static Font font = Font.loadFont(PlayerHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);
    Player player = initiatePlayer();
    
    Pokemon playerPokemon = player.getFrontPokemon();

    // Player variable
    int playerPokemonHP = playerPokemon.getHP();
    int playerPokemonLvl = playerPokemon.getLevel();
    int playerPokemonMaxHP = playerPokemon.getMaxHP();

    // Player Labels
    Label playerPokemonName = new Label(playerPokemon.getName());
    Label HP = new Label("HP :");
    Label pokemonHPs = new Label(playerPokemon.getHP() + "/" + playerPokemon.getMaxHP());
    Label pokemonLvl = new Label("Lvl : " + playerPokemon.getLevel());



//    // NPC variable
//    int npcPokemonHP = npc.getFrontPokemon().getHP();
//    int npcPokemonLvl = npc.getFrontPokemon().getLevel();
//    int npcPokemonMaxHP = npc.getFrontPokemon().getMaxHP();

    ProgressBar playerBar = new ProgressBar(10);

    protected PlayerHPBar(double spacing){

        playerPokemonName.setFont(font);
        HP.setFont(font);
        pokemonHPs.setFont(font);
        pokemonLvl.setFont(font);

        playerBar.setPrefSize(150,20);
        playerBar.setStyle("-fx-accent: #709f5e;");

        updateHPBars();

        if(playerPokemon.getMaxHP()/4 < playerPokemonHP  && playerPokemonHP < playerPokemon.getMaxHP()/2){
            playerBar.setStyle("-fx-accent: #f68524;");
        }

        if(playerPokemonHP < playerPokemon.getMaxHP()/4){
            playerBar.setStyle("-fx-accent: #d81e1e;");
        }

        HBox HBox1 = new HBox(playerPokemonName, pokemonLvl);
        HBox HBox2 = new HBox(HP, playerBar);
        HBox HBox3 = new HBox(pokemonHPs);

        HBox1.setSpacing(spacing * 10);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_RIGHT);

        setPadding(new Insets(20));

        this.getChildren().addAll(HBox1, HBox2, HBox3);
        this.setAlignment(Pos.BOTTOM_RIGHT);
    }

    private void updateHPBars() {
        // Player HP bar update
        playerPokemonHP = 3; // Test
        playerBar.setProgress((double) playerPokemonHP / playerPokemonMaxHP);
        pokemonHPs.setText(playerPokemonHP + "/" + playerPokemonMaxHP);
    }
    private void updateStatus(){

    }
}
