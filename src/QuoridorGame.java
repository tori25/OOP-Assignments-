import java.util.*;

// Main game logic for Quoridor - manages game flow, turns, and win conditions
public class QuoridorGame extends Game {

    private final QuoridorBoard board;      // The 9x9 game board
    private final List<QuoridorPlayer> players; // List of players (2 players)
    private QuoridorPlayer currentPlayer;   // Whose turn it is
    private GameState state;                // Current game state (START, PLAYING, GAME_OVER)

    // Initialize the game with a 9x9 board and empty player list
    public QuoridorGame() {
        super();
        this.board = new QuoridorBoard(9, 9);
        this.players = new ArrayList<>();
        this.state = GameState.START;
    }

    // Main game loop - setup players, then alternate turns until someone wins
    @Override
    public void start() {
        System.out.println("=== Welcome to Quoridor! ===");
        initPlayer();

        // Create 2 players with opposite starting positions and goals
        QuoridorPlayer p1 = new QuoridorPlayer("Player 1");
        QuoridorPlayer p2 = new QuoridorPlayer("Player 2");

        // Player 1 starts at bottom (row 8) and needs to reach top (row 0)
        p1.pawn = new QuoridorPiece(p1, 8, 4);
        p1.goalLine = new GoalLine(0);

        // Player 2 starts at top (row 0) and needs to reach bottom (row 8)
        p2.pawn = new QuoridorPiece(p2, 0, 4);
        p2.goalLine = new GoalLine(8);

        players.add(p1);
        players.add(p2);
        currentPlayer = p1;

        // Register players with the board so it knows where pawns are
        board.setPlayers(players);

        System.out.println("Players ready: " + p1.name + " and " + p2.name);
        System.out.println("Starting game...");
        state = GameState.PLAYING;

        board.printBoard();

        // Main game loop - continue until someone wins
        while (state == GameState.PLAYING) {
            System.out.println("\n" + currentPlayer.name + "'s turn.");
            System.out.println("Walls remaining: " + currentPlayer.wallsRemaining);

            // Ask player to move pawn or place wall
            String action = inputHandler.getAction("Move (M) or Place Wall (W)? ");

            if (action.equalsIgnoreCase("M")) {
                handleMove(currentPlayer);
            } else if (action.equalsIgnoreCase("W")) {
                handleWallPlacement(currentPlayer);
            } else {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            // Check if current player reached their goal
            if (checkForWin()) {
                System.out.println(currentPlayer.name + " wins!");
                state = GameState.GAME_OVER;
                break;
            }

            endTurn(); // Switch to the other player
        }

        System.out.println("Game over!");
    }

    // Get player name from user input
    @Override
    public void initPlayer() {
        player.name = inputHandler.getPlayerName("Please enter your name for Quoridor: ");
    }

    // Handle pawn movement - get coordinates and validate move
    private void handleMove(QuoridorPlayer player) {
        System.out.println("Enter your move coordinates (row and col): ");
        int row = inputHandler.getInt("Row: ");
        int col = inputHandler.getInt("Col: ");

        Coordinate newPosition = new Coordinate(row, col);
        movePawn(player, newPosition);
        board.printBoard();
    }

    // Handle wall placement - get position, orientation, and validate placement
    private void handleWallPlacement(QuoridorPlayer player) {
        if (!player.hasWalls()) {
            System.out.println("You have no walls left!");
            return;
        }

        System.out.println("Enter wall start coordinates (row and col): ");
        int row = inputHandler.getInt("Row: ");
        int col = inputHandler.getInt("Col: ");
        String dir = inputHandler.getDirection("Orientation (H/V): ");

        // Create wall piece with specified orientation
        QuoridorPiece wall = new QuoridorPiece(player, row, col,
                dir.equalsIgnoreCase("H") ? QuoridorPiece.WallOrientation.HORIZONTAL : QuoridorPiece.WallOrientation.VERTICAL);

        placeWall(player, wall);
        board.printBoard();
    }

    // Switch to the next player's turn
    public void endTurn() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }

    // Attempt to move a player's pawn to a new position
    public void movePawn(QuoridorPlayer player, Coordinate newPosition) {
        QuoridorPiece pawn = player.pawn;
        Coordinate currentPos = new Coordinate(pawn.getRow(), pawn.getCol());

        // Check if path is clear (no walls blocking) before moving
        if (board.isPathClear(currentPos, newPosition)) {
            player.movePawn(newPosition);
            System.out.println(player.name + " moved pawn to " + newPosition);
        } else {
            System.out.println("Invalid move: path blocked.");
        }
    }

    // Attempt to place a wall on the board
    public void placeWall(QuoridorPlayer player, QuoridorPiece wall) {
        // Validate: wall must be in bounds, not overlap, and not block all paths to goal
        if (board.isWallPlacementValid(wall)) {
            board.placeWall(wall);
            player.wallsRemaining--;
            System.out.println(player.name + " placed a wall at (" + wall.getRow() + ", " + wall.getCol() + ")" +
                    " (" + wall.getOrientation() + ")");
        } else {
            System.out.println("Invalid wall placement.");
        }
    }

    // Check if current player reached their goal row
    public boolean checkForWin() {
        Coordinate pos = new Coordinate(currentPlayer.pawn.getRow(), currentPlayer.pawn.getCol());
        return currentPlayer.goalLine.isReached(pos);
    }
}