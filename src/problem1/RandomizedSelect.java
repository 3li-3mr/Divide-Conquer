package problem1;

public class RandomizedSelect implements SelectionAlgorithm {

    @Override
    public int select(int[] arr, int k) {
        // TODO: Implement CLRS 9.2 Randomized-Select
        throw new UnsupportedOperationException("RandomizedSelect not implemented yet.");
    }

    private int randomizedSelect(int[] arr, int left, int right, int k) {
        // TODO: Recursive helper
        return 0;
    }

    private int randomizedPartition(int[] arr, int left, int right) {
        // TODO: Random pivot selection and partitioning
        return 0;
    }

    @Override
    public String getName() {
        return "Randomized D&C Select";
    }
}
