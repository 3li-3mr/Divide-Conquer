package problem1;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Main {

    private static final int WARMUP_RUNS = 10;
    private static final int BENCHMARK_RUNS = 50;
    private static final int[] INPUT_SIZES = {
        1_000, 10_000, 100_000, 500_000, 1_000_000, 5_000_000, 10_000_000
    };

    public static void main(String[] args) {
        List<SelectionAlgorithm> algorithms = List.of(
            new NaiveSortSelect(),
            new RandomizedSelect(),
            new DeterministicSelect()
        );

        warmupJvm(algorithms);
        runBenchmarks(algorithms);
    }

    private static void warmupJvm(List<SelectionAlgorithm> algorithms) {
        System.out.println("Warming up JVM...");
        Random rand = new Random(42);
        int warmupSize = 1_000_000;
        int k = warmupSize / 2;

        for (int r = 0; r < WARMUP_RUNS; r++) {
            int[] master = generateRandomArray(warmupSize, rand);
            for (SelectionAlgorithm algo : algorithms) {
                int[] copy = master.clone();
                algo.select(copy, k);
            }
        }
        System.out.println("Warmup complete.\n");
    }

    private static void runBenchmarks(List<SelectionAlgorithm> algorithms) {
        Random rand = new Random(100);

        System.out.printf("%-12s | %-28s | %-28s | %-28s%n",
                "N",
                algorithms.get(0).getName(),
                algorithms.get(1).getName(),
                algorithms.get(2).getName());
        System.out.println("-".repeat(105));

        for (int n : INPUT_SIZES) {
            int k = n / 2;
            long[] totalTimeNanos = new long[algorithms.size()];

            for (int run = 0; run < BENCHMARK_RUNS; run++) {
                int[] master = generateRandomArray(n, rand);
                Integer baselineResult = null;

                for (int i = 0; i < algorithms.size(); i++) {
                    int[] copy = master.clone();
                    System.gc();

                    long start = System.nanoTime();
                    int result = algorithms.get(i).select(copy, k);
                    long elapsed = System.nanoTime() - start;

                    totalTimeNanos[i] += elapsed;

                    if (baselineResult == null) {
                        baselineResult = result;
                    } else if (result != baselineResult) {
                        throw new IllegalStateException("Mismatch at N=" + n + " from " + algorithms.get(i).getName());
                    }
                }
            }

            double avg0Ms = (totalTimeNanos[0] / (double) BENCHMARK_RUNS) / 1_000_000.0;
            double avg1Ms = (totalTimeNanos[1] / (double) BENCHMARK_RUNS) / 1_000_000.0;
            double avg2Ms = (totalTimeNanos[2] / (double) BENCHMARK_RUNS) / 1_000_000.0;

            System.out.printf(Locale.US, "%-12d | %25.3f ms | %25.3f ms | %25.3f ms%n",
                    n, avg0Ms, avg1Ms, avg2Ms);
        }
    }

    private static int[] generateRandomArray(int size, Random rand) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
    }
}