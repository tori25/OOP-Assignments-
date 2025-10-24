public class QuoridorPlayer extends Player{
    public String name;
    public Pawn pawn;
    public int wallsRemaining = 10;
    public GoalLine goalLine;

    public QuoridorPlayer(String name) {
        this.name = name;
    }

    public void movePawn(Coordinate newPosition) {
        pawn.moveTo(newPosition);
    }

    public void placeWall(Wall wall) {
        wallsRemaining--;
    }

    public boolean hasWalls() {
        return wallsRemaining > 0;
    }
}