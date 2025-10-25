// Board implementation for the sliding puzzle game
class PuzzleBoard implements Board {
    private int rows, cols;      // Board dimensions
    private Space[][] spaces;    // 2D grid of spaces holding puzzle pieces
    private int emptyRow;
    private int emptyCol;

    // Create a new puzzle board with specified dimensions
    public PuzzleBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
        initBoard();
    }

    // Update board size (used when user wants different dimensions)
    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
    }

    // Fill board with numbered tiles
    @Override
    public void initBoard() {
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spaces[i][j] = new PuzzleSpace(i, j, new PuzzlePiece(null, counter++));
            }
        }
        // Make last cell empty
        ((PuzzleSpace) spaces[rows - 1][cols - 1]).setPiece(null);
        emptyRow = rows - 1;
        emptyCol = cols - 1;
    }

    // Display the puzzle board with borders and tile numbers
    @Override
    public void printBoard() {
        // Create top/bottom border (e.g., "+__+__+__+")
        StringBuilder border = new StringBuilder("+");
        for (int k = 0; k < cols; k++) border.append("__+");

        for (int i = 0; i < rows; i++) {
            System.out.println(border);
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < cols; j++) {
                PuzzlePiece piece = (PuzzlePiece) spaces[i][j].getPiece();

                String cell;
                if (piece == null) {
                    cell = "  ";  // Empty space shows as blank
                } else {
                    cell = String.format("%2d", piece.getValue());  // Show tile number
                }

                row.append(cell).append("|");
            }
            System.out.println(row);
        }
        System.out.println(border);
    }

    // Get board size from user input
    @Override
    public void getSize(InputHandler inputHandler) {
        int[] size = inputHandler.getBoardSize("Puzzle size (rows, cols): ");
        setSize(size[0], size[1]);
        initBoard();
    }

    // Swapping pieces randomly
    @Override
    public void shuffleBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Pick random position and swap
                int r = (int) (Math.random() * rows);
                int c = (int) (Math.random() * cols);
                Piece tmp = spaces[i][j].getPiece();
                spaces[i][j].setPiece(spaces[r][c].getPiece());
                spaces[r][c].setPiece(tmp);
            }
        }

        // Find where the empty space ended up after shuffling
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (spaces[i][j].getPiece() == null) {
                    emptyRow = i;
                    emptyCol = j;
                    return;
                }
            }
        }
    }

    // Move a tile into the empty space, true if successful, false otherwise
    @Override
    public boolean moveTile(int value) {
        if (value == 0) return false; // 0 means quit, don't move anything

        // Search for the tile with this value
        int tileRow = -1, tileCol = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                PuzzlePiece piece = (PuzzlePiece) spaces[i][j].getPiece();
                if (piece != null && piece.getValue() == value) {
                    tileRow = i;
                    tileCol = j;
                }
            }
        }

        // Tile not found on the board
        if (tileRow == -1) {
            System.out.println("Tile not found!");
            return false;
        }

        // Check if tile is adjacent to the empty space
        if ((Math.abs(tileRow - emptyRow) == 1 && tileCol == emptyCol) ||
                (Math.abs(tileCol - emptyCol) == 1 && tileRow == emptyRow)) {

            // Swap tile with empty space
            spaces[emptyRow][emptyCol].setPiece(spaces[tileRow][tileCol].getPiece());
            spaces[tileRow][tileCol].setPiece(null);

            // Update empty space location
            emptyRow = tileRow;
            emptyCol = tileCol;

            return true; // Move successful
        } else {
            System.out.println("Invalid move! Try again.");
            return false;
        }
    }

    // Getter methods required by Board interface
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}