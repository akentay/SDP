package SDP;

import java.util.Random;

public class ArrayUtils {
    private static final Random rand = new Random();

    // swap two elements in array
    public static void swap(int[] arr, int i, int j) {
        if (arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length) {
            throw new IllegalArgumentException("Invalid indices for swap");
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // shuffle array randomly (Fisher–Yates shuffle)
    public static void shuffle(int[] arr) {
        if (arr == null) return;
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    // partition (used in QuickSort & Select)
    public static int partition(int[] arr, int left, int right, int pivot, MetricsCollector metrics) {
        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                metrics.incrementSwaps();
                i++;
            }
        }
        swap(arr, i, right);
        metrics.incrementSwaps();
        return i;
    }

    // guard: check if array is sorted
    public static boolean isSorted(int[] arr) {
        if (arr == null) return false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) return false;
        }
        return true;
    }

    // guard: validate not null and not empty
    public static void checkArray(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
        if (arr.length == 0) throw new IllegalArgumentException("Array cannot be empty");
    }
}
