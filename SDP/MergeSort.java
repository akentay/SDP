package SDP;

public class MergeSort {
    private final MetricsCollector metrics;
    private static final int CUTOFF = 10; // small arrays use insertion sort

    public MergeSort(MetricsCollector metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        metrics.startTimer();
        mergeSort(arr, new int[arr.length], 0, arr.length - 1);
        metrics.stopTimer();
    }

    private void mergeSort(int[] arr, int[] temp, int left, int right) {
        metrics.enterRecursion();

        // cutoff: use insertion sort for small arrays
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right);
            metrics.exitRecursion();
            return;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
        metrics.exitRecursion();
    }

    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // copy into reusable buffer
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
                metrics.incrementSwaps();
            } else {
                arr[k++] = temp[j++];
                metrics.incrementSwaps();
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
            metrics.incrementSwaps();
        }
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    metrics.incrementSwaps();
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }

    // --- Simple test ---
    public static void main(String[] args) {
        MetricsCollector metrics = new MetricsCollector();
        MergeSort sorter = new MergeSort(metrics);

        int[] arr = {8, 3, 5, 1, 9, 6, 2, 7, 4};
        sorter.sort(arr);

        System.out.print("Sorted: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println("\nMetrics CSV: " + metrics.toCSV("MergeSort", arr.length));
    }
}
