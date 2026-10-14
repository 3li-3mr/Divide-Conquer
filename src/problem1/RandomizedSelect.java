package problem1;

import java.util.Random;

public class RandomizedSelect implements SelectionAlgorithm {

    private final Random rand = new Random(42);

    @Override
    public int select(int[] arr, int k) {
        return randomizedSelect(arr, 0, arr.length-1, k);
    }

    private int randomizedSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int i = randomizedPartition(arr, left, right);
        if(i == k) return arr[i];
        else if(i > k){
            return randomizedSelect(arr, left, i-1, k);
        }
        else{
            return randomizedSelect(arr, i+1, right, k);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int randomizedPartition(int[] arr, int left, int right) {
        
        int r = left + rand.nextInt(right - left + 1);
        swap(arr, left, r);
        int j = left + 1;
        for(int i = left+1; i <= right; i++){
            if(arr[i] < arr[left]){
                swap(arr, i, j);
                j++;
            }
        }
        j--;
        swap(arr, left, j);
        return j;
    }

    @Override
    public String getName() {
        return "Randomized D&C Select";
    }
}
