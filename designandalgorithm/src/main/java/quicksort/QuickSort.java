package quicksort;

import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            // выбираем случайный pivot
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];
            swap(arr, pivotIndex, high);

            int partitionIndex = partition(arr, low, high, pivot);

            // рекурсивно вызываем меньшую часть
            if (partitionIndex - low < high - partitionIndex) {
                quickSort(arr, low, partitionIndex - 1);
                low = partitionIndex + 1;
            } else {
                quickSort(arr, partitionIndex + 1, high);
                high = partitionIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, int pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Для проверки руками
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr);
        System.out.print("Sorted: ");
        for (int x : arr) {
            System.out.print(x + " ");
        }
    }
}
