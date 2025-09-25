import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private static int select(int[] arr, int left, int right, int k) {
        while (true) {
            if (left == right) {
                return arr[left];
            }

            int pivotValue = medianOfMedians(arr, left, right);
            int pivotIndex = partition(arr, left, right, pivotValue);

            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
    }

    // Правильный partition
    private static int partition(int[] arr, int left, int right, int pivotValue) {
        // Находим индекс pivot и ставим его в конец
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(arr, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }

        // Ставим pivot обратно на своё место
        swap(arr, storeIndex, right);
        return storeIndex; // возвращаем индекс pivot
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;

        // Если элементов мало, просто берём медиану напрямую
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return arr[left + n / 2];
        }

        // Разбиваем на группы по 5 и находим медианы
        int numMedians = (int) Math.ceil(n / 5.0);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

       
        return medianOfMedians(medians, 0, numMedians - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
