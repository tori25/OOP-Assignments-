# CS611-Assignment < #2 >
## <Dots and Boxes>
---------------------------------------------------------------------------
- Student 1: Vera Mezhvynskiy
- Email: veramezh@bu.edu

- Student 2: Adithya Darshan Nayak
- Email: adithyad@bu.edu

## Files
---------------------------------------------------------------------------
Game.java – This is the abstract Game class from which concrete game classes are created.
            It defines the common structure and behavior shared by all games.

Main.java – The entry point of the program; responsible for starting the selected game.

PuzzleGame.java – Concrete class for the Puzzle Game, inheriting from Game.

DotBoxGame.java – Concrete class for the Dots and Boxes Game, inheriting from Game.

Board.java – Abstract class defining a generic game board.
            Other board types (like PuzzleBoard or DotBoxBoard) inherit from it.
            Board is also a has-a relationship to the abstract Game class.

PuzzleBoard.java – The specific board implementation for the Puzzle Game, extending Board.

DotBoxBoard.java – The specific board implementation for the Dots and Boxes Game, extending Board.

Player.java – Represents the player in a game.
               Each game has-a player instance (or two players in the Dots and Boxes game).

Piece.java – Abstract class representing a generic piece that can be placed on a space.

PuzzlePiece.java – Concrete implementation of Piece used in the Puzzle Game.

Space.java – Represents a single cell (or square) on a game board.

InputHandler.java – Handles all user input and validation for both games.

## Notes
---------------------------------------------------------------------------
My program has three abstract classes: Game, Board, and Piece.
From the abstract class Game, I have two subclasses: PuzzleGame and DotBoxGame.
The Board class also has two subclasses: PuzzleBoard and DotBoxBoard.
The Piece class is an abstract base for all game pieces, and PuzzlePiece is its concrete implementation for the puzzle game.

•	I used encapsulation and inheritance throughout the program.
•	Both games share a similar structure built on the Game and Board abstractions.
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

 If the user choose 2!
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

```

