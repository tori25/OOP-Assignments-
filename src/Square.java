// Represents a single cell on the board that can hold a piece
public class Square extends Space {
    private QuoridorPiece occupant; // The piece currently on this square (null if empty)

    // Create a square at a specific position
    public Square(int row, int col) {
        super(row, col); // Initialize the coordinate in the parent Space class
    }

    // Check if there's a piece on this square
    public boolean isOccupied() {
        return occupant != null;
    }

    // Place a piece on this square
    public void setOccupant(QuoridorPiece piece) {
        this.occupant = piece;
    }

    // Get the piece currently on this square
    public QuoridorPiece getOccupant() {
        return occupant;
    }

    // Visual representation for displaying the square
    @Override
    public String toString() {
        if (isOccupied()) {
            return "[P]"; // Show P if a pawn is here
        }
        return "[ ]"; // Show empty brackets for empty square
    }
}