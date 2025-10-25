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