package week6.Shopping;

import java.util.ArrayList;
import java.util.List;



public class CoinChangeBacktrackingWithIndices {

    public static void main(String[] args) {
        int[] coins = {4, 5, 8};
        int targetAmount = 14;

        List<List<Integer>> combinations = getCoinCombinations(coins, targetAmount);

        System.out.println("All possible combinations to make the target amount (using indices):");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
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

