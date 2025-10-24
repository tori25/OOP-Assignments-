// single cell (space) on the game board
class Space {
    int row;
    int col;
    protected Piece piece;

    public Space(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = null;
    }

    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
}