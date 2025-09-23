package mergesort;

import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {
    public static void main(String[] args) {
        int[] arr1 = {5, 2, 9, 1, 6, 3};
        int[] arr2 = arr1.clone();

        MergeSort.sortBuffer(arr1);
        System.out.println("Buffer: " + Arrays.toString(arr1));

        MergeSort.sortCutoff(arr2);
        System.out.println("With cutoff: " + Arrays.toString(arr2));

        int[] big1 = new Random().ints(20, 0, 100).toArray();
        int[] big2 = big1.clone();

        MergeSort.sortBuffer(big1);
        MergeSort.sortCutoff(big2);

        System.out.println("Buffer only (big numbers): " + Arrays.toString(big1));
        System.out.println("With cutoff (big numbers): " + Arrays.toString(big2));
    }
}


