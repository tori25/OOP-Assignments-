// This class implements the Board interface and is optimized for the Puzzle game.
// This class implements the Board interface and is optimized for the Puzzle game.
class PuzzleBoard implements Board {
    private int rows, cols;
    private Space[][] spaces;
    private int emptyRow;
    private int emptyCol;

    public PuzzleBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
        initBoard();
    }

    public void setSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
    }

    @Override
    public void initBoard() {
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spaces[i][j] = new PuzzleSpace(i, j, new PuzzlePiece(null, counter++));
            }
        }
        // last cell empty
        ((PuzzleSpace) spaces[rows - 1][cols - 1]).setPiece(null);
        emptyRow = rows - 1;
        emptyCol = cols - 1;
    }

    @Override
    public void printBoard() {
        StringBuilder border = new StringBuilder("+");
        for (int k = 0; k < cols; k++) border.append("__+");

        for (int i = 0; i < rows; i++) {
            System.out.println(border);
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < cols; j++) {
                PuzzlePiece piece = (PuzzlePiece) spaces[i][j].getPiece();

                String cell;
                if (piece == null) {
                    cell = "  ";
                } else {
                    cell = String.format("%2d", piece.getValue());
                }

                row.append(cell).append("|");
            }
            System.out.println(row);
        }
        System.out.println(border);
    }

    @Override
    public void getSize(InputHandler inputHandler) {
        int[] size = inputHandler.getBoardSize("Puzzle size (rows, cols): ");
        setSize(size[0], size[1]);
        initBoard();
    }

    @Override
    public void shuffleBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int r = (int) (Math.random() * rows);
                int c = (int) (Math.random() * cols);
                Piece tmp = spaces[i][j].getPiece();
                spaces[i][j].setPiece(spaces[r][c].getPiece());
                spaces[r][c].setPiece(tmp);
            }
        }

        // ✅ find empty cell after shuffle
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

    @Override
    public boolean moveTile(int value) {
        if (value == 0) return false; // quit signal

        // find tile coordinates
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

        // tile not found
        if (tileRow == -1) {
            System.out.println("Tile not found!");
            return false;
        }

        // check adjacency to empty cell
        if ((Math.abs(tileRow - emptyRow) == 1 && tileCol == emptyCol) ||
                (Math.abs(tileCol - emptyCol) == 1 && tileRow == emptyRow)) {

            // swap tile with empty
            spaces[emptyRow][emptyCol].setPiece(spaces[tileRow][tileCol].getPiece());
            spaces[tileRow][tileCol].setPiece(null);

            // update empty cell location
            emptyRow = tileRow;
            emptyCol = tileCol;

            return true;
        } else {
            System.out.println("Invalid move! Try again.");
            return false;
        }
    }

    // --- Required getters for interface compatibility ---
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}