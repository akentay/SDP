package algos;

import java.util.*;

public class ClosestPair {
    public static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public double findClosest(Point[] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p.x));
        return closest(points, 0, points.length - 1);
    }

    private double closest(Point[] points, int left, int right) {
        if (right - left <= 3) return bruteForce(points, left, right);

        int mid = (left + right) / 2;
        double d1 = closest(points, left, mid);
        double d2 = closest(points, mid + 1, right);
        double d = Math.min(d1, d2);

        List<Point> strip = new ArrayList<>();
        int midX = points[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < d) strip.add(points[i]);
        }
        strip.sort(Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }

    private double bruteForce(Point[] points, int left, int right) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    private double distance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }
}
