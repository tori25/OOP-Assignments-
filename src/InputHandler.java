import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

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
}
