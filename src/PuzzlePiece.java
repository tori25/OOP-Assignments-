//PuzzlePiece extends the generic Piece class
class PuzzlePiece extends Piece {
    private int value;

    public PuzzlePiece(Player owner, int value) {
        super(owner);
        this.value = value;
    }

    public int getValue() { return value; }
}