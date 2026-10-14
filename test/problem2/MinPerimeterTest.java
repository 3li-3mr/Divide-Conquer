package problem2;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class MinPerimeterTest {

    private static final MinPerimeterSolver SOLVER = new MinPerimeterSolver();

    public static void main(String[] args) {
        System.out.println("Running Problem 2 Test Suite...");

        testSampleCases();
        System.out.println("  [PASS] PDF Sample Test Cases");

        testEdgeCases();
        System.out.println("  [PASS] Collinear & Coordinate Overflow Edge Cases");

        testRandomStressOracle();
        System.out.println("  [PASS] 100 Randomized Stress Trials vs Brute-Force Oracle");

        testLargeScalePerformance();
        System.out.println("\nAll Problem 2 tests passed successfully!");
    }

    private static void testSampleCases() {
        Point[] case1 = {
            new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(1, 3)
        };
        assertFormatted(SOLVER.solve(case1), "4.000", "Sample Case 1");

        Point[] case2 = {
            new Point(0, 0), new Point(3, 0), new Point(0, 4),
            new Point(10, 10), new Point(11, 10), new Point(10, 11)
        };
        assertFormatted(SOLVER.solve(case2), "3.414", "Sample Case 2");

        Point[] case3 = {
            new Point(0, 0), new Point(1, 0), new Point(3, 0)
        };
        assertFormatted(SOLVER.solve(case3), "6.000", "Sample Case 3");

        Point[] case4 = {
            new Point(0, 0), new Point(6, 0), new Point(0, 8)
        };
        assertFormatted(SOLVER.solve(case4), "24.000", "Sample Case 4");
    }

    private static void testEdgeCases() {
        // 1. All points on the exact same vertical line x = 5 (tests YL/YR tie-breaker)
        Point[] vertical = {
            new Point(5, 0), new Point(5, 10), new Point(5, 2),
            new Point(5, 3), new Point(5, 20), new Point(5, 100),
            new Point(5, 1), new Point(5, 50)
        };
        // Closest collinear triple is (5,0), (5,1), (5,2) -> perimeter = 2 * 2 = 4.000
        assertFormatted(SOLVER.solve(vertical), "4.000", "Vertical Collinear Line");

        // 2. Extreme coordinates (-10^6 to 10^6)
        Point[] extreme = {
            new Point(-1_000_000, -1_000_000),
            new Point(1_000_000, 1_000_000),
            new Point(-1_000_000, 1_000_000),
            new Point(0, 0), new Point(3, 4), new Point(6, 0)
        };
        // (0,0), (3,4), (6,0) has sides 5, 5, 6 -> perimeter 16.000
        assertFormatted(SOLVER.solve(extreme), "16.000", "Extreme Coordinates");
    }

    private static void testRandomStressOracle() {
        Random rand = new Random(42);
        for (int trial = 0; trial < 100; trial++) {
            int n = 6 + rand.nextInt(120);
            Point[] pts = generateDistinctPoints(n, 10_000, rand);

            double expected = bruteForceOracle(pts);
            double actual = SOLVER.solve(pts);

            if (Math.abs(expected - actual) > 1e-9) {
                throw new AssertionError(String.format(
                    "Trial %d (n=%d) failed! Expected: %.6f, Actual: %.6f",
                    trial, n, expected, actual));
            }
        }
    }

    private static void testLargeScalePerformance() {
        Random rand = new Random(999);
        int n = 100_000;
        Point[] pts = generateDistinctPoints(n, 1_000_000, rand);

        long start = System.nanoTime();
        double ans = SOLVER.solve(pts);
        double ms = (System.nanoTime() - start) / 1_000_000.0;

        System.out.printf(Locale.US, "  [PERF] N = %d solved in %.2f ms (Min Perimeter = %.3f)%n", n, ms, ans);
    }

    private static Point[] generateDistinctPoints(int n, int bound, Random rand) {
        Set<String> seen = new HashSet<>();
        Point[] pts = new Point[n];
        int idx = 0;
        while (idx < n) {
            long x = rand.nextInt(2 * bound + 1) - bound;
            long y = rand.nextInt(2 * bound + 1) - bound;
            String key = x + "," + y;
            if (seen.add(key)) {
                pts[idx++] = new Point(x, y);
            }
        }
        return pts;
    }

    private static double bruteForceOracle(Point[] pts) {
        double minP = Double.POSITIVE_INFINITY;
        int n = pts.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    double p = pts[i].distanceTo(pts[j])
                             + pts[j].distanceTo(pts[k])
                             + pts[k].distanceTo(pts[i]);
                    if (p < minP) minP = p;
                }
            }
        }
        return minP;
    }

    private static void assertFormatted(double actualVal, String expectedStr, String label) {
        String actualStr = String.format(Locale.US, "%.3f", actualVal);
        if (!actualStr.equals(expectedStr)) {
            throw new AssertionError(label + " | Expected: " + expectedStr + ", Actual: " + actualStr);
        }
    }
}
