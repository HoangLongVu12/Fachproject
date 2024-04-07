package week4.Knight;

import java.util.Scanner;

public class Knight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] board = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String row = sc.next();
            for (int j = 0; j < 5; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        int count = 0;

        // Iterate through the 2D array and count occurrences of the target character
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == 'k') {
                    count++;
                }
            }
        }

        if( count != 9) {
            System.out.println("invalid");
            return;
        }

        int[] moveX = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int[] moveY= { 1, 2, 2, 1, -1, -2, -2, -1 };

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                int next_x;
                int next_y;
                for(int k = 0; k < 8; k++) {
                    if(board[i][j]== 'k' ) {
                        next_x = i + moveX[k];
                        next_y = j + moveY[k];
                        if (isSafe(next_x, next_y, board)) {
                            if (board[next_x][next_y]== 'k') {
                                System.out.println("invalid");
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("valid");
    }

    private static boolean isSafe(int x, int y, char[][] sol)
    {
        return (x >= 0 && x < 5 && y >= 0 && y < 5
                && ( sol[x][y] == '.' || sol[x][y] == 'k' ));
    }


    public static void print2D(char[][] mat)
    {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

}
