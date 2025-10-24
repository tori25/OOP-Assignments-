// Base abstract class for all games. Concrete classes extend Game
import java.util.Scanner;

abstract class Game {
    protected Board board;
    protected Player player;
    protected Scanner scanner;
    protected InputHandler inputHandler;

    Game() { //Game constructor
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        player = new Player();
    }

    abstract void start();

    abstract void initPlayer();
}