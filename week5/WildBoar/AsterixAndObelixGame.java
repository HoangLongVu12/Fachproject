package week5.WildBoar;

import java.util.Scanner;



import java.util.Scanner;

import java.util.Scanner;

public class AsterixAndObelixGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: board dimensions and winning condition
        int h = scanner.nextInt();
        int w = scanner.nextInt();
        int k = scanner.nextInt();

        // Input: moves
        int[] moves = new int[h * w];
        for (int i = 0; i < h * w; i++) {
            moves[i] = scanner.nextInt();
        }

        // Close the scanner
        scanner.close();

        // Output the result
        String result = findWinner(h, w, k, moves);
        System.out.println(result);
    }

    private static String findWinner(int h, int w, int k, int[] moves) {
        char[][] board = new char[h][w];
        char winner = ' ';
        int moveCount = 0;

        for (int i = 0; i < h * w; i++) {
            char player = (i % 2 == 0) ? 'A' : 'B';
            int column = moves[i] - 1;

            // Drop the tile in the selected column
            int row = dropTile(board, column, player);

            // Check for a winner
            if (checkForWinner(board, row, column, player, k)) {
                winner = player;
                moveCount = i + 1;
                break;
            }

            // Check for a draw
            if (i == h * w - 1) {
                return "D";
            }
        }

        return winner + " " + moveCount;
    }

    private static int dropTile(char[][] board, int column, char player) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = player;
                return i;
            }
        }
        return -1; // Should not reach here, as the column should not be full
    }

    private static boolean checkForWinner(char[][] board, int row, int column, char player, int k) {
        // Check horizontally
        int count = 0;
        for (int i = Math.max(0, column - k + 1); i <= Math.min(column + k - 1, board[0].length - 1); i++) {
            if (board[row][i] == player) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check vertically
        count = 0;
        for (int i = Math.max(0, row - k + 1); i <= Math.min(row + k - 1, board.length - 1); i++) {
            if (board[i][column] == player) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check diagonally (top-left to bottom-right)
        count = 0;
        int startRow = row - Math.min(row, column);
        int startCol = column - Math.min(row, column);
        int endRow = Math.min(board.length - 1, row + (board[0].length - 1 - column));
        int endCol = Math.min(board[0].length - 1, column + (board.length - 1 - row));

        for (int i = startRow, j = startCol; i <= endRow && j <= endCol; i++, j++) {
            if (board[i][j] == player) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check diagonally (top-right to bottom-left)
        count = 0;
        startRow = row - Math.min(row, board[0].length - 1 - column);
        startCol = column + Math.min(row, board[0].length - 1 - column);
        endRow = Math.min(board.length - 1, row + column);
        endCol = Math.max(0, column - (board.length - 1 - row));

        for (int i = startRow, j = startCol; i <= endRow && j >= endCol; i++, j--) {
            if (board[i][j] == player) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }
}




