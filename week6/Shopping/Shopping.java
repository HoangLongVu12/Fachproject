package week6.Shopping;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Shopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of items on the menu
        int n = scanner.nextInt();

        // Read the prices of each garment
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }

        // Read the number of friends
        int m = scanner.nextInt();

        // Read the prices each friend paid
        int[] friends = new int[m];
        for (int i = 0; i < m; i++) {
            friends[i] = scanner.nextInt();
        }

        // Close the scanner
        scanner.close();

        for(int i = 0; i < friends.length; i++ ) {
            int ways = getNumberOfWays(friends[i],prices);
            if(ways == 0) {
                System.out.print("Impossible ");
            } else if(ways >= 2) {
                System.out.print("Ambiguous ");
            } else {
                List<List<Integer>> combinations = getCoinCombinations(prices, friends[i]);
                for(int j = 0; j < combinations.get(0).size();j++) {
                    System.out.print(combinations.get(0).get(j)+" ");
                }
            }
        }
    }

    static int getNumberOfWays(int N, int[] prices)
    {

        int[] ways = new int[(int)N + 1];

        ways[0] = 1;

        for (int i = 0; i < prices.length; i++) {

            for (int j = 0; j < ways.length; j++) {
                if (prices[i] <= j) {

                    ways[j] += ways[(j - prices[i])];
                }
            }
        }

        return ways[N];
    }

    static List<List<Integer>> getCoinCombinations(int[] coins, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(coins, target, 0, new ArrayList<>(), result);
        return result;
    }

    static void backtrack(int[] coins, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < coins.length; i++) {
            if (coins[i] <= remaining) {
                current.add(i+1); // Add index instead of coin value
                backtrack(coins, remaining - coins[i], i, current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}
