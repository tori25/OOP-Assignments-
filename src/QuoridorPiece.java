// Unified class for representing both pawns and walls in Quoridor game
// This simplifies the design by using one class with different types instead of separate Pawn/Wall classes
public class QuoridorPiece extends Piece {
    // Enum to distinguish between pawn pieces (player tokens) and wall pieces (barriers)
    public enum PieceType {PAWN, WALL}

    // Enum for wall orientation - walls can block horizontal or vertical movement
    public enum WallOrientation {HORIZONTAL, VERTICAL}

    private PieceType type;           // Whether this is a pawn or wall
    private int row, col;             // Position on the board
    private WallOrientation orientation; // Only used for walls, null for pawns

    // Constructor for pawn pieces - only needs position, no orientation
    public QuoridorPiece(Player owner, int row, int col) {
        super(owner);
        this.type = PieceType.PAWN;
        this.row = row;
        this.col = col;
        this.orientation = null; // Pawns don't have orientation
    }

    // Constructor for wall pieces - needs position AND orientation
    public QuoridorPiece(Player owner, int row, int col, WallOrientation orientation) {
        super(owner);
        this.type = PieceType.WALL;
        this.row = row;
        this.col = col;
        this.orientation = orientation; // Horizontal or vertical
    }

    // Getter methods to access piece information
    public PieceType getType() {
        return type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public WallOrientation getOrientation() {
        return orientation;
    }

    // Display representation: First letter of player name for pawns, line symbols for walls
    @Override
    public String toString() {
        if (type == PieceType.PAWN)
            return player != null ? player.name.substring(0, 1).toUpperCase() : "P";
        else
            return (orientation == WallOrientation.HORIZONTAL) ? "─" : "│";
    }
}
