package problem1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<SelectionAlgorithm> algorithms = List.of(
            new NaiveSortSelect(),
            new RandomizedSelect(),
            new DeterministicSelect()
        );

        verify(algorithms);
        runBenchmarks(algorithms);
    }

    private static void verify(List<SelectionAlgorithm> algorithms) {
        // TODO: Generate random arrays, clone before passing to each algorithm,
        // and verify all three return the exact same median.
    }

    private static void runBenchmarks(List<SelectionAlgorithm> algorithms) {
        // TODO: Test across growing input sizes up to 10^7, averaging multiple runs.
    }
}
