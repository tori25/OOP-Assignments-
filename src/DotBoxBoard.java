// This class implements the Board interface for the DotBox game.
class DotBoxBoard implements Board {
    private int rows, cols;
    private Space[][] spaces;
    private boolean[][] hEdges;
    private boolean[][] vEdges;
    private int[][] boxOwner;

    public DotBoxBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.spaces = new Space[rows][cols];
        initBoard();
    }

    @Override
    public void initBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                spaces[i][j] = new Space(i, j);
            }
        }
        // initialize edges and owners
        hEdges = new boolean[rows + 1][cols];
        vEdges = new boolean[rows][cols + 1];
        boxOwner = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boxOwner[i][j] = -1;
            }
        }
    }

    @Override
    public void printBoard() {
        for (int r = 0; r < rows; r++) {
            // draw top edge row for boxes in row r
            for (int c = 0; c < cols; c++) {
                System.out.print("+");
                System.out.print(hEdges[r][c] ? "---" : "   ");
            }
            System.out.println("+");

            // draw vertical edges and box owners for row r
            for (int c = 0; c < cols; c++) {
                System.out.print(vEdges[r][c] ? "|" : " ");
                int owner = boxOwner[r][c];
                char ch = owner == -1 ? ' ' : (char) ('1' + owner); // '1' or '2'
                System.out.print(" " + ch + " ");
            }
            // last vertical edge at the end of the row
            System.out.println(vEdges[r][cols] ? "|" : " ");
        }

        // bottom edge row (after last box row)
        for (int c = 0; c < cols; c++) {
            System.out.print("+");
            System.out.print(hEdges[rows][c] ? "---" : "   ");
        }
        System.out.println("+");
    }

    @Override
    public void getSize(InputHandler inputHandler) {
        int[] size = inputHandler.getBoardSize("Dots & Boxes size (rows, cols): ");
        this.rows = size[0];
        this.cols = size[1];
        this.spaces = new Space[rows][cols];
        initBoard();
    }

    public int claimEdge(int r, int c, char dir, int playerIndex) {
        // returns number of boxes completed, or -1 if invalid/claimed
        dir = Character.toUpperCase(dir);
        if (dir == 'H') {
            if (r < 0 || r > rows || c < 0 || c >= cols) return -1;
            if (hEdges[r][c]) return -1;
            hEdges[r][c] = true;
            int completed = 0;
            // box above (r-1, c)
            if (r > 0 && isBoxComplete(r - 1, c)) {
                if (boxOwner[r - 1][c] == -1) {
                    boxOwner[r - 1][c] = playerIndex;
                    completed++;
                }
            }
            // box below (r, c)
            if (r < rows && isBoxComplete(r, c)) {
                if (boxOwner[r][c] == -1) {
                    boxOwner[r][c] = playerIndex;
                    completed++;
                }
            }
            return completed;
        } else if (dir == 'V') {
            if (r < 0 || r >= rows || c < 0 || c > cols) return -1;
            if (vEdges[r][c]) return -1;
            vEdges[r][c] = true;
            int completed = 0;
            // box to the left (r, c-1)
            if (c > 0 && isBoxComplete(r, c - 1)) {
                if (boxOwner[r][c - 1] == -1) {
                    boxOwner[r][c - 1] = playerIndex;
                    completed++;
                }
            }
            // box to the right (r, c)
            if (c < cols && isBoxComplete(r, c)) {
                if (boxOwner[r][c] == -1) {
                    boxOwner[r][c] = playerIndex;
                    completed++;
                }
            }
            return completed;
        } else {
            return -1;
        }
    }

    private boolean isBoxComplete(int r, int c) {
        return hEdges[r][c] && hEdges[r + 1][c] && vEdges[r][c] && vEdges[r][c + 1];
    }

    public boolean isComplete() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boxOwner[i][j] == -1) return false;
            }
        }
        return true;
    }

    @Override
    public boolean moveTile(int value) {
        return false; // Not used in DotBox
    }

    @Override
    public void shuffleBoard() {
        // Not used in DotBox
    }

    // Required getters (for game access)
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}