package SDP;

import java.util.Random;

public class QuickSort {
    private final MetricsCollector metrics;
    private final Random rand = new Random();

    public QuickSort(MetricsCollector metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        metrics.startTimer();
        quickSort(arr, 0, arr.length - 1);
        metrics.stopTimer();
    }

    private void quickSort(int[] arr, int left, int right) {
        metrics.enterRecursion();
        while (left < right) {
            // randomized pivot
            int pivotIndex = left + rand.nextInt(right - left + 1);
            metrics.incrementAllocations(); // track pivot choice
            int pivot = arr[pivotIndex];
            ArrayUtils.swap(arr, pivotIndex, right);

            int partitionIndex = partition(arr, left, right, pivot);

            // recurse smaller side first
            if (partitionIndex - left < right - partitionIndex) {
                quickSort(arr, left, partitionIndex - 1);
                left = partitionIndex + 1; // iterate larger side
            } else {
                quickSort(arr, partitionIndex + 1, right);
                right = partitionIndex - 1;
            }
        }
        metrics.exitRecursion();
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons(); // track comparison
            if (arr[j] <= pivot) {
                ArrayUtils.swap(arr, i, j);
                metrics.incrementSwaps();
                i++;
            }
        }
        ArrayUtils.swap(arr, i, right);
        metrics.incrementSwaps();
        return i;
    }

    // --- Simple test method ---
    public static void main(String[] args) {
        MetricsCollector metrics = new MetricsCollector();
        QuickSort sorter = new QuickSort(metrics);

        int[] arr = {5, 2, 9, 1, 7, 3};
        sorter.sort(arr);

        System.out.println("Sorted: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nMetrics CSV: " + metrics.toCSV("QuickSort", arr.length));
    }
}
