// Represents a goal line in Quoridor - the row a player needs to reach to win
public class GoalLine {
    private final int targetRow;  // The row number that defines the goal

    // Create a goal line at the specified row
    public GoalLine(int targetRow) {
        this.targetRow = targetRow;
    }

    // Check if a given position has reached this goal line
    public boolean isReached(Coordinate position) {
        return position.row() == targetRow;
    }

    // Get the target row number
    public int getTargetRow() {
        return targetRow;
    }
}