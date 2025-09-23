package quicksort;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    public void testSorting() {
        int[] arr = {5, 2, 8, 1, 3};
        QuickSort.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 5, 8}, arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        QuickSort.quickSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSort.quickSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
