package problem1;

public class DeterministicSelect implements SelectionAlgorithm {

    @Override
    public int select(int[] arr, int k) {
        // TODO: Implement CLRS 9.3 Select (Median-of-Medians)
        throw new UnsupportedOperationException("DeterministicSelect not implemented yet.");
    }

    private int deterministicSelect(int[] arr, int left, int right, int k) {
        // TODO: Recursive helper using groups of 5
        return 0;
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        // TODO: Find pivot using median-of-medians
        return 0;
    }

    private int partitionAroundPivot(int[] arr, int left, int right, int pivotValue) {
        // TODO: Partition around the chosen median-of-medians pivot
        return 0;
    }

    @Override
    public String getName() {
        return "Deterministic Select (median of medians)";
    }
}
