package problem1;

public interface SelectionAlgorithm {
    /**
     * Returns the k-th smallest element in the array (0-indexed: k = 0 is minimum, k = n/2 is median).
     */
    int select(int[] arr, int k);

    /**
     * Returns the display name of the algorithm for benchmark reporting.
     */
    String getName();
}
