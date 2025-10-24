// This is the game file for Dot Box Game, extending the abstract Game class.
class DotBoxGame extends Game {
    private Player player2;

    DotBoxGame() {
        super();
        this.player  = new Player();
        this.player2 = new Player();
    }

    @Override
    public void start() {
        System.out.println("Dots & Boxes!");
        player.initPlayer(inputHandler); // initialize first player
        player2.name = inputHandler.getPlayerName("Player 2 name: "); // initialize second player

        board = new DotBoxBoard(2, 2);   // temporary board
        board.getSize(inputHandler);     // get real size from user

        int current = 0;                 // 0 = player1, 1 = player2
        int[] scores = new int[]{0, 0};

        printHelp();

        while (true) {
            board.printBoard();
            System.out.println("Score: " + player.name + "=" + scores[0] + ", " + player2.name + "=" + scores[1]);
            System.out.println("Turn: " + (current == 0 ? player.name : player2.name));

            // get move input
            Object[] move = inputHandler.getDotBoxMove(board);
            if (move[0].equals("q")) break;
            if (move[0].equals("h")) { printHelp(); continue; }

            int r = (int) move[0];
            int c = (int) move[1];
            char d = (char) move[2];

            // perform move
            int completed = ((DotBoxBoard) board).claimEdge(r, c, d, current);
            if (completed == -1) continue; // invalid edge, skip
            scores[current] += completed;

            // check if game finished
            if (((DotBoxBoard) board).isComplete()) {
                board.printBoard();
                System.out.println("Final Score: " + player.name + "=" + scores[0] + ", " + player2.name + "=" + scores[1]);
                if (scores[0] > scores[1]) System.out.println("Congrats, " + player.name + "!");
                else if (scores[1] > scores[0]) System.out.println("Congrats, " + player2.name + "!");
                else System.out.println("It's a tie!");
                break;
            }

            // switch turns only if no box was completed
            if (completed == 0) current = 1 - current;
        }
    }

    private void printHelp() {
        System.out.println("Commands: 'h' help, 'q' quit round, or claim an edge as 'r c d'.");
        System.out.println("Examples: 0 0 H   or   1,2,V");
        System.out.println("H edges: r in [0," + board.getRows() + "], c in [0," + (board.getCols() - 1) + "]");
        System.out.println("V edges: r in [0," + (board.getRows() - 1) + "], c in [0," + board.getCols() + "]");
    }
}