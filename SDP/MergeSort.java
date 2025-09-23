package algos;

public class MergeSort {
    private final MetricsCollector metrics;

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
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
        metrics.exitRecursion();
    }

    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (temp[i] <= temp[j]) arr[k++] = temp[i++];
            else arr[k++] = temp[j++];
        }
        while (i <= mid) arr[k++] = temp[i++];
    }
}
