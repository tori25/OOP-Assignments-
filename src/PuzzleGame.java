// This is the game file for Puzzle Game, extending the abstract Game class.
class PuzzleGame extends Game {

    PuzzleGame() {
        super();
        player =  new Player();
    }

    @Override
    void start() {
        initPlayer();

        // ask for size FIRST
        int[] size = inputHandler.getBoardSize("Puzzle size (rows, cols): ");
        int rows = size[0];
        int cols = size[1];

        // NOW create the board
        board = new PuzzleBoard(rows, cols);

        // initialize + shuffle
        board.initBoard();
        board.shuffleBoard();

        while (true) { //runs always
            board.printBoard();
            int move = inputHandler.getInt("Enter the tile number to move (or 0 to quit): ");
            if (move == 0) {
                System.out.println("Goodbye, " + player.name + "!");
                break;
            }
            board.moveTile(move);
        }
    }

    @Override
    public void initPlayer() {
        player.name = inputHandler.getPlayerName(
                "Hello! Welcome to the Puzzle Game!\n" +
                        "What's your name? "
        );
    }
}