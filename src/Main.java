// In this Main class, the user chooses between two games, and the program starts the selected one
public class Main {
    public static void main(String[] args) {
        Game game = (new InputHandler(new java.util.Scanner(System.in))) //creating new obj. game
                .getInt("Welcome! Let's play! Choose a game: 1 = Puzzle, 2 = DotBox  ") == 1 // if else condition
                ? new PuzzleGame()
                : new DotBoxGame();
        game.start(); // game starter
    }
}