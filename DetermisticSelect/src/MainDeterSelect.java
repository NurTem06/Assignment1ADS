import java.util.Random;
import java.util.Arrays;

public class MainDeterSelect{
    public static void main(String[] args) {
        Random rnd = new Random();
        int n = 15;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(100);
        }

        System.out.println("Массив: " + Arrays.toString(arr));
        int k = n / 2;
        int result = DeterministicSelect.select(arr.clone(), k);

        System.out.println("Медиана (Deterministic Select): " + result);
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        System.out.println("Медиана (через Arrays.sort): " + sorted[k]);
    }
}

