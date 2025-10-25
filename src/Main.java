// Entry point for the game application - lets user choose between Puzzle, DotBox, or Quoridor
public class Main {
    public static void main(String[] args) {
        // Set up scanner and input handler for user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);

        // Display menu and get user's game choice
        int choice = inputHandler.getInt(
                "Hello! Welcome to Assignment 3! Let's play!\n" +
                        "Please choose a game:\n" +
                        "1 = Puzzle\n" +
                        "2 = DotBox\n" +
                        "3 = Quoridor \n"
        );

        Game game;

        // Initialize the selected game based on user choice
        if (choice == 1) {
            game = new PuzzleGame();
        } else if (choice == 2) {
            game = new DotBoxGame();
        } else if (choice == 3) {
            game = new QuoridorGame();
        } else {
            System.out.println("Invalid choice. Defaulting to Puzzle Game.");
            game = new PuzzleGame();
        }

        // Start the selected game
        game.start();
    }
}