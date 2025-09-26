package SDP;

public class MetricsCollector {
    private long comparisons = 0;
    private long swaps = 0;
    private int maxRecursionDepth = 0;
    private int currentDepth = 0;
    private long allocations = 0;
    private long startTime;
    private long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public void incrementComparisons() { comparisons++; }
    public void incrementSwaps() { swaps++; }
    public void incrementAllocations() { allocations++; }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxRecursionDepth) {
            maxRecursionDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAllocations() { return allocations; }
    public int getMaxRecursionDepth() { return maxRecursionDepth; }

    // CSV writer (returns a line with metrics)
    public String toCSV(String algorithmName, int n) {
        return algorithmName + "," + n + "," + getElapsedTime() + "," 
                + comparisons + "," + swaps + "," + allocations + "," + maxRecursionDepth;
    }
}
