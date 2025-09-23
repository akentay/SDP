package algos;

import java.util.Arrays;

public class DeterministicSelect {
    private final MetricsCollector metrics;

    public DeterministicSelect(MetricsCollector metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        return selectHelper(arr, 0, arr.length - 1, k);
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
        if (k == length) {
            metrics.exitRecursion();
            return arr[pivotIndex];
        } else if (k < length) {
            int res = selectHelper(arr, left, pivotIndex - 1, k);
            metrics.exitRecursion();
            return res;
        } else {
            int res = selectHelper(arr, pivotIndex + 1, right, k - length);
            metrics.exitRecursion();
            return res;
        }
    }

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
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        int[] medians = new int[(n + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1);
    }
}
