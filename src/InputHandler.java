// Helper class for validating user input and output.
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    // Verifies that the user input is an integer; otherwise, displays the message.
    public int getInt(String prompt) {
        while (true) { // runs always
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // Verifies that the user entered a name and didn't leave it empty
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

    //Validating board size (rows, cols) input from user
    public int[] getBoardSize(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            String[] parts = line.trim().split("\\s+|,|;");
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

    public String getLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Handles and validates a move command for the DotBox game
    public Object[] getDotBoxMove(Board board) {
        while (true) {
            String cmd = getLine("Command (h for help): ").trim();

            if (cmd.equalsIgnoreCase("q")) return new Object[]{"q"}; // quit
            if (cmd.equalsIgnoreCase("h")) return new Object[]{"h"}; // help

            // Accept formats: "r c d" or "r,c,d" where d in H/V
            String[] parts = cmd.split("\\s+|,");
            if (parts.length != 3) {
                System.out.println("Invalid format. Use: r c d (d is H or V). Type 'h' for help.");
                continue;
            }

            try {
                int r = Integer.parseInt(parts[0]);
                int c = Integer.parseInt(parts[1]);
                char d = Character.toUpperCase(parts[2].trim().charAt(0));

                // Range validation depending on direction
                if (d == 'H') {
                    if (r < 0 || r > board.getRows() || c < 0 || c >= board.getCols()) {
                        System.out.println("Out of range for H edge. r in [0," + board.getRows() + "], c in [0," + (board.getCols() - 1) + "]");
                        continue;
                    }
                } else if (d == 'V') {
                    if (r < 0 || r >= board.getRows() || c < 0 || c > board.getCols()) {
                        System.out.println("Out of range for V edge. r in [0," + (board.getRows() - 1) + "], c in [0," + board.getCols() + "]");
                        continue;
                    }
                } else {
                    System.out.println("Direction must be H or V.");
                    continue;
                }

                // Valid move
                return new Object[]{r, c, d};

            } catch (Exception e) {
                System.out.println("Invalid values. Example: 0 0 H");
            }
        }
    }


}
