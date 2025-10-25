import java.util.*;

// Manages the Quoridor game board - handles walls, pawns, pathfinding, and board display
public class QuoridorBoard implements Board {

    private int rows;                      // Board height (9 for standard Quoridor)
    private int cols;                      // Board width (9 for standard Quoridor)
    private Space[][] spaces;              // 2D array of squares on the board
    private Set<QuoridorPiece> walls;     // Collection of all placed walls
    private List<QuoridorPlayer> players;  // Players in the game (needed to show pawn positions)

    // Create a new board with specified dimensions
    public QuoridorBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
        this.walls = new HashSet<>();
        this.players = new ArrayList<>();
        initBoard();
    }

    // Initialize all squares on the board
    @Override
    public void initBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                spaces[r][c] = new Square(r, c);
            }
        }
        System.out.println("Initialized Quoridor board (" + rows + "x" + cols + ")");
    }

    // Display the current board state with pawns and walls
    @Override
    public void printBoard() {
        System.out.println("\n=== Quoridor Board ===");

        // Print column numbers at the top
        System.out.print("   ");
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + c + "  ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            // Print the row of cells with pawn positions
            System.out.print(r + " ");
            for (int c = 0; c < cols; c++) {
                // Check if any player's pawn is at this position
                String cell = " . ";
                for (int p = 0; p < players.size(); p++) {
                    QuoridorPiece pawn = players.get(p).pawn;
                    if (pawn.getRow() == r && pawn.getCol() == c) {
                        cell = " P" + (p + 1) + " "; // P1 or P2
                        break;
                    }
                }
                System.out.print(cell);

                // Check for vertical wall to the right of this cell
                if (c < cols - 1) {
                    boolean hasVWall = hasVerticalWall(r, c + 1);
                    System.out.print(hasVWall ? "|" : " ");
                }
            }
            System.out.println();

            // Print horizontal walls below this row
            if (r < rows - 1) {
                System.out.print("  ");
                for (int c = 0; c < cols; c++) {
                    boolean hasHWall = hasHorizontalWall(r + 1, c);
                    System.out.print(hasHWall ? "--- " : "    ");
                }
                System.out.println();
            }
        }
        System.out.println("======================\n");
    }

    // Check if there's a vertical wall at a given position (blocks left-right movement)
    private boolean hasVerticalWall(int row, int col) {
        for (QuoridorPiece wall : walls) {
            if (wall.getOrientation() == QuoridorPiece.WallOrientation.VERTICAL) {
                // A vertical wall at (r, c) blocks movement between (r, c-1) and (r, c)
                if (wall.getCol() == col && wall.getRow() == row) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if there's a horizontal wall at a given position (blocks up-down movement)
    private boolean hasHorizontalWall(int row, int col) {
        for (QuoridorPiece wall : walls) {
            if (wall.getOrientation() == QuoridorPiece.WallOrientation.HORIZONTAL) {
                // A horizontal wall at (r, c) blocks movement between (r-1, c) and (r, c)
                if (wall.getRow() == row && wall.getCol() == col) {
                    return true;
                }
            }
        }
        return false;
    }

    // Board methods required by interface but not needed for Quoridor
    @Override
    public void getSize(InputHandler inputHandler) {
        System.out.println("Quoridor board is fixed 9x9.");
        this.rows = 9;
        this.cols = 9;
        this.spaces = new Space[rows][cols];
        initBoard();
    }

    @Override
    public void shuffleBoard() {
        System.out.println("Quoridor cannot be shuffled.");
    }

    @Override
    public boolean moveTile(int value) {
        System.out.println("MoveTile not used in Quoridor.");
        return false;
    }

    // --- Quoridor-specific methods ---

    // Register players so the board can display their pawns
    public void setPlayers(List<QuoridorPlayer> players) {
        this.players = players;
    }

    // Check if a pawn can move from one position to another (considering walls and adjacency)
    public boolean isPathClear(Coordinate from, Coordinate to) {
        // Check if the move is valid (adjacent or jump)
        int rowDiff = Math.abs(to.row() - from.row());
        int colDiff = Math.abs(to.col() - from.col());

        // Valid moves: adjacent (1 square) or jump (2 squares) in one direction, or diagonal (1,1)
        if (!((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) ||
                (rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2) ||
                (rowDiff == 1 && colDiff == 1))) {
            return false; // Not an adjacent or valid jump move
        }

        // Check bounds
        if (to.row() < 0 || to.row() >= rows || to.col() < 0 || to.col() >= cols) {
            return false;
        }

        // Check if there's a wall blocking the path
        return !isBlockedByWall(from, to);
    }

    // Check if a wall blocks movement between two cells
    private boolean isBlockedByWall(Coordinate from, Coordinate to) {
        int rowDiff = to.row() - from.row();
        int colDiff = to.col() - from.col();

        // Moving up (row decreases) - check for horizontal wall above
        if (rowDiff == -1 && colDiff == 0) {
            return hasHorizontalWall(from.row(), from.col());
        }
        // Moving down (row increases) - check for horizontal wall below
        if (rowDiff == 1 && colDiff == 0) {
            return hasHorizontalWall(to.row(), to.col());
        }
        // Moving left (col decreases) - check for vertical wall to the left
        if (rowDiff == 0 && colDiff == -1) {
            return hasVerticalWall(from.row(), from.col());
        }
        // Moving right (col increases) - check for vertical wall to the right
        if (rowDiff == 0 && colDiff == 1) {
            return hasVerticalWall(to.row(), to.col());
        }

        // For jumps (2 squares), check if both segments are clear
        if (Math.abs(rowDiff) == 2 || Math.abs(colDiff) == 2) {
            int midRow = (from.row() + to.row()) / 2;
            int midCol = (from.col() + to.col()) / 2;
            Coordinate mid = new Coordinate(midRow, midCol);
            // Check if both segments are clear
            return isBlockedByWall(from, mid) || isBlockedByWall(mid, to);
        }

        return false;
    }

    // Validate if a wall can be placed at the specified position
    public boolean isWallPlacementValid(QuoridorPiece wall) {
        QuoridorPiece.WallOrientation orientation = wall.getOrientation();

        // Check bounds: walls are placed between cells
        if (orientation == QuoridorPiece.WallOrientation.HORIZONTAL) {
            // Horizontal wall blocks vertical movement
            if (wall.getRow() < 1 || wall.getRow() >= rows || wall.getCol() < 0 || wall.getCol() >= cols - 1) {
                return false;
            }
        } else { // VERTICAL
            // Vertical wall blocks horizontal movement
            if (wall.getRow() < 0 || wall.getRow() >= rows - 1 || wall.getCol() < 1 || wall.getCol() >= cols) {
                return false;
            }
        }

        // Check if wall overlaps with existing walls
        for (QuoridorPiece existingWall : walls) {
            if (wallsOverlap(wall, existingWall)) {
                return false;
            }
        }

        // Temporarily add wall to check if it blocks any player's path to their goal
        walls.add(wall);
        boolean allPlayersHavePath = true;

        for (QuoridorPlayer player : players) {
            if (!hasPathToGoal(player)) {
                allPlayersHavePath = false;
                break;
            }
        }

        walls.remove(wall); // Remove temporary wall
        return allPlayersHavePath; // Wall is valid only if all players can still reach their goal
    }

    // Check if two walls overlap (occupy the same space)
    private boolean wallsOverlap(QuoridorPiece w1, QuoridorPiece w2) {
        if (w1.getOrientation() != w2.getOrientation()) {
            return false; // Different orientations can't overlap
        }

        if (w1.getOrientation() == QuoridorPiece.WallOrientation.HORIZONTAL) {
            // Horizontal walls span 2 columns
            return w1.getRow() == w2.getRow() && Math.abs(w1.getCol() - w2.getCol()) < 2;
        } else {
            // Vertical walls span 2 rows
            return w1.getCol() == w2.getCol() && Math.abs(w1.getRow() - w2.getRow()) < 2;
        }
    }

    // Use BFS (Breadth-First Search) to check if a player has a path to their goal
    private boolean hasPathToGoal(QuoridorPlayer player) {
        Coordinate start = new Coordinate(player.pawn.getRow(), player.pawn.getCol());
        int goalRow = player.goalLine.getTargetRow();

        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        // Explore all reachable squares using BFS
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            // Check if we reached the goal row
            if (current.row() == goalRow) {
                return true; // Path exists!
            }

            // Explore all 4 adjacent cells (up, down, left, right)
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newRow = current.row() + dir[0];
                int newCol = current.col() + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    Coordinate next = new Coordinate(newRow, newCol);

                    // Add to queue if not visited and not blocked by wall
                    if (!visited.contains(next) && !isBlockedByWall(current, next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        return false; // No path found
    }

    // Add a wall to the board
    public void placeWall(QuoridorPiece wall) {
        walls.add(wall);
    }

    // Getter methods
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}