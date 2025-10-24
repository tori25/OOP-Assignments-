public class Pawn {
    private Coordinate position;

    public Pawn(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void moveTo(Coordinate newPosition) {
        this.position = newPosition;
    }
}