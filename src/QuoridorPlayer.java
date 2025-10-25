// Represents a player in the Quoridor game with their pawn, walls, and goal
public class QuoridorPlayer extends Player{
    public String name;                       // Player's name
    public QuoridorPiece pawn;               // The player's pawn piece on the board
    public int wallsRemaining = 10;          // Each player starts with 10 walls to place
    public GoalLine goalLine;                // The row this player needs to reach to win

    // Initialize a new player with a name
    public QuoridorPlayer(String name) {
        this.name = name;
    }

    // Move the pawn to a new position by creating a new piece at that location
    public void movePawn(Coordinate newPosition) {
        pawn = new QuoridorPiece(this, newPosition.row(), newPosition.col());
    }

    // Place a wall and decrement the remaining wall count
    public void placeWall(QuoridorPiece wall) {
        wallsRemaining--;
    }

    // Check if player has walls left to place
    public boolean hasWalls() {
        return wallsRemaining > 0;
    }
}