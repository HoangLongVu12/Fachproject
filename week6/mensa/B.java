package week6.mensa;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int budget = scanner.nextInt();

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int cost = scanner.nextInt();
            int calories = scanner.nextInt();

            for (int j = budget; j >= cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost] + calories);
            }
        }

        int result = dp[budget];
        System.out.println(result);

        scanner.close();
    }
}

