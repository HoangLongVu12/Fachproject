package week5.DogBreeding;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DogBreeding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input
        int n = sc.nextInt();
        int k = sc.nextInt();

        String[] prefixname = new String[n];
        int[] numberofmom = new int[n];
        String[] names = new String[k];

        for(int i = 0; i < n; i++) {
            prefixname[i] = sc.next();
            numberofmom[i] = sc.nextInt();
        }


        for(int i = 1; i < names.length; i++) {
            if(names[i].length() == 0) {
                int sum = 0;
                for (int j = n - 1; j > 0; j--) {
                    if (names[i] == prefixname[j]) {
                        sum++;
                    }
                }
                System.out.println(sum);
            } else {
                for(int j = 0; j < names[i].length(); j++) {

                }
            }
        }
    }
}
