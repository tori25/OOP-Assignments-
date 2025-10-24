//creating a player instance
class Player {
     String name;
     int score;

    public Player() {
        this.name = "";
        this.score = 0;
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void initPlayer(InputHandler inputHandler) {
        this.name = inputHandler.getPlayerName("Hello! Welcome to Puzzle Game!\nWhat's your name? ");
    }
}
