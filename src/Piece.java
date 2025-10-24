//A piece is any element which is on the table , like numbers, lines, dots, ...
abstract class Piece {
    protected Player player;

    public Piece(Player owner) {
        this.player = owner;
    }

}