import java.util.Scanner;
import java.util.Arrays;

public class Necklace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of items and the target length
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        // Read the lengths of the sticks
        int[] sticks = new int[n];
        for (int i = 0; i < n; i++) {
            sticks[i] = scanner.nextInt();
        }

        // Close the scanner
        scanner.close();

        // Calculate the minimum number of sticks
        int result = minSticks(sticks, n, k);

        // Print the result
        if (result == Integer.MAX_VALUE) {
            System.out.println("impossible");
        } else {
            System.out.println(result);
        }
    }

    private static int minSticks(int[] sticks, int n, int k) {
        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if (i - sticks[j] >= 0 && dp[i - sticks[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - sticks[j]] + 1);
                }
            }
        }

        return dp[k] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[k];
    }
}
