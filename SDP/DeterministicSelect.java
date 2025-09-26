package SDP;

import java.util.Arrays;

public class DeterministicSelect {
    private final MetricsCollector metrics;

    public DeterministicSelect(MetricsCollector metrics) {
        this.metrics = metrics;
    }

    // main method to call: find k-th smallest (1-based index)
    public int select(int[] arr, int k) {
        ArrayUtils.checkArray(arr);
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is out of bounds");
        }
        metrics.startTimer();
        int result = selectHelper(arr, 0, arr.length - 1, k);
        metrics.stopTimer();
        return result;
    }

    private int selectHelper(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();

        if (left == right) {
            metrics.exitRecursion();
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        int length = pivotIndex - left + 1;
        int result;

        if (k == length) {
            result = arr[pivotIndex];
        } else if (k < length) {
            result = selectHelper(arr, left, pivotIndex - 1, k);
        } else {
            result = selectHelper(arr, pivotIndex + 1, right, k - length);
        }

        metrics.exitRecursion();
        return result;
    }

    // use ArrayUtils.swap for consistency
    private int partition(int[] arr, int left, int right, int pivot) {
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                ArrayUtils.swap(arr, i, right);
                break;
            }
        }

        int i = left;
        for (int j = left; j < right; j++) {
            metrics.incrementComparisons();
            if (arr[j] < pivot) {
                ArrayUtils.swap(arr, i, j);
                metrics.incrementSwaps();
                i++;
            }
        }
        ArrayUtils.swap(arr, i, right);
        return i;
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;

        // if small group: sort directly
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        // divide into groups of 5 and collect medians
        int[] medians = new int[(n + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        // recursive step on medians
        return medianOfMedians(medians, 0, medians.length - 1);
    }

    // --- Simple test ---
    public static void main(String[] args) {
        MetricsCollector metrics = new MetricsCollector();
        DeterministicSelect ds = new DeterministicSelect(metrics);

        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int kthSmallest = ds.select(arr, k);

        System.out.println(k + "-th smallest element is: " + kthSmallest);
        System.out.println("Metrics CSV: " + metrics.toCSV("DeterministicSelect", arr.length));
    }
}
