package week4.SmashingRocks;

import java.util.Scanner;
import java.nio.file.attribute.DosFileAttributeView;
import java.util.Arrays;

public class Rocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        int dif = arr[size - 1];

        for (int i = size - 1; i > 0; i--) {
            dif = Math.abs(dif - arr[i - 1]);
        }

        // System.out.println(dif);
        // for (int i = 0; i < size; i++) {
        // System.out.println(arr[i] + " ");
        // }
        if (dif == 0) {
            System.out.println("NONE");
        } else {
            System.out.println(dif);
        }
    }

}
