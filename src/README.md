# CS611-Assignment < # >
## <Slide Puzzle>
---------------------------------------------------------------------------
- Student 1: Vera Mezhvynskiy
- Email: veramezh@bu.edu

- Student 2: Adithya Darshan Nayak
- Email: adithyad@bu.edu

## Files
---------------------------------------------------------------------------
Game.java – This is the main class where the application starts running.
            It also creates the Player object, the Board object, and the Scanner object, which listens for user input.


Board.java - This class is responsible for the game board. Everything the board knows or does is in this class.
            For example: columns, rows, the board array, as well as the accessors and mutators for the board.
            The Board class also handles the initialization and shuffling of the board.


Player.java – This class is responsible for the player, its instance variables, and its methods.
               For now, it only contains the player’s name and an init method, but it can be expanded in the future.


InputHandler.java – This class is responsible for catching and handling errors in the application.
                    It keeps the program running in a loop and displays error messages when something goes wrong.


## Notes
---------------------------------------------------------------------------
My program currently has four classes: Board, Player, Game, and InputHandler. I’ve tried my best to use encapsulation where possible.
I don’t have a superclass yet because I don’t see the necessity for it right now, but I designed the program with room for future inheritance and polymorphism.
In my opinion, these are the most important components of the game at this stage. Later, I can add new subclasses and implement inheritance.

- One cool thing about this program is its simplicity.
- The main components are kept as simple as possible.
- Each main component of the program is separated into its own class.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "src" after unzipping the VeraMezhvynskiyGame files
2. Run: javac Main.java
3. Run: java Main
4. Play the Game


## Input/Output Example
---------------------------------------------------------------------------
1. Hello! Welcome to Puzzle Game!
2. What’s your name?
3. Puzzle size (rows, cols):
3.a) If the player enters the numbers differently then asked: Please enter two numbers separated by space or comma.
4. Enter the tile number to move (or 0 to quit):
4.a) If the player enters a number that is not next to the empty cell, show: Invalid move! Try again.
4.b) If the player enters a string: Please enter a valid integer.
5. If the player enters 0, show: Goodbye, [player.name]!

```
    < PLAY THROUGH HERE >
```
