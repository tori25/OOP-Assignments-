// Representing a board position (row, col) used for pieces, walls, and moves
public class Coordinate {
    private final int row;
    private final int col;

    // Create a new coordinate at specified row and column
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // Record-style accessor for row
    public int row() {
        return row;
    }

    // Record-style accessor for column
    public int col() {
        return col;
    }

    // String representation for debugging and display
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    // Two coordinates are equal if they have the same row and column
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Coordinate)) return false;
        Coordinate other = (Coordinate) obj;
        return row == other.row && col == other.col;
    }

    // Hash code for use in hash-based collections (HashSet, HashMap)
    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}