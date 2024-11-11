import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        boolean playAgain;

        do {
            clearBoard();
            int moveCount = 0;
            boolean gameWon = false;

            while (moveCount < ROWS * COLS && !gameWon) {
                displayBoard();
                int row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                int col = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;

                while (!isValidMove(row, col)) {
                    System.out.println("Invalid move! Try again.");
                    row = SafeInput.getRangedInt(console, "Enter row (1-3): ", 1, 3) - 1;
                    col = SafeInput.getRangedInt(console, "Enter column (1-3): ", 1, 3) - 1;
                }

                board[row][col] = currentPlayer;
                moveCount++;
                if (moveCount >= 5) {  // Min moves to potentially win
                    gameWon = isWin(currentPlayer);
                }

                if (gameWon) {
                    displayBoard();
                    System.out.println(currentPlayer + " wins!");
                } else if (moveCount == ROWS * COLS) {
                    displayBoard();
                    System.out.println("It's a tie!");
                }

                currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
            }

            playAgain = SafeInput.getYNConfirm(console, "Play again? (Y/N): ");
        } while (playAgain);

        console.close();
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void displayBoard() {
        System.out.println("  1 2 3");
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) System.out.println("  -----");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }
}
