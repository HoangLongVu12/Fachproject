package week5.Supersort;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supersort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int N = scanner.nextInt();

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        for(int i = 1; i < N; i++) {
            for(int j = i-1; j >= 0; j--) {
                if(arr[i] - arr[j] < 0 ) {
                    System.out.println("no");
                    return;
                }
            }
        }

        System.out.println("yes");
    }
}