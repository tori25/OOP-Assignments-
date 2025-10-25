// Represents a single cell (space) on the game board
// Can hold a piece and has a fixed position
class Space {
    private final Coordinate position;  // Location of this space on the board
    protected Piece piece;              // The piece occupying this space (null if empty)

    // Create a new space at the given row and column
    public Space(int row, int col) {
        this.position = new Coordinate(row, col);
        this.piece = null;  // Starts empty
    }

    // Get the coordinate of this space
    public Coordinate getPosition() {
        return position;
    }

    // Get the piece currently on this space
    public Piece getPiece() {
        return piece;
    }

    // Place or remove a piece on this space
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Convenience method to get just the row
    public int getRow() {
        return position.getRow();
    }

    // Convenience method to get just the column
    public int getCol() {
        return position.getCol();
    }

    // String representation showing position and whether space is occupied
    @Override
    public String toString() {
        return "Space " + position.toString() + " [" + (piece == null ? "empty" : piece.toString()) + "]";
    }
}