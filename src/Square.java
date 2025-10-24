public class Square extends Space {
    private Pawn occupant;

    public Square(int row, int col) {
        super(row, col);
    }

    public boolean isOccupied() { return occupant != null; }

    public void setOccupant(Pawn pawn) { this.occupant = pawn; }

    public Pawn getOccupant() { return occupant; }
}