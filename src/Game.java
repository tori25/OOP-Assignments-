// Base abstract class for all games - defines common structure and behavior
// Concrete game classes (PuzzleGame, DotBoxGame, QuoridorGame) extend this
import java.util.Scanner;

abstract class Game {
    protected Board board;
    protected Player player;
    protected Scanner scanner;
    protected InputHandler inputHandler;

    // Initialize common game components
    Game() {
        scanner = new Scanner(System.in);         // Read from console
        inputHandler = new InputHandler(scanner); // Validate user input
        player = new Player();                    // Create default player
    }

    // Abstract methods that each game must implement
    abstract void start();       // Main game loop
    abstract void initPlayer();  // Set up player(s) for the game
}