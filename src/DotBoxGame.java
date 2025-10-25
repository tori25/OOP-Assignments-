class DotBoxGame extends Game {

    private final Player player2;  // second player

    DotBoxGame() {
        super(); // initializes inputHandler, scanner, and player (from Game)
        this.player = new Player();
        this.player2 = new Player();
    }

    @Override
    public void start() {
        System.out.println("=== Welcome to Dots & Boxes! ===");

        // initialize players
        initPlayer(); // player 1
        player2.name = inputHandler.getPlayerName("Player 2 name: ");

        // initialize board
        board = new DotBoxBoard(2, 2);   // temporary 2x2 grid
        board.getSize(inputHandler);     // ask user for board size

        int current = 0;                 // 0 = player1, 1 = player2
        int[] scores = new int[]{0, 0};  // track player scores

        printHelp();

        // Main game loop
        while (true) {
            board.printBoard();
            System.out.println("Score: " + player.name + "=" + scores[0] +
                    ", " + player2.name + "=" + scores[1]);
            System.out.println("Turn: " + (current == 0 ? player.name : player2.name));

            // get player move
            Object[] move = inputHandler.getDotBoxMove(board);
            if (move[0].equals("q")) break;   // quit game
            if (move[0].equals("h")) { printHelp(); continue; }

            int r = (int) move[0];
            int c = (int) move[1];
            char d = (char) move[2];

            // perform move
            int completed = ((DotBoxBoard) board).claimEdge(r, c, d, current);
            if (completed == -1) continue; // invalid or duplicate edge
            scores[current] += completed;

            // check for game completion
            if (((DotBoxBoard) board).isComplete()) {
                board.printBoard();
                System.out.println("\n=== Final Score ===");
                System.out.println(player.name + ": " + scores[0]);
                System.out.println(player2.name + ": " + scores[1]);
                if (scores[0] > scores[1]) System.out.println("🏆 Congrats, " + player.name + "!");
                else if (scores[1] > scores[0]) System.out.println("🏆 Congrats, " + player2.name + "!");
                else System.out.println("🤝 It's a tie!");
                break;
            }

            // switch turns only if no box was completed
            if (completed == 0) current = 1 - current;
        }

        System.out.println("Game over!");
    }

    @Override
    public void initPlayer() {
        player.name = inputHandler.getPlayerName(
                "Hello! Welcome to the Dot & Box Game!\nWhat's your name? "
        );
    }

    private void printHelp() {
        System.out.println("\n=== Commands Help ===");
        System.out.println(" - 'h' → Show help");
        System.out.println(" - 'q' → Quit the game");
        System.out.println(" - Claim an edge as: r c d  (e.g. 0 0 H or 1,2,V)");
        System.out.println("H edges: r in [0," + board.getRows() + "], c in [0," + (board.getCols() - 1) + "]");
        System.out.println("V edges: r in [0," + (board.getRows() - 1) + "], c in [0," + board.getCols() + "]");
        System.out.println("=========================");
    }
}