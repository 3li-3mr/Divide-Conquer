package problem2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream in = (args.length >= 1) ? new FileInputStream(args[0]) : System.in;
        OutputStream outStream = (args.length >= 2) ? new FileOutputStream(args[1]) : System.out;

        Scanner sc = new Scanner(new BufferedInputStream(in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(outStream));

        MinPerimeterSolver solver = new MinPerimeterSolver();

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                long x = sc.nextLong();
                long y = sc.nextLong();
                points[i] = new Point(x, y);
            }
            double ans = solver.solve(points);
            out.printf(Locale.US, "%.3f%n", ans);
        }

        out.flush();
        if (args.length >= 2) {
            out.close();
        }
        sc.close();
    }
}