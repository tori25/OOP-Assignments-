class Board {
    int n; // cols
    int m; // rows
    int [][] board;
    int emptyRow;
    int emptyCol;

    public void setSize(int rows, int cols) {
        m = rows;
        n = cols;
        board = new int[m][n];
    }

    public void getSize(InputHandler inputHandler) {
        int[] size = inputHandler.getBoardSize("Puzzle size (rows, cols): ");
        setSize(size[0], size[1]);
    }

    public void initBoard () {
        int counter = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = counter++;
            }
        }
        board[m-1][n-1] = 0; // make last cell empty
        emptyRow = m - 1;
        emptyCol = n - 1;
    }

    public void shuffleBoard() {
        int rowLen = board.length;
        int colLen = board[0].length;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                int randRow = (int)(Math.random() * rowLen);
                int randCol = (int)(Math.random() * colLen);

                int temp = board[i][j];
                board[i][j] = board[randRow][randCol];
                board[randRow][randCol] = temp;
            }
        }
        findEmptyCell();
    }

    public void printBoard() {
        StringBuilder border = new StringBuilder("+");
        for (int k = 0; k < n; k++) border.append("__+");

        for (int i = 0; i < m; i++) {
            System.out.println(border);
            StringBuilder row = new StringBuilder("|");
            for (int j = 0; j < n; j++) {
                String cell = (board[i][j] == 0) ? "  " : String.format("%2d", board[i][j]);
                row.append(cell).append("|");
            }
            System.out.println(row);
        }
        System.out.println(border);
    }

    public void findEmptyCell() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    boolean moveTile(int value) {
        if (value == 0) return false; // if value ==  quit

        // find tile
        int tileRow = -1, tileCol = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == value) {
                    tileRow = i;
                    tileCol = j;
                }
            }
        }

        // check adjacency
        if ((Math.abs(tileRow - emptyRow) == 1 && tileCol == emptyCol) ||
                (Math.abs(tileCol - emptyCol) == 1 && tileRow == emptyRow)) {
            // swap
            board[emptyRow][emptyCol] = value;
            board[tileRow][tileCol] = 0;
            emptyRow = tileRow;
            emptyCol = tileCol;
            return true;
        } else {
            System.out.println("Invalid move! Try again.");
            return false;
        }
    }

}
