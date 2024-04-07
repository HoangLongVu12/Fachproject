package week6.Election;

import java.util.Scanner;

public class Elections {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of hours
        int n = scanner.nextInt();
        System.out.println(n);
        // Read the number of voters for each hour
        int[] voters = new int[n];
        for (int i = 0; i < n; i++) {
            voters[i] = scanner.nextInt();
        }

        for (int num : voters) {
            System.out.print(num + " ");
        }
        // Close the scanner
        scanner.close();

        // Calculate the maximum number of voters
        int result = maxVoters(voters, n);

        // Print the result
        System.out.println(result);
    }

    private static int maxVoters(int[] voters, int n) {
        int[] dp = new int[n];

        // Base case
        dp[0] = voters[0];

        // Iterate through the rest of the hours
        for (int i = 1; i < n; i++) {
            // Choose the maximum value between taking a break and promoting at the current hour
            // or continuing from the previous hour without a break
            dp[i] = Math.max(dp[i - 1], (i > 1 ? dp[i - 2] : 0) + voters[i]);
        }

        return dp[n - 1];
    }
}