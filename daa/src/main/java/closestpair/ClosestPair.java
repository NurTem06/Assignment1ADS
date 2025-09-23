package closestpair;

import java.util.*;

public class ClosestPair {

    // Главный метод: найти минимальное расстояние
    public static double findClosestPair(Point[] points) {
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, Comparator.comparingDouble(p -> p.x));

        Point[] pointsByY = points.clone();
        Arrays.sort(pointsByY, Comparator.comparingDouble(p -> p.y));

        return closestPair(pointsByX, pointsByY, points.length);
    }

    // Рекурсивное деление
    private static double closestPair(Point[] pointsByX, Point[] pointsByY, int n) {
        if (n <= 3) {
            return bruteForce(pointsByX, n);
        }

        int mid = n / 2;
        Point midPoint = pointsByX[mid];

        Point[] leftX = Arrays.copyOfRange(pointsByX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(pointsByX, mid, n);

        List<Point> leftY = new ArrayList<>();
        List<Point> rightY = new ArrayList<>();
        for (Point p : pointsByY) {
            if (p.x <= midPoint.x) leftY.add(p);
            else rightY.add(p);
        }

        double dl = closestPair(leftX, leftY.toArray(new Point[0]), leftX.length);
        double dr = closestPair(rightX, rightY.toArray(new Point[0]), rightX.length);

        double d = Math.min(dl, dr);

        // Проверяем "strip"
        List<Point> strip = new ArrayList<>();
        for (Point p : pointsByY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip.add(p);
            }
        }

        return Math.min(d, stripClosest(strip, d));
    }

    // Проверка расстояний в полосе
    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, distance(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    // Brute force для маленьких массивов
    private static double bruteForce(Point[] points, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    // Евклидово расстояние
    private static double distance(Point a, Point b) {
        return Math.hypot(a.x - b.x, a.y - b.y);
    }
}
