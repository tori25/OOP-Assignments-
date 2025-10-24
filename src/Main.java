// In this Main class, the user chooses between two games, and the program starts the selected one
public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);

        int choice = inputHandler.getInt(
                "Hello! Welcome to Assignment 3! Let's play!\n" +
                        "Please choose a game:\n" +
                        "1 = Puzzle\n" +
                        "2 = DotBox\n" +
                        "3 = Quoridor \n"
        );

        Game game;
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
        game.start();
    }
}