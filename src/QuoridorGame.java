import java.util.*;

public class QuoridorGame extends Game {

    private final QuoridorBoard board;
    private final List<QuoridorPlayer> players;
    private QuoridorPlayer currentPlayer;
    private GameState state;

    public QuoridorGame() {
        super();
        this.board = new QuoridorBoard(9, 9);
        this.players = new ArrayList<>();
        this.state = GameState.START;
    }

    @Override
    public void start() {
        System.out.println("=== Welcome to Quoridor! ===");
        initPlayer();

        // Create 2 players with opposite goals
        QuoridorPlayer p1 = new QuoridorPlayer("Player 1");
        QuoridorPlayer p2 = new QuoridorPlayer("Player 2");

        p1.pawn = new Pawn(new Coordinate(8, 4));
        p1.goalLine = new GoalLine(0);

        p2.pawn = new Pawn(new Coordinate(0, 4));
        p2.goalLine = new GoalLine(8);

        players.add(p1);
        players.add(p2);
        currentPlayer = p1;

        // Register players with the board
        board.setPlayers(players);

        System.out.println("Players ready: " + p1.name + " and " + p2.name);
        System.out.println("Starting game...");
        state = GameState.PLAYING;

        board.printBoard();

        while (state == GameState.PLAYING) {
            System.out.println("\n" + currentPlayer.name + "'s turn.");
            System.out.println("Walls remaining: " + currentPlayer.wallsRemaining);

            String action = inputHandler.getAction("Move (M) or Place Wall (W)? ");

            if (action.equalsIgnoreCase("M")) {
                handleMove(currentPlayer);
            } else if (action.equalsIgnoreCase("W")) {
                handleWallPlacement(currentPlayer);
            } else {
                System.out.println("Invalid action. Try again.");
                continue;
            }

            if (checkForWin()) {
                System.out.println(currentPlayer.name + " wins!");
                state = GameState.GAME_OVER;
                break;
            }

            endTurn();
        }

        System.out.println("Game over!");
    }

    @Override
    public void initPlayer() {
        player.name = inputHandler.getPlayerName("Please enter your name for Quoridor: ");
    }

    private void handleMove(QuoridorPlayer player) {
        System.out.println("Enter your move coordinates (row and col): ");
        int row = inputHandler.getInt("Row: ");
        int col = inputHandler.getInt("Col: ");

        Coordinate newPosition = new Coordinate(row, col);
        movePawn(player, newPosition);
        board.printBoard();
    }

    private void handleWallPlacement(QuoridorPlayer player) {
        if (!player.hasWalls()) {
            System.out.println("You have no walls left!");
            return;
        }

        System.out.println("Enter wall start coordinates (row and col): ");
        int row = inputHandler.getInt("Row: ");
        int col = inputHandler.getInt("Col: ");
        String dir = inputHandler.getDirection("Orientation (H/V): ");

        Wall wall = new Wall(new Coordinate(row, col),
                dir.equalsIgnoreCase("H") ? Orientation.HORIZONTAL : Orientation.VERTICAL);

        placeWall(player, wall);
        board.printBoard();
    }

    public void endTurn() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }

    public void movePawn(QuoridorPlayer player, Coordinate newPosition) {
        Pawn pawn = player.pawn;
        Coordinate currentPos = pawn.getPosition();

        if (board.isPathClear(currentPos, newPosition)) {
            pawn.moveTo(newPosition);
            System.out.println(player.name + " moved pawn to " + newPosition);
        } else {
            System.out.println("Invalid move: path blocked.");
        }
    }

    public void placeWall(QuoridorPlayer player, Wall wall) {
        if (board.isWallPlacementValid(wall)) {
            board.placeWall(wall);
            player.wallsRemaining--;
            System.out.println(player.name + " placed a wall at " + wall.getStart() +
                    " (" + wall.getOrientation() + ")");
        } else {
            System.out.println("Invalid wall placement.");
        }
    }

    public boolean checkForWin() {
        Coordinate pos = currentPlayer.pawn.getPosition();
        return currentPlayer.goalLine.isReached(pos);
    }
}