package mergesort;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sortBuffer(int[] a) {
        if (a == null || a.length <= 1)
            return;
        int[] aux = new int[a.length];
        mergesortBuffer(a, aux, 0, a.length - 1);
    }

    private static void mergesortBuffer(int[] a, int[] aux, int l, int r) {
        if (l >= r)
            return;
        int m = (l + r) / 2;
        mergesortBuffer(a, aux, l, m);
        mergesortBuffer(a, aux, m + 1, r);
        merge(a, aux, l, m, r);
    }


    public static void sortCutoff(int[] a) {
        if (a == null || a.length <= 1)
            return;
        int[] aux = new int[a.length];
        mergesortCutoff(a, aux, 0, a.length - 1);
    }

    private static void mergesortCutoff(int[] a, int[] aux, int l, int r) {
        if (r - l <= CUTOFF) {
            insertionSort(a, l, r);
            return;
        }
        if (l >= r)
            return;
        int m = (l + r) / 2;
        mergesortCutoff(a, aux, l, m);
        mergesortCutoff(a, aux, m + 1, r);
        merge(a, aux, l, m, r);
    }

    private static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int key = a[i], j = i - 1;
            while (j >= l && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int[] aux, int l, int m, int r) {
        for (int k = l; k <= r; k++) aux[k] = a[k];

        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            if (i > m) a[k] = aux[j++];
            else if (j > r) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
