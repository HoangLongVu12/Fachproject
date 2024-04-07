package week7.repetition;

import java.util.Arrays;
import java.util.Scanner;

public class DNARepetition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String space = sc.nextLine();
        String ADN = sc.nextLine();

        // Build suffix array and LCP array
        int[] suffixArray = buildSuffixArray(ADN);
        int[] lcpArray = buildLCPArray(ADN, suffixArray);

        // Find the maximum value in the LCP array
        int maxLength = findMax(lcpArray);

        // Print the result
        System.out.println(maxLength);
    }

    // Function to build the suffix array
    private static int[] buildSuffixArray(String s) {
        int n = s.length();
        Suffix[] suffixes = new Suffix[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(i, s.substring(i));
        }

        Arrays.sort(suffixes);

        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index;
        }

        return suffixArray;
    }

    // Function to build the longest common prefix (LCP) array
    private static int[] buildLCPArray(String s, int[] suffixArray) {
        int n = s.length();
        int[] lcpArray = new int[n];
        int[] inverseSuffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            inverseSuffixArray[suffixArray[i]] = i;
        }

        int k = 0;

        for (int i = 0; i < n; i++) {
            if (inverseSuffixArray[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = suffixArray[inverseSuffixArray[i] + 1];

            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }

            lcpArray[inverseSuffixArray[i]] = k;

            if (k > 0) {
                k--;
            }
        }

        return lcpArray;
    }

    // Function to find the maximum value in an array
    private static int findMax(int[] arr) {
        int max = 0;

        for (int value : arr) {
            max = Math.max(max, value);
        }

        return max;
    }

    // Helper class to represent a suffix
    static class Suffix implements Comparable<Suffix> {
        int index;
        String suffix;

        Suffix(int index, String suffix) {
            this.index = index;
            this.suffix = suffix;
        }

        @Override
        public int compareTo(Suffix other) {
            return this.suffix.compareTo(other.suffix);
        }
    }
}
