package View.FightView;

import Person.NPC;
import Pokemon.PokemonEnum.Status;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import Pokemon.Pokemon;

import static View.FightView.FightView.npc;

public class EnemyHPBar extends VBox {
    static Font font = Font.loadFont(EnemyHPBar.class.getResource("/font/pokemonFont.ttf").toExternalForm(), 18);

    Pokemon pokemon = npc.getFrontPokemon();

    // NPC Variable
    int pokemonHP = pokemon.getHP();
    int pokemonLvl = pokemon.getLevel();
    int pokemonMaxHP = pokemon.getMaxHP();

    // NPC Label
    Label name = new Label(pokemon.getName());
    Label HP = new Label("HP :");
    Label level = new Label("Lvl : " + pokemonLvl);
    Label HPs = new Label(pokemonHP + "/" + pokemonMaxHP);
    Label status = new Label(pokemon.getStatus().toString());

    ProgressBar npcBar = new ProgressBar(10);

    public EnemyHPBar(double spacing){

        name.setFont(font);
        HP.setFont(font);
        level.setFont(font);
        HPs.setFont(font);

        npcBar.setPrefSize(150,20);
        npcBar.setStyle("-fx-accent: #709f5e;");

        HBox HBox1 = new HBox(name, level);
        HBox HBox2 = new HBox(HP, npcBar);
        HBox HBox3 = new HBox(HPs);

        HBox1.setSpacing(spacing * 10);
        HBox2.setSpacing(spacing);
        HBox3.setSpacing(spacing);

        HBox3.setAlignment(Pos.CENTER_LEFT);

        setPadding(new Insets(20));

        this.getChildren().addAll(HBox1, HBox2, HBox3);

        if(!pokemon.getStatus().equals(Status.normal)){
            this.getChildren().remove(HBox3);
            HBox statusHBox = new HBox(HPs, status);
            this.getChildren().add(statusHBox);
            statusHBox.setSpacing(spacing * 10);
        }
    }

    public void updateHPBars(){
        int currentHP = pokemon.getHP();
        npcBar.setProgress((double) currentHP / pokemon.getMaxHP());
        HPs.setText(currentHP + "/" + pokemon.getMaxHP());

        if(currentHP > pokemon.getMaxHP() / 2){
            npcBar.setStyle("-fx-accent: #709f5e;");
        } else if(currentHP > pokemon.getMaxHP() / 4){
            npcBar.setStyle("-fx-accent: #f68524;");
        } else {
            npcBar.setStyle("-fx-accent: #d81e1e;");
        }
    }
}
