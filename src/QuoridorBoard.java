import java.util.*;

public class QuoridorBoard implements Board {

    private int rows;
    private int cols;
    private Space[][] spaces;
    private Set<Wall> walls;
    private List<QuoridorPlayer> players;

    public QuoridorBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
        this.walls = new HashSet<>();
        this.players = new ArrayList<>();
        initBoard();
    }

    @Override
    public void initBoard() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                spaces[r][c] = new Space(r, c);
            }
        }
        System.out.println("Initialized Quoridor board (" + rows + "x" + cols + ")");
    }

    @Override
    public void printBoard() {
        System.out.println("\n=== Quoridor Board ===");
        System.out.print("   ");
        for (int c = 0; c < cols; c++) {
            System.out.print(" " + c + "  ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            // Print the row of cells
            System.out.print(r + " ");
            for (int c = 0; c < cols; c++) {
                // Check if any player's pawn is at this position
                String cell = " . ";
                for (int p = 0; p < players.size(); p++) {
                    Coordinate pawnPos = players.get(p).pawn.getPosition();
                    if (pawnPos.row() == r && pawnPos.col() == c) {
                        cell = " P" + (p + 1) + " ";
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

    // Check if there's a vertical wall at a given position
    private boolean hasVerticalWall(int row, int col) {
        for (Wall wall : walls) {
            if (wall.getOrientation() == Orientation.VERTICAL) {
                Coordinate start = wall.getStart();
                // A vertical wall at (r, c) blocks movement between (r, c-1) and (r, c)
                if (start.col() == col && start.row() == row) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if there's a horizontal wall at a given position
    private boolean hasHorizontalWall(int row, int col) {
        for (Wall wall : walls) {
            if (wall.getOrientation() == Orientation.HORIZONTAL) {
                Coordinate start = wall.getStart();
                // A horizontal wall at (r, c) blocks movement between (r-1, c) and (r, c)
                if (start.row() == row && start.col() == col) {
                    return true;
                }
            }
        }
        return false;
    }

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

    public void setPlayers(List<QuoridorPlayer> players) {
        this.players = players;
    }

    // Check if a path is clear between two adjacent positions (considering walls)
    public boolean isPathClear(Coordinate from, Coordinate to) {
        // Check if the move is valid (adjacent or jump)
        int rowDiff = Math.abs(to.row() - from.row());
        int colDiff = Math.abs(to.col() - from.col());

        // Basic adjacency check
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

        // Moving up (row decreases)
        if (rowDiff == -1 && colDiff == 0) {
            return hasHorizontalWall(from.row(), from.col());
        }
        // Moving down (row increases)
        if (rowDiff == 1 && colDiff == 0) {
            return hasHorizontalWall(to.row(), to.col());
        }
        // Moving left (col decreases)
        if (rowDiff == 0 && colDiff == -1) {
            return hasVerticalWall(from.row(), from.col());
        }
        // Moving right (col increases)
        if (rowDiff == 0 && colDiff == 1) {
            return hasVerticalWall(to.row(), to.col());
        }

        // For jumps (2 squares), check intermediate cell
        if (Math.abs(rowDiff) == 2 || Math.abs(colDiff) == 2) {
            int midRow = (from.row() + to.row()) / 2;
            int midCol = (from.col() + to.col()) / 2;
            Coordinate mid = new Coordinate(midRow, midCol);
            // Check if both segments are clear
            return isBlockedByWall(from, mid) || isBlockedByWall(mid, to);
        }

        return false;
    }

    public boolean isWallPlacementValid(Wall wall) {
        Coordinate start = wall.getStart();
        Orientation orientation = wall.getOrientation();

        // Check bounds: walls are placed between cells
        if (orientation == Orientation.HORIZONTAL) {
            // Horizontal wall blocks vertical movement
            if (start.row() < 1 || start.row() >= rows || start.col() < 0 || start.col() >= cols - 1) {
                return false;
            }
        } else { // VERTICAL
            // Vertical wall blocks horizontal movement
            if (start.row() < 0 || start.row() >= rows - 1 || start.col() < 1 || start.col() >= cols) {
                return false;
            }
        }

        // Check if wall overlaps with existing walls
        for (Wall existingWall : walls) {
            if (wallsOverlap(wall, existingWall)) {
                return false;
            }
        }

        // Temporarily add wall to check if it blocks any player's path
        walls.add(wall);
        boolean allPlayersHavePath = true;

        for (QuoridorPlayer player : players) {
            if (!hasPathToGoal(player)) {
                allPlayersHavePath = false;
                break;
            }
        }

        walls.remove(wall); // Remove temporary wall
        return allPlayersHavePath;
    }

    // Check if two walls overlap
    private boolean wallsOverlap(Wall w1, Wall w2) {
        if (w1.getOrientation() != w2.getOrientation()) {
            return false; // Different orientations can't overlap
        }

        Coordinate s1 = w1.getStart();
        Coordinate s2 = w2.getStart();

        if (w1.getOrientation() == Orientation.HORIZONTAL) {
            // Horizontal walls span 2 columns
            return s1.row() == s2.row() && Math.abs(s1.col() - s2.col()) < 2;
        } else {
            // Vertical walls span 2 rows
            return s1.col() == s2.col() && Math.abs(s1.row() - s2.row()) < 2;
        }
    }

    // Use BFS to check if a player has a path to their goal
    private boolean hasPathToGoal(QuoridorPlayer player) {
        Coordinate start = player.pawn.getPosition();
        int goalRow = player.goalLine.getTargetRow();

        Queue<Coordinate> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            // Check if we reached the goal row
            if (current.row() == goalRow) {
                return true;
            }

            // Explore all 4 adjacent cells
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int newRow = current.row() + dir[0];
                int newCol = current.col() + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    Coordinate next = new Coordinate(newRow, newCol);

                    if (!visited.contains(next) && !isBlockedByWall(current, next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        return false; // No path found
    }

    public void placeWall(Wall wall) {
        walls.add(wall);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}