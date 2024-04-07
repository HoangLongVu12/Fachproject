package week6.SDS;

import java.util.Scanner;



import java.util.Scanner;

public class SDS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of integers in A
        int n = scanner.nextInt();

        // Read the integers in A
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        // Close the scanner
        scanner.close();

        // Calculate the maximum sum and corresponding subarray
        int[] result = maxSumDisjointSubarray(A, n);

        int count = 0;
        for (int i = 1; i < result.length; i++) {
            if(result[i] != 0 ) {
                count++;
            }
        }
        // Print the result
        System.out.println(count); // Length of B'
        for (int i = 1; i <= result[0]; i++) {
            if(result[i] != 0) {
                System.out.print(result[i] + " ");
            }
        }
    }

    private static int[] maxSumDisjointSubarray(int[] A, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        // Iterate through A to find the maximum sum of disjoint subarrays
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], (i > 2 ? dp[i - 2] : 0) + A[i - 1]);
        }

        // Reconstruct the subarray B'
        int length = dp[n];
        int[] result = new int[length + 1];
        result[0] = length;

        for (int i = n, j = length; i >= 1 && j > 0; i--) {
            if (dp[i] != dp[i - 1]) {
                result[j--] = A[i - 1];
                i--; // Move to the next non-adjacent element
            }
        }

        return result;
    }
}


