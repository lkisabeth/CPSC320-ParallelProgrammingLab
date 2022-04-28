import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        // instantiate an array of 200M random numbers
        int[] array = new int[200000000];
        Random rand = new Random();

        for(int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt();
        }

        // instantiate ParallelMergeSort using the array we just made
        ParallelMergeSort all = new ParallelMergeSort(array, 0, array.length-1);
        // instantiate a new ForkJoinPool and use it to run the ParallelMergeSort on a pool of threads
        ForkJoinPool pool = new ForkJoinPool();

        int[] result = pool.invoke(all);

        // verify that the result is sorted
        System.out.println(isSorted(result) ? "Sorted" : "Not Sorted");
        /* uncomment this section to print the whole sorted array
        for (int num : result) {
            System.out.print(num + " ");
        }
         */
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            // if you encounter an element out of order, return false
            if (array[i] > array[i + 1]) return false;
        }
        // otherwise, the array is sorted, so return true
        return true;
    }
}