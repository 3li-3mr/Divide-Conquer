package problem2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream in = (args.length > 0) ? new FileInputStream(args[0]) : System.in;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        MinPerimeterSolver solver = new MinPerimeterSolver();

        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);
            Point[] points = new Point[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());
                points[i] = new Point(x, y);
            }

            double minPerimeter = solver.solve(points);
            out.printf(Locale.US, "%.3f%n", minPerimeter);
        }
        out.flush();
    }
}
