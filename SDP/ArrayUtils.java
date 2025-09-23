package algos;

import java.util.Random;
import java.util.Arrays;

public class ArrayUtils {
    public static void printArray(int[] arr) {
        for (int x : arr) System.out.print(x + " ");
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] randomArray(int n, int bound) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(bound);
        return arr;
    }

    public static int[] copy(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
}
