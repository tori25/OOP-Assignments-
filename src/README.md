# CS611-Assignment < #3 >
## <Quoridor Game>
---------------------------------------------------------------------------
- Student 1: Vera Mezhvynskiy
- Email: veramezh@bu.edu

- Student 2: Adithya Darshan Nayak
- Email: adithyad@bu.edu

## Files
---------------------------------------------------------------------------
Game.java – Abstract base class defining the core structure and behavior shared by all games.
        Initializes shared components — Scanner, InputHandler, and a Player instance — providing a common foundation for all subclasses.

Main.java – Entry point of the application. Displays the welcome menu, lets the user choose which game to play, and launches the corresponding game.

Board.java – Abstract class defining the blueprint for all game boards (initBoard, printBoard, etc.).
        Each game (Puzzle, DotBox, Quoridor) provides its own concrete implementation.
        The Game class has a composition relationship with Board (a Game has-a Board).

PuzzleGame.java – Concrete implementation of the classic sliding Puzzle Game.
        Extends Game, uses PuzzleBoard and PuzzlePiece, and manages player moves and victory conditions.

PuzzleBoard.java – Implements Board for the Puzzle Game.
        Handles tile movement, shuffle logic, and empty-space tracking.

DotBoxGame.java – Concrete implementation of the Dots and Boxes Game.
        Extends Game, supports two-player mode, uses DotBoxBoard, and implements scoring and edge-claiming logic.

DotBoxBoard.java – Implements Board for the Dots and Boxes Game.
        Manages the grid, edges, and completed boxes for each player.

QuoridorGame.java – Concrete implementation of the Quoridor board game.
        Extends Game, manages two players (QuoridorPlayer), pawns, walls, and victory detection based on reaching the goal line.

QuoridorBoard.java – Implements Board for the Quoridor Game.
        Manages spaces, wall placement, and movement validation.

Player.java – Represents a game player with a name and score.
    Used directly in the Puzzle and DotBox games. Quoridor Player extends this class. 

QuoridorPlayer.java – Specialized subclass of Player with Pawn, GoalLine, and wallsRemaining fields specific to Quoridor.

Piece.java – Abstract base class for any movable or placeable piece on a board.
PuzzlePiece.java – Concrete implementation used in the Puzzle Game.

Space.java – Represents a single cell or coordinate on any game board.
PuzzleSpace.java – Specialized Space for the Puzzle Game.

Coordinate.java – Represents a row/column position on the board.
GoalLine.java – Represents the target line a pawn must reach in Quoridor.
Pawn.java – Represents a player’s pawn in the Quoridor Game.
Wall.java – Represents a placed wall in Quoridor.
Square.java – Utility class for Quoridor grid representation.
Orientation.java – Enum defining horizontal/vertical wall orientation.

InputHandler.java – Handles playerS input and input validation for all three games.

## Notes
---------------------------------------------------------------------------
The program contains three abstract classes: Game, Board, and Piece.
From the abstract class Game, I have three subclasses: PuzzleGame, DotBoxGame, and QuoridorGame.
The Board class also has three subclasses: PuzzleBoard, DotBoxBoard, and QuoridorBoard.
The Piece class serves as an abstract base for all game pieces, and PuzzlePiece is its concrete implementation for the puzzle game.
For the Quoridor game, I followed the same structure and added the concrete classes QuoridorGame, QuoridorBoard, and QuoridorPlayer.
These classes inherit from their corresponding superclasses.
Additionally, I used several helper classes—Wall, Square, Pawn, and GameState—to handle the specific details of the Quoridor game.
Object-Oriented Design Highlights
Encapsulation and inheritance are applied consistently throughout the program.
All three games share a unified structure built on the Game and Board abstractions.
Input is handled and validated through the InputHandler class, ensuring safe and consistent user interaction.
The program uses both composition (has-a relationships) and inheritance to achieve modularity and reusability.
Each major component is encapsulated within its own class, adhering to the Single Responsibility Principle.

•	I used encapsulation and inheritance throughout the whole program.
•	All three games share a similar structure built on the Game and Board abstractions.
•	Input is handled and validated through InputHandler, ensuring safe and consistent user interaction.
•	The program uses composition (has-a relationships) and inheritance to achieve modular and reusable design.
•	Each main component of the program is separated into its own class, following the single responsibility principle.


## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "src" after unzipping the VeraMezhvynskiyGame files
2. Run: javac Main.java
3. Run: java Main
4. Play the Game


## Input/Output Example
---------------------------------------------------------------------------

1. Welcome! Let's play! Choose a game: 1 = Puzzle, 2 = DotBox 

If the user choose 1!
1. Puzzle size (rows, cols): 
2. If the player enters the numbers differently then asked: Please enter two numbers separated by space or comma. 
3. Enter the tile number to move (or 0 to quit): 
4.  If the player enters a number that is not next to the empty cell, show: Invalid move! Try again. 
5. If the player enters a string: Please enter a valid integer. 
6. If the player enters 0, show: Goodbye, [player.name]!

If the user choose 2! - Dots and Boxes Game 

1. Dots & Boxes!
   Hello! Welcome to Puzzle Game!
   What's your name?
2. Player 2 name:
3. Dots & Boxes size (rows, cols):
4. Commands: 'h' help, 'q' quit round, or claim an edge as 'r c d'.
   Examples: 0 0 H   or   1,2,V
   H edges: r in [0,3], c in [0,3]
   V edges: r in [0,2], c in [0,4]
   Score: Player 1 = 0, Player 2 = 0
   Turn: Player 1
   Command (h for help):
5. If player introduce wrong numbers: Invalid format. Use: r c d (d is H or V). Type 'h' for help.
   Command (h for help):
6.  If wrong direction: Direction must be H or V.
7. If invalid value:  Invalid values. Example: 0 0 H
8. If player won: 
   Final Score:
   Congrats [player.name] !
9. If no one won: It's a tie!

