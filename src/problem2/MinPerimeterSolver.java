package problem2;

import java.util.Arrays;

public class MinPerimeterSolver {

    public double solve(Point[] points) {
        Point[] X = points.clone();
        Arrays.sort(X, (a, b) -> {
            if (a.x != b.x) return Long.compare(a.x, b.x);
            return Long.compare(a.y, b.y);
        });
        Point[] Y = points.clone();
        Arrays.sort(Y, (a, b) -> {
            if (a.y != b.y) return Long.compare(a.y, b.y);
            return Long.compare(a.x, b.x);
        });
        return minPerimeter(X, Y);
    }

    private double minPerimeter(Point[] X, Point[] Y) {
        int n = X.length;
        if (n <= 5) {
            return baseCase(X);
        }
        int mid = n / 2;
        Point midPoint = X[mid - 1];
        Point[] XL = Arrays.copyOfRange(X, 0, mid);
        Point[] XR = Arrays.copyOfRange(X, mid, n);
        Point[] YL = new Point[mid];
        Point[] YR = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : Y) {
            if (p.x < midPoint.x || (p.x == midPoint.x && p.y <= midPoint.y)) {
                YL[li++] = p;
            } else {
                YR[ri++] = p;
            }
        }
        double delta1 = minPerimeter(XL, YL);
        double delta2 = minPerimeter(XR, YR);
        double delta = Math.min(delta1, delta2);

        Point[] Y_strip = new Point[n];
        int size = 0;
        for (Point p: Y) {
            if(Math.abs(p.x - midPoint.x) < delta / 2){
                Y_strip[size++] = p;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (Y_strip[j].y - Y_strip[i].y >= delta / 2) {
                    break;
                }
                for (int k = j + 1; k < size; k++) {
                    if (Y_strip[k].y - Y_strip[i].y >= delta / 2) {
                        break;
                    }
                    double p = perimeter(Y_strip[i], Y_strip[j], Y_strip[k]);
                    if (p < delta) {
                        delta = p;
                    }
                }
            }
        }
        return delta;
    }

    private double baseCase(Point[] pts) {
        double minP = Double.POSITIVE_INFINITY;
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double p = perimeter(pts[i], pts[j], pts[k]);
                    if (p < minP) {
                        minP = p;
                    }
                }
            }
        }
        return minP;
    }

    private double perimeter(Point a, Point b, Point c) {
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a);
    }
}