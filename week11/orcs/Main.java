package week11.orcs;

import java.util.*;

public class Main {
    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] houses = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            houses[i] = new Point(x, y);
        }

        List<Point> hull = convexHull(houses, n);
        int fenceLength = (int) Math.ceil(calculatePerimeter(hull));
        System.out.println(fenceLength);
    }

    private static double calculatePerimeter(List<Point> hull) {
        double perimeter = 0.0;
        for (int i = 0; i < hull.size(); i++) {
            Point p1 = hull.get(i);
            Point p2 = hull.get((i + 1) % hull.size());
            perimeter += distance(p1, p2);
        }
        return perimeter;
    }

    private static double distance(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

    private static List<Point> convexHull(Point[] points, int n) {
        if (n < 3) return null;

        List<Point> hull = new ArrayList<>();

        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].x < points[l].x)
                l = i;

        int p = l, q;
        do {
            hull.add(points[p]);
            q = (p + 1) % n;

            for (int i = 0; i < n; i++) {
                if (orientation(points[p], points[i], points[q]) == 2)
                    q = i;
            }

            p = q;

        } while (p != l);

        return hull;
    }

    private static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;  // colinear
        return (val > 0) ? 1 : 2; // clock or counterclockwise
    }
}
