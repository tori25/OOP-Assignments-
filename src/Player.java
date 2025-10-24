// Player.java
class Player {
    protected String name;
    protected int score;

    public Player() {
        this.name = "";
        this.score = 0;
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        score += points;
    }

    @Override
    public String toString() {
        return name + " (Score: " + score + ")";
    }
}