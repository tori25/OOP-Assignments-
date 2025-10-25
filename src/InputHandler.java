// Helper class for validating and handling all user input throughout the games
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;  // Scanner for reading console input

    // Create an input handler with the provided scanner
    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Get a valid integer from user, allowing 'q' to quit
    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Check if user wants to quit
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0); // Cleanly terminate program
            }

            try {
                return Integer.parseInt(input); // Try to parse as integer
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (or 'q' to quit).");
            }
        }
    }

    // Get a non-empty player name from user
    public String getPlayerName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                return name;
            } else {
                System.out.println("Name cannot be empty. Please enter your name.");
            }
        }
    }

    // Get valid board dimensions (rows and columns) from user
    public int[] getBoardSize(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            String[] parts = line.trim().split("\\s+|,|;");  // Split on space, comma, or semicolon

            if (parts.length >= 2) {
                try {
                    int rows = Integer.parseInt(parts[0]);
                    int cols = Integer.parseInt(parts[1]);
                    if (rows > 1 && cols > 1) {
                        return new int[]{rows, cols};
                    } else {
                        System.out.println("Rows and columns must be greater than 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter two valid integers for rows and columns.");
                }
            } else {
                System.out.println("Please enter two numbers separated by space or comma.");
            }
        }
    }

    // Get text from user
    public String getLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Get and validate a move for DotBox game (row, col, direction)
    public Object[] getDotBoxMove(Board board) {
        while (true) {
            String cmd = getLine("Command (h for help): ").trim();

            if (cmd.equalsIgnoreCase("q")) return new Object[]{"q"}; // Quit command
            if (cmd.equalsIgnoreCase("h")) return new Object[]{"h"}; // Help command

            // Accept formats: "r c d" or "r,c,d" where d is H (horizontal) or V (vertical)
            String[] parts = cmd.split("\\s+|,");
            if (parts.length != 3) {
                System.out.println("Invalid format. Use: r c d (d is H or V). Type 'h' for help.");
                continue;
            }

            try {
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                char d = Character.toUpperCase(parts[2].trim().charAt(0));

                // Validate range based on direction
                if (d == 'H') {
                    // Horizontal edge validation
                    if (r < 0 || r > board.getRows() || c < 0 || c >= board.getCols()) {
                        System.out.println("Out of range for H edge. r in [0," + board.getRows() + "], c in [0," + (board.getCols() - 1) + "]");
                        continue;
                    }
                } else if (d == 'V') {
                    // Vertical edge validation
                    if (r < 0 || r >= board.getRows() || c < 0 || c > board.getCols()) {
                        System.out.println("Out of range for V edge. r in [0," + (board.getRows() - 1) + "], c in [0," + board.getCols() + "]");
                        continue;
                    }
                } else {
                    System.out.println("Direction must be H or V.");
                    continue;
                }

                // Valid move - return the coordinates and direction
                return new Object[]{r, c, d};

            } catch (Exception e) {
                System.out.println("Invalid values. Example: 0 0 H");
            }
        }
    }

    // Get an action command from user (e.g., "M" for move, "W" for wall)
    public String getAction(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Get a direction input (H for horizontal or V for vertical) for wall placement
    public String getDirection(String prompt) {
        while (true) {
            System.out.print(prompt);
            String dir = scanner.nextLine().trim().toUpperCase();
            if (dir.equals("H") || dir.equals("V")) {
                return dir;
            }
            System.out.println("Invalid direction. Enter H (horizontal) or V (vertical).");
        }
    }

}
