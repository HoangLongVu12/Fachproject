package week7.substitution;

import java.util.Scanner;

public class Substitution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        String s1 = scanner.next();
        String s2 = scanner.next();

        // Output
        int result = editDistance(s1, s2);
        System.out.println(result);

        scanner.close();
    }

    public static int editDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // Create a 2D array to store the edit distances
        int[][] dp = new int[m + 1][n + 1];

        // Fill in the base cases
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;  // Number of insertions needed for empty s1 to s2
                } else if (j == 0) {
                    dp[i][j] = i;  // Number of deletions needed for s1 to empty s2
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }

        return dp[m][n];
    }
}
