import java.util.Scanner;

class Player {
    String name;

    public void initPlayer(InputHandler inputHandler) {
        this.name = inputHandler.getPlayerName("Hello! Welcome to Puzzle Game!\nWhat's your name? ");
    }
}
