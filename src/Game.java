import java.util.Scanner;

class Game{
    Player player;
    Board board;
    Scanner scanner;
    InputHandler inputHandler;

    Game() {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        player = new Player();
        board = new Board();
    }


    void start() {
        player.initPlayer(inputHandler);
        board.getSize(inputHandler);
        board.initBoard();
        board.shuffleBoard();

        while (true) {
            board.printBoard();
            int move = inputHandler.getInt("Enter the tile number to move (or 0 to quit): ");
            if (move == 0) {
                System.out.println("Goodbye, " + player.name + "!");
                break;
            }
            board.moveTile(move);

        }

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}