package week11.sweep;

import java.util.*;
import java.io.*;

public class abc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Polygon[] houses = new Polygon[n];

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            Point[] points = new Point[k];
            for (int j = 0; j < k; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                points[j] = new Point(x, y);
            }
            houses[i] = new Polygon(points);
        }

        int[] angles = new int[m];

        for (int i = 0; i < m; i++) {
            int xs = sc.nextInt();
            int ys = sc.nextInt();
            int zs = sc.nextInt();
            int xt = sc.nextInt();
            int yt = sc.nextInt();
            int zt = sc.nextInt();

            double minAngle = Double.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                double angle = houses[j].getMinAngle(xs, ys, zs, xt, yt, zt);
                minAngle = Math.min(minAngle, angle);
            }

            if (minAngle == Double.MAX_VALUE) {
                System.out.println("impossible");
                return;
            }

            angles[i] = (int) Math.ceil(Math.toDegrees(minAngle));
        }

        int maxAngle = 0;
        for (int angle : angles) {
            maxAngle = Math.max(maxAngle, angle);
        }

        System.out.println(maxAngle);
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Polygon {
    Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }

    public double getMinAngle(int xs, int ys, int zs, int xt, int yt, int zt) {
        double minAngle = Double.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            int x1 = points[i].x;
            int y1 = points[i].y;
            int x2 = points[(i + 1) % points.length].x;
            int y2 = points[(i + 1) % points.length].y;

            double angle1 = Math.atan2(y1 - ys, x1 - xs) - Math.atan2(yt - ys, xt - xs);
            double angle2 = Math.atan2(y2 - ys, x2 - xs) - Math.atan2(yt - ys, xt - xs);

            angle1 = (angle1 + 2 * Math.PI) % (2 * Math.PI);
            angle2 = (angle2 + 2 * Math.PI) % (2 * Math.PI);

            double angle = Math.abs(angle2 - angle1);

            if (angle > Math.PI) {
                angle = 2 * Math.PI - angle;
            }

            minAngle = Math.min(minAngle, angle);
        }

        return minAngle;
    }
}
