package closestpair;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class ClosestPairTest {

    // Тест для небольшого массива
    @Test
    public void testSmallArray() {
        Point[] points = {
                new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4)
        };

        double result = ClosestPair.findClosestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6); // ближайшие (2,3) и (3,4)
    }

    // Сравнение с O(n^2) алгоритмом для проверки
    @Test
    public void testCompareWithBruteForce() {
        Random rand = new Random(42);
        for (int n = 2; n <= 200; n += 20) {
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
            }

            double fast = ClosestPair.findClosestPair(points);
            double brute = bruteForce(points);

            assertEquals(brute, fast, 1e-6);
        }
    }

    // Простой O(n^2) алгоритм для проверки
    private double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                min = Math.min(min, Math.hypot(points[i].x - points[j].x, points[i].y - points[j].y));
            }
        }
        return min;
    }
}
