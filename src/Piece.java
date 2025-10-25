// Abstract base class for any game piece (pawns, walls, tiles, dots, etc.)
abstract class Piece {
    protected Player player;

    // Create a piece owned by the specified player
    public Piece(Player owner) {
        this.player = owner;
    }

}