import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SortingAlgorithmsTest {

    @Test
    public void testMergeSort() {
        int[] arr = {5, 3, 8, 1, 2};
        MergeSort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }

    @Test
    public void testSelectionSort() {
        int[] arr = {10, 7, 4, 3, 1};
        SelectionSort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 4, 7, 10}, arr);
    }
}
