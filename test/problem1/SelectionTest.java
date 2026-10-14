package problem1;

import java.util.List;
import java.util.Random;

public class SelectionTest {

    private static final List<SelectionAlgorithm> ALGORITHMS = List.of(
        new NaiveSortSelect(),
        new RandomizedSelect(),
        new DeterministicSelect()
    );

    public static void main(String[] args) {
        System.out.println("Running Problem 1 Test Suite...");

        for (SelectionAlgorithm algo : ALGORITHMS) {
            testEdgeCases(algo);
            System.out.println("  [PASS] Edge cases: " + algo.getName());
        }

        testCrossAlgorithmEquivalence();
        System.out.println("  [PASS] Cross-algorithm equivalence (500 random trials)");

        System.out.println("\nAll tests passed successfully!");
    }

    private static void testEdgeCases(SelectionAlgorithm algo) {
        // 1. Single element
        assertEqual(algo.select(new int[]{42}, 0), 42, algo.getName() + " single element");

        // 2. Two elements
        assertEqual(algo.select(new int[]{10, 5}, 0), 5, algo.getName() + " two elements (k=0)");
        assertEqual(algo.select(new int[]{10, 5}, 1), 10, algo.getName() + " two elements (k=1)");

        // 3. Already sorted (odd length = 7, not divisible by 5)
        int[] sorted = {1, 2, 3, 4, 5, 6, 7};
        assertEqual(algo.select(sorted.clone(), 0), 1, algo.getName() + " sorted min");
        assertEqual(algo.select(sorted.clone(), 3), 4, algo.getName() + " sorted median");
        assertEqual(algo.select(sorted.clone(), 6), 7, algo.getName() + " sorted max");

        // 4. Reverse sorted (length = 8)
        int[] reverse = {80, 70, 60, 50, 40, 30, 20, 10};
        assertEqual(algo.select(reverse.clone(), 0), 10, algo.getName() + " reverse min");
        assertEqual(algo.select(reverse.clone(), 4), 50, algo.getName() + " reverse median");
        assertEqual(algo.select(reverse.clone(), 7), 80, algo.getName() + " reverse max");

        // 5. Negative numbers
        int[] negatives = {-5, -100, 0, 50, -20, -1};
        assertEqual(algo.select(negatives.clone(), 0), -100, algo.getName() + " negatives min");
        assertEqual(algo.select(negatives.clone(), 2), -5, algo.getName() + " negatives k=2");
    }

    private static void testCrossAlgorithmEquivalence() {
        Random rand = new Random(12345);
        SelectionAlgorithm naive = new NaiveSortSelect();
        SelectionAlgorithm randomized = new RandomizedSelect();
        SelectionAlgorithm deterministic = new DeterministicSelect();

        for (int trial = 0; trial < 500; trial++) {
            // Test random sizes between 1 and 2,500 (hits all mod-5 remainders)
            int n = 1 + rand.nextInt(2500);
            int k = rand.nextInt(n); // Also test random ranks k, plus median n/2
            if (trial % 2 == 0) {
                k = n / 2;
            }

            int[] master = new int[n];
            for (int i = 0; i < n; i++) {
                master[i] = rand.nextInt();
            }

            int expected = naive.select(master.clone(), k);
            int randAns = randomized.select(master.clone(), k);
            int detAns = deterministic.select(master.clone(), k);

            assertEqual(randAns, expected, "Trial " + trial + " (n=" + n + ", k=" + k + ") Randomized mismatch");
            assertEqual(detAns, expected, "Trial " + trial + " (n=" + n + ", k=" + k + ") Deterministic mismatch");
        }
    }

    private static void assertEqual(int actual, int expected, String message) {
        if (actual != expected) {
            throw new AssertionError(message + " | Expected: " + expected + ", Actual: " + actual);
        }
    }
}
