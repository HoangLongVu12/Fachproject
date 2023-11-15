package week4.Zoo;

import java.util.Scanner;

public class Zoo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for(int i = 0; i < n; i++) {
            arr1[i] = sc.nextInt();
        }
        for(int i = 0; i < n; i++) {
            arr2[i] = sc.nextInt();
        }
        ascendingSort(arr1);
        descendingSort(arr2);
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += arr1[i] * arr2[i]* 1000;
        }
        System.out.println(sum);
    }
    public static void ascendingSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }



    public static void descendingSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }
    }
}
