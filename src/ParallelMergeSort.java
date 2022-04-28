import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {
    private final int[] array;
    private final int start;
    private final int end;

    public ParallelMergeSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected int[] compute() {
        if (start < end) {
            int mid = (start + end) / 2;
            ParallelMergeSort left = new ParallelMergeSort(array, start, mid);
            ParallelMergeSort right = new ParallelMergeSort(array, mid + 1, end);

            invokeAll(left, right);

            merge(array, start, mid, end);
        }

        return array;
    }

    private void merge(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];

        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }

        if (i <= mid) {
            while (i <= mid) {
                temp[k] = array[i];
                i++;
                k++;
            }
        }

        if (j <= end) {
            while (j <= end) {
                temp[k] = array[j];
                j++;
                k++;
            }
        }

        k = 0;
        while (start <= end) {
            array[start] = temp[k];
            start++;
            k++;
        }
    }
}