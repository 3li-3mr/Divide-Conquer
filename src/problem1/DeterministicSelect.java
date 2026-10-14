package problem1;

public class DeterministicSelect implements SelectionAlgorithm {

    @Override
    public int select(int[] arr, int k) {
        return deterministicSelect(arr, 0, arr.length - 1 , k);
    }

    private int deterministicSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivot = medianOfMedians(arr, left, right);
        int i = partition(arr, left, right, pivot);
        if(i == k) return arr[i];
        else if(i > k){
            return deterministicSelect(arr, left, i-1, k);
        }
        else{
            return deterministicSelect(arr, i+1, right, k);
        }
    }

    private int medianOfMedians(int[] arr, int left, int right) {
        int gps = (right - left) / 5 + 1;
        int[] medians = new int[gps];
        for(int i = 0; i < gps; i++){
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);
            insertionSort(arr, groupLeft, groupRight);
            int mid = groupLeft + (groupRight - groupLeft) / 2;
            medians[i] = arr[mid];
        }
        return deterministicSelect(medians, 0, gps - 1, gps/2);
    }

    private void insertionSort(int[] arr, int left, int right){
        for(int i = left + 1; i <= right; i++){
            int j = i - 1;
            while(j >= left && arr[j] > arr[j+1]){
                swap(arr, j, j+1);
                j--;
            }
        }
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        for (int i = left; i <= right; i++) {
            if (arr[i] == pivot) {
                swap(arr, left, i);
                break;
            }
        }
        int j = left + 1;
        for(int i = left + 1; i <= right; i++){
            if(arr[i] < arr[left]){
                swap(arr, i, j);
                j++;
            }
        }
        j--;
        swap(arr, j, left);
        return j;   
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String getName() {
        return "Deterministic Select (median of medians)";
    }
}
