// Base board class. PuzzleBoard and DotBoxBoard inherit from this.
abstract class Board {
     int rows, cols;
    protected Space[][] spaces;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        spaces = new Space[rows][cols];
    }

    public abstract void initBoard();
    public abstract void printBoard();
    public abstract void getSize(InputHandler inputHandler);


    public abstract void shuffleBoard();
    public abstract boolean moveTile(int value);

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
