// Interface defining the common behavior all game boards
public interface Board {
    void initBoard();                        // Set up the initial board state
    void printBoard();                       // Display the current board
    void getSize(InputHandler inputHandler); // Get board dimensions from user
    void shuffleBoard();                     // Randomize board (if applicable)
    boolean moveTile(int value);             // Move a tile/piece (if applicable)

    default int getRows() { throw new UnsupportedOperationException(); }
    default int getCols() { throw new UnsupportedOperationException(); }
}