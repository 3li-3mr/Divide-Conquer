package problem1;

import java.util.Arrays;

public class NaiveSortSelect implements SelectionAlgorithm {

    @Override
    public int select(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }

    @Override
    public String getName() {
        return "Naive Sort Select";
    }
}