// Represents a player in any game with a name and score
class Player {
    protected String name;
    protected int score;

    // Create a player with empty name and zero score
    public Player() {
        this.name = "";
        this.score = 0;
    }

    // Create a player with a given name and zero score
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
}