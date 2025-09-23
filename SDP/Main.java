import algos.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.randomArray(10, 100);
        System.out.println("Original array:");
        ArrayUtils.printArray(arr);

        MetricsCollector metrics = new MetricsCollector();

        // MergeSort
        MergeSort ms = new MergeSort(metrics);
        ms.sort(ArrayUtils.copy(arr));
        System.out.println("MergeSort -> Time: " + metrics.getElapsedTime() + " ns, Depth: " + metrics.getMaxRecursionDepth() + ", Comparisons: " + metrics.getComparisons());

        // QuickSort
        metrics = new MetricsCollector();
        QuickSort qs = new QuickSort(metrics);
        qs.sort(ArrayUtils.copy(arr));
        System.out.println("QuickSort -> Time: " + metrics.getElapsedTime() + " ns, Depth: " + metrics.getMaxRecursionDepth() + ", Comparisons: " + metrics.getComparisons());

        // Deterministic Select
        metrics = new MetricsCollector();
        DeterministicSelect ds = new DeterministicSelect(metrics);
        int k = 5;
        int kth = ds.select(ArrayUtils.copy(arr), k);
        System.out.println(k + "-th smallest element (Select): " + kth);

        // Closest Pair
        ClosestPair cp = new ClosestPair();
        ClosestPair.Point[] points = {
            new ClosestPair.Point(2, 3),
            new ClosestPair.Point(12, 30),
            new ClosestPair.Point(40, 50),
            new ClosestPair.Point(5, 1),
            new ClosestPair.Point(12, 10),
            new ClosestPair.Point(3, 4)
        };
        double dist = cp.findClosest(points);
        System.out.println("Closest pair distance: " + dist);
    }
}
