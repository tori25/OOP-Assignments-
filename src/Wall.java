public class Wall {
    private final Coordinate start;
    private final Orientation orientation;

    public Wall(Coordinate start, Orientation orientation) {
        this.start = start;
        this.orientation = orientation;
    }

    public Coordinate getStart() {
        return start;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    // For debugging
    @Override
    public String toString() {
        return "Wall{" + start + ", " + orientation + "}";
    }
}