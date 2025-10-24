public interface Board {
    void initBoard();
    void printBoard();
    void getSize(InputHandler inputHandler);
    void shuffleBoard();
    boolean moveTile(int value);

    default int getRows() { throw new UnsupportedOperationException(); }
    default int getCols() { throw new UnsupportedOperationException(); }
}