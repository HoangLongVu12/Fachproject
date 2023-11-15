package week4.Temperature;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Temperature {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Deque<Integer> window = new ArrayDeque<>();
        int[] temperatures = new int[n];

        // Calculate and print the maximum temperatures
        for (int i = 0; i < n; i++) {
            temperatures[i] = scanner.nextInt();

            // Remove indices that are outside the current window
            while (!window.isEmpty() && window.peekFirst() < i - k + 1) {
                window.pollFirst();
            }

            // Remove indices with temperatures smaller than the current temperature
            while (!window.isEmpty() && temperatures[window.peekLast()] < temperatures[i]) {
                window.pollLast();
            }

            window.offerLast(i);

            // Start reporting maximum temperatures from the kth day
            if (i >= k - 1) {
                System.out.println(temperatures[window.peekFirst()]);
            }
        }
    }
}

