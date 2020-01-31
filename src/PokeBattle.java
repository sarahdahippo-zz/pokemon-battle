import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import java.util.Random;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
* PokeBattle.java
*
* A Pokemon battle game
* @author Sarah
* @version 1.0.0
**/
public class PokeBattle extends Application {
    /**
     * The main method where the game is launched
     *
     * @param args an array of command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * implements the GUI-specific-code for the Pokemon battle
     *
     * @param stage a window
     **/
    @Override
    public void start(Stage stage) {
        Pokemon[] pokemons = {
            new Pokemon("Bulbasaur", 23, 45, 3, "GRASS", new Move[] {new Move("Growl", 5, "NORMAL"),
                new Move("Vine Whip", 12, "GRASS"), new Move("Leech Seed", 10, "GRASS"),
                new Move("Poison Powder", 7, "POISON")}),
            new Pokemon("Pikachu", 49, 35, 1, "ELECTRIC", new Move[] {new Move("Charm", 5, "FAIRY"),
                new Move("Whip", 10, "NORMAL"), new Move("ThunderBolt", 15, "ELECTRIC"),
                new Move("Quick Attack", 9, "NORMAL")}),
            new Pokemon("Squirtle", 36, 44, 3, "WATER", new Move[] {new Move("Water Gun", 12, "WATER"),
                new Move("Tail Whip", 14, "NORMAL"), new Move("Bite", 7, "GRASS"),
                new Move("Hydro Pump", 15, "WATER")}),
            new Pokemon("Eevee", 38, 50, 3, "NORMAL", new Move[] {new Move("Bite", 5, "NORMAL"),
                new Move("Whip", 10, "NORMAL"), new Move("Sand", 10, "GRASS"),
                new Move("Spit", 5, "WATER")})
        };
        Pokemon user = pokemons[3];
        ImageView userPic = new ImageView(new Image("eevee.png"));
        switchPoke(user, pokemons[3], userPic, "eevee.png");
        Label name = new Label(user.getName() + "  ");
        Label lv = new Label("Lv" + user.getLevel());
        Label type = new Label(user.getType());
        Label hp = new Label("HP: ");
        Label hpScore = new Label((int) user.getCurrentHP() + "/" + (int) user.getMaxHP());
        ProgressBar hpBar = new ProgressBar(user.getMaxHP());
        updateInfo(name, lv, type, hp, hpScore, hpBar, user);
        GridPane userInfo = new GridPane(); //user info pane
        styleInfo(userInfo, name, lv, type, hp, hpBar, hpScore);

        Pokemon opponent = new Pokemon("Psyduck", 54, 50, 3, "WATER", new Move[] {new Move("Surf", 7, "WATER"),
            new Move("Water Gun", 9, "WATER"), new Move("Confusion", 12, "PSYCHIC"),
            new Move("Scratch", 5, "NORMAL")});
        ImageView oppPic = new ImageView(new Image("psyduck.png"));
        switchPoke(opponent, opponent, oppPic, "psyduck.png");
        Label oName = new Label(opponent.getName() + "  ");
        Label oLv = new Label("Lv" + opponent.getLevel());
        Label oType = new Label(opponent.getType());
        Label oHp = new Label("HP: ");
        Label oHpScore = new Label((int) opponent.getCurrentHP() + "/" + (int) opponent.getMaxHP());
        ProgressBar oHpBar = new ProgressBar(opponent.getMaxHP());
        updateInfo(oName, oLv, oType, oHp, oHpScore, oHpBar, opponent);
        GridPane oppInfo = new GridPane(); //opponent info pane
        styleInfo(oppInfo, oName, oLv, oType, oHp, oHpBar, oHpScore);
        oHpScore.setVisible(false);

        Text instructions = new Text("\nWhat should " + user.getName() + " do?");
        Button fight = new Button("FIGHT");
        Button bag = new Button("BAG");
        Button pokemon = new Button("POKEMON");
        Button run = new Button("RUN");
        styleButtons(fight, bag, pokemon, run);
        VBox startBox = new VBox(10, new HBox(10, fight, bag), new HBox(10, pokemon, run));
        HBox myPokeTop = new HBox(150, userPic, userInfo); //user pic and info
        HBox myPokeBottom = new HBox(130, instructions, startBox); //bottom panel
        VBox myPoke = new VBox(20, myPokeTop, myPokeBottom); //entire user pane
        HBox oppPoke = new HBox(150, oppInfo, oppPic); //opponent pic and info

        Button move1 = new Button();
        Button move2 = new Button();
        Button move3 = new Button();
        Button move4 = new Button();
        VBox moveBox = newMoveBox(user, move1, move2, move3, move4); //move buttons
        Button poke1 = new Button(pokemons[0].getName().toUpperCase());
        Button poke2 = new Button(pokemons[1].getName().toUpperCase());
        Button poke3 = new Button(pokemons[2].getName().toUpperCase());
        Button poke4 = new Button(pokemons[3].getName().toUpperCase());
        styleButtons(poke1, poke2, poke3, poke4);
        VBox pokeBox = new VBox(10, new HBox(10, poke1, poke2), new HBox(10, poke3, poke4)); //Pokemons to choose

        BorderPane background = new BorderPane(null, oppPoke, null, myPoke, null);
        styleBackground(background, myPoke, oppPoke);
        startScreen(instructions, user, startBox, userPic, userInfo, background);

        fight.setOnAction(event -> fightChosen(instructions, newMoveBox(user, move1, move2, move3, move4),
                                                myPokeTop, userPic, userInfo, background));
        pokemon.setOnAction(evt -> pokemonChosen(instructions, pokeBox, myPokeTop, userPic, userInfo, background));
        poke1.setOnAction(evt -> {
                switchPoke(user, pokemons[0], userPic, "bulbasaur.png");
                updateInfo(name, lv, type, hp, hpScore, hpBar, user);
                startScreen(instructions, user, startBox, userPic, userInfo, background);
            }
        );
        poke2.setOnAction(evt -> {
                switchPoke(user, pokemons[1], userPic, "pikachu.png");
                updateInfo(name, lv, type, hp, hpScore, hpBar, user);
                startScreen(instructions, user, startBox, userPic, userInfo, background);
            }
        );
        poke3.setOnAction(evt -> {
                switchPoke(user, pokemons[2], userPic, "squirtle.png");
                updateInfo(name, lv, type, hp, hpScore, hpBar, user);
                startScreen(instructions, user, startBox, userPic, userInfo, background);
            }
        );
        poke4.setOnAction(evt -> {
                switchPoke(user, new Pokemon("Eevee", 38, 50, 3, "NORMAL", new Move[] {
                    new Move("Bite", 5, "NORMAL"), new Move("Whip", 10, "NORMAL"),
                    new Move("Sand", 10, "GRASS"), new Move("Spit", 5, "WATER")}), userPic, "eevee.png");
                updateInfo(name, lv, type, hp, hpScore, hpBar, user);
                startScreen(instructions, user, startBox, userPic, userInfo, background);
            }
        );
        run.setOnAction(event -> {
                instructions.setText("\nGot away safely!");
                userPic.setVisible(false);
                startBox.setVisible(false);
                delayExit(2);
            }
        );
        move1.setOnAction(event -> {
                instructions.setText("\n" + user.getName() + " used " + user.getMoves()[0].getName() + "!");
                updateMoves(user, move1, move2, move3, move4);
                flash(oppPic);
                moveChosen(user, opponent, instructions, hpScore, userPic, oHpBar, hpBar);
                delay(user, instructions, startBox, userPic, userInfo, background);
            }
        );
        move2.setOnAction(event -> {
                instructions.setText("\n" + user.getName() + " used " + user.getMoves()[1].getName() + "!");
                updateMoves(user, move1, move2, move3, move4);
                flash(oppPic);
                moveChosen(user, opponent, instructions, hpScore, userPic, oHpBar, hpBar);
                delay(user, instructions, startBox, userPic, userInfo, background);
            }
        );
        move3.setOnAction(event -> {
                instructions.setText("\n" + user.getName() + " used " + user.getMoves()[2].getName() + "!");
                updateMoves(user, move1, move2, move3, move4);
                flash(oppPic);
                moveChosen(user, opponent, instructions, hpScore, userPic, oHpBar, hpBar);
                delay(user, instructions, startBox, userPic, userInfo, background);
            }
        );
        move4.setOnAction(event -> {
                instructions.setText("\n" + user.getName() + " used " + user.getMoves()[3].getName() + "!");
                updateMoves(user, move1, move2, move3, move4);
                flash(oppPic);
                moveChosen(user, opponent, instructions, hpScore, userPic, oHpBar, hpBar);
                delay(user, instructions, startBox, userPic, userInfo, background);
            }
        );

        Scene scene = new Scene(background);
        stage.setTitle("PokeBattle!");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * changes buttons when "Pokemon" button is selected
     * @param u        user Pokemon
     * @param t        description of battle
     * @param h        HBox myPokeTop
     * @param v        VBox startBox
     * @param iU       image of user Pokemon
     * @param b        BorderPane background
     * @param pokemons array of Pokemon
     */
    private Object[] pokeSelect(Pokemon u, Text t, HBox h, VBox v, ImageView iU, BorderPane b, Pokemon[] pokemons) {
        Button poke1 = new Button(pokemons[0].getName().toUpperCase());
        Button poke2 = new Button(pokemons[1].getName().toUpperCase());
        Button poke3 = new Button(pokemons[2].getName().toUpperCase());
        Button poke4 = new Button(pokemons[3].getName().toUpperCase());
        styleButtons(poke1, poke2, poke3, poke4);
        HBox pokeRow1 = new HBox(10, poke1, poke2);
        HBox pokeRow2 = new HBox(10, poke3, poke4);
        VBox pokeBox = new VBox(10, pokeRow1, pokeRow2);
        Object[] arr = {poke1, poke2, poke3, poke4, pokeBox};
        return arr;
    }
    /**
     * changes Pokemon
     * @param u       user Pokemon
     * @param newPoke new Pokemon to switch to
     * @param iU      ImageView userPic
     * @param s       name of image
     */
    private void switchPoke(Pokemon u, Pokemon newPoke, ImageView iU, String s) {
        u.setName(newPoke.getName());
        u.setLevel(newPoke.getLevel());
        u.setMaxHP(newPoke.getMaxHP());
        u.setAtk(newPoke.getAtk());
        u.setType(newPoke.getType());
        u.setCurrentHP(newPoke.getCurrentHP());
        u.setMoves(newPoke.getMoves());
        iU.setImage(new Image(s));
        iU.setFitWidth(200);
        iU.setPreserveRatio(true);
    }
    /**
     * updates user info
     * @param n  name label
     * @param l  level label
     * @param t  type label
     * @param h  hp label
     * @param hp hp score label
     * @param p  hp progress bar
     * @param u  user Pokemon
     */
    private void updateInfo(Label n, Label l, Label t, Label h, Label hp, ProgressBar p, Pokemon u) {
        n.setText(u.getName() + "  ");
        n.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
        l.setText("Lv" + u.getLevel());
        l.setFont(new Font("Consolas", 20));
        t.setText(u.getType());
        t.setFont(new Font("Consolas", 20));
        t.setTextFill(Color.RED);
        h.setText("HP: ");
        h.setFont(new Font("Consolas", 20));
        hp.setText((int) u.getCurrentHP() + "/" + (int) u.getMaxHP());
        hp.setFont(new Font("Consolas", 18));
        hp.setTextFill(Color.BLUE);
        p.setProgress(u.getMaxHP());
        p.setStyle("-fx-accent: limegreen");
        u.setCurrentHP(u.getMaxHP());
    }
    /**
     * updates moves of Pokemon
     * @param  user  user Pokemon
     * @param  move1 move 1
     * @param  move2 move 2
     * @param  move3 move 3
     * @param  move4 move 4
     * @return moveBox panel of the 4 move buttons
     */
    private VBox newMoveBox(Pokemon user, Button move1, Button move2, Button move3, Button move4) {
        move1.setText(user.getMoves()[0].getName().toUpperCase());
        move2.setText(user.getMoves()[1].getName().toUpperCase());
        move3.setText(user.getMoves()[2].getName().toUpperCase());
        move4.setText(user.getMoves()[3].getName().toUpperCase());
        styleButtons(move1, move2, move3, move4);
        HBox moveRow1 = new HBox(10, move1, move2);
        HBox moveRow2 = new HBox(10, move3, move4);
        VBox moveBox = new VBox(10, moveRow1, moveRow2);
        return moveBox;
    }
    /**
     * updates moves of Pokemon
     * @param  user  user Pokemon
     * @param  move1 move 1
     * @param  move2 move 2
     * @param  move3 move 3
     * @param  move4 move 4
     */
    private void updateMoves(Pokemon user, Button move1, Button move2, Button move3, Button move4) {
        move1.setText(user.getMoves()[0].getName().toUpperCase());
        move2.setText(user.getMoves()[1].getName().toUpperCase());
        move3.setText(user.getMoves()[2].getName().toUpperCase());
        move4.setText(user.getMoves()[3].getName().toUpperCase());
        styleButtons(move1, move2, move3, move4);
        HBox moveRow1 = new HBox(10, move1, move2);
        HBox moveRow2 = new HBox(10, move3, move4);
        VBox moveBox = new VBox(10, moveRow1, moveRow2);
        moveBox.setVisible(false);
    }
    /**
     * delays 2 seconds before exiting
     * @param t time of delay in seconds
     */
    private void delayExit(int t) {
        PauseTransition delay = new PauseTransition(Duration.seconds(t));
        delay.setOnFinished(e -> {
                System.exit(0);
            }
        );
        delay.play();
    }
    /**
     * delay between attack scene and original scene
     * @param u user Pokemon
     * @param t description of battle
     * @param v VBox startBox
     * @param i ImageView of user Pokemon
     * @param g GridPane userInfo
     * @param b BorderPane background
     */
    private void delay(Pokemon u, Text t, VBox v, ImageView i, GridPane g, BorderPane b) {
        PauseTransition delay = new PauseTransition(Duration.seconds(8));
        delay.setOnFinished(e -> {
                startScreen(t, u, v, i, g, b);
            }
        );
        delay.play();
    }
    /**
     * defines what happens when fight is selected
     * @param t description of battle
     * @param u user Pokemon
     * @param v VBox startBox
     * @param i ImageView of user Pokemon
     * @param g GridPane userInfo
     * @param b BorderPane background
     */
    private void startScreen(Text t, Pokemon u, VBox v, ImageView i, GridPane g, BorderPane b) {
        t.setText("\nWhat should " + u.getName() + " do?");
        t.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
        HBox myPokeTop = new HBox(150, i, g);
        HBox myPokeBottom = new HBox(80, t, v);
        VBox myPoke = new VBox(20, myPokeTop, myPokeBottom);
        b.setBottom(myPoke);
        b.setMargin(myPoke, new Insets(20, 20, 20, 60));
    }
    /**
     * styles background
     * @param b background
     * @param v myPoke
     * @param h oppPoke
     */
    private void styleBackground(BorderPane b, VBox v, HBox h) {
        b.setPrefSize(700, 500);
        String image = PokeBattle.class.getResource("background.png").toExternalForm();
        b.setStyle("-fx-background-image: url('" + image + "');" + "-fx-background-size: 708 570");
        b.setMargin(v, new Insets(20, 20, 20, 60));
        b.setMargin(h, new Insets(20, 20, 20, 60));
    }
    /**
     * styles a button
     * @param b button
     */
    private void styleButton(Button b) {
        b.setMinWidth(130);
        b.setFont(Font.font("Consolas", FontWeight.BOLD, 15));
        b.setStyle("-fx-border-width: 8");
        b.setStyle("-fx-border-color: red");
    }
    /**
     * styles buttons
     * @param b1 button 1
     * @param b2 button 2
     * @param b3 button 3
     * @param b4 button 4
     */
    private void styleButtons(Button b1, Button b2, Button b3, Button b4) {
        styleButton(b1);
        styleButton(b2);
        styleButton(b3);
        styleButton(b4);
    }
    /**
     * changes color of progress bars depending on HP
     * @param p ProgressBar of user or opponent
     */
    private void styleBar(ProgressBar p) {
        if (p.getProgress() > 0.5) {
            p.setStyle("-fx-accent: limegreen");
        } else if (p.getProgress() > 0.3) {
            p.setStyle("-fx-accent: orange");
        } else {
            p.setStyle("-fx-accent: red");
        }
    }
    /**
     * styles the information pane
     * @param p  GridPane info panel
     * @param n  name
     * @param l  level
     * @param t  type
     * @param h  hp
     * @param b  hpBar
     * @param hp hpScore
     */
    private void styleInfo(GridPane p, Label n, Label l, Label t, Label h, ProgressBar b, Label hp) {
        p.add(n, 0, 0);
        p.add(l, 1, 0);
        p.add(t, 0, 1);
        p.add(h, 1, 1);
        p.add(b, 2, 1);
        p.add(hp, 1, 2);
    }
    /**
     * defines what happens when fight is selected
     * @param t description of battle
     * @param v VBox moveBox
     * @param h HBox myPokeTop
     * @param i userPic
     * @param g GridPane userInfo
     * @param b BorderPane background
     */
    private void fightChosen(Text t, VBox v, HBox h, ImageView i, GridPane g, BorderPane b) {
        t.setText("\nChoose a move.");
        v.setVisible(true);
        HBox myPokeTop = new HBox(150, i, g);
        HBox myPokeBottom = new HBox(160, t, v);
        VBox myPoke = new VBox(20, myPokeTop, myPokeBottom);
        b.setBottom(myPoke);
        b.setMargin(myPoke, new Insets(20, 20, 20, 60));
    }
    /**
     * defines what happens when pokemon is selected
     * @param t description of battle
     * @param v VBox pokeBox
     * @param h HBox myPokeTop
     * @param i userPic
     * @param g GridPane userInfo
     * @param b BorderPane background
     */
    private void pokemonChosen(Text t, VBox v, HBox h, ImageView i, GridPane g, BorderPane b) {
        t.setText("\nChoose a Pokemon.");
        v.setVisible(true);
        HBox myPokeTop = new HBox(150, i, g);
        HBox myPokeBottom = new HBox(160, t, v);
        VBox myPoke = new VBox(20, myPokeTop, myPokeBottom);
        b.setBottom(myPoke);
        b.setMargin(myPoke, new Insets(20, 20, 20, 60));
    }
    /**
     * flashes an image
     * @param i image of Pokemon
     */
    private void flash(ImageView i) {
        Timeline flash = new Timeline(new KeyFrame(Duration.seconds(0.1),
                                        evt -> i.setVisible(false)),
                            new KeyFrame(Duration.seconds(0.4),
                            evt -> i.setVisible(true)));
        flash.setCycleCount(3);
        flash.play();
    }
    /**
     * defines what happens when each move is selected
     * @param u  user Pokemon
     * @param o  opponent Pokemon
     * @param t  description of battle
     * @param l  label hpScore
     * @param iU user's Pokemon image
     * @param pO opponent's health progress bar
     * @param pU user's health progress bar
     */
    private void moveChosen(Pokemon u, Pokemon o, Text t, Label l,
                            ImageView iU, ProgressBar pO, ProgressBar pU) {
        calcDamage(u, o, pO);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
                oppAttk(o, t);
                Timeline wait = new Timeline(new KeyFrame(Duration.seconds(1.5), evt -> {
                        flash(iU);
                        calcDamage(o, u, pU);
                        l.setText((int) u.getCurrentHP() + "/" + (int) u.getMaxHP());
                    }
                ));
                wait.play();
            }
        );
        delay.play();
        hpBelow0(u, o, t);
        styleBar(pU);
        styleBar(pO);
    }
    /**
     * calculates damage inflicted on opponent
     * @param u user Pokemon
     * @param o opponent Pokemon
     * @param p opponent's health progress bar
     */
    private void calcDamage(Pokemon u, Pokemon o, ProgressBar p) {
        double damagePower = (u.getMoves())[0].getPower() * u.getAtk();
        double damage = damagePower * u.compareType((u.getMoves())[0]);
        o.setCurrentHP(o.getCurrentHP() - damage);
        p.setProgress(o.getCurrentHP() / o.getMaxHP());
    }
    /**
     * represents opponent's attack on user
     * @param o opponent's Pokemon
     * @param t description of battle
     */
    private void oppAttk(Pokemon o, Text t) {
        Random ran = new Random();
        int rand = ran.nextInt(4);
        String randMove = o.getMoves()[rand].getName();
        t.setText("\n" + o.getName() + " used " + randMove + "!");
    }
    /**
     * checks if Pokemon has fainted
     * @param u user Pokemon
     * @param o opponent Pokemon
     * @param t description of battle
     */
    private void hpBelow0(Pokemon u, Pokemon o, Text t) {
        if (u.isFainted()) {
            t.setText("\n" + u.getName() + " fainted!");
            delayExit(3);
        }
        if (o.isFainted()) {
            t.setText("\n" + o.getName() + " fainted!");
            delayExit(3);
        }
    }
}
