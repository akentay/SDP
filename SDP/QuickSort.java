package algos;

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
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            ArrayUtils.swap(arr, pivotIndex, right);

            int partitionIndex = partition(arr, left, right, pivot);

            if (partitionIndex - left < right - partitionIndex) {
                quickSort(arr, left, partitionIndex - 1);
                left = partitionIndex + 1;
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
            metrics.incrementComparisons();
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
}
 