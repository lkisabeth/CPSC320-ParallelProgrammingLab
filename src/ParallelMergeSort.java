import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {
    private int[] array;
    private int start;
    private int end;

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

            merge(array, left.getRawResult(), mid, right.getRawResult());
        }

        return array;
    }

    private void merge(int[] array, int[] left, int mid, int[] right) {
        int start2 = mid + 1;
        int index = start;

        while (start <= mid && start2 <= end) {
            if (left[start] <= right[start2]) {
                array[index] = left[start];
                start++;
            } else {
                array[index] = right[start2];
                start2++;
            }
            index++;
        }

        while (start <= mid) {
            array[index] = left[start];
            index++;
            start++;
        }

        while (start2 <= end) {
            array[index] = right[start2];
            index++;
            start2++;
        }
    }
}