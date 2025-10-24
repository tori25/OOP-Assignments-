public class GoalLine {
    private final int targetRow;

    public GoalLine(int targetRow) {
        this.targetRow = targetRow;
    }

    public boolean isReached(Coordinate position) {
        return position.row() == targetRow;
    }

    public int getTargetRow() {
        return targetRow;
    }
}