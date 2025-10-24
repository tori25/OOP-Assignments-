// Creating a concrete class for Space in the Puzzle game, inherited from Space
class PuzzleSpace extends Space {
    public PuzzleSpace(int row, int col, Piece piece) {
        super(row, col);
        this.piece = piece;
    }

    // Setting the piece for this space
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}

//PuzzlePiece extends the generic Piece class
class PuzzlePiece extends Piece {
    private int value;

    public PuzzlePiece(Player owner, int value) {
        super(owner);
        this.value = value;
    }

    public int getValue() { return value; }
}