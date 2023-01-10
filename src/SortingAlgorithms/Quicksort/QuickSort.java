package SortingAlgorithms.Quicksort;

import java.util.Arrays;

public class QuickSort {
    public static int[] arr = { 16,24,17,22,23,7,4,26,2,75,58,73,69 };
    public static void start(){
        // compare int i with the intArray (arr)
        for( int i = 1; i < arr.length; i++ ) {
            // make temp equal the intArray based on the int i
            int temp = arr[i];
            // looking for the current int inside the intArray based on j(=i) (if 0 than 16, if 1 than 24, and so on...)
            for( int j = i; j >= 0; j-- ) {
                // checking if the current int is smaller/ bigger than the previous int
                // if so, continue inside the if method
                if( j > 0 && arr[j-1] > temp ) {
                    // let the current int move 1 to the left (because the current int is smaller than the previous
                    arr[j] = arr[j-1];
                    // listing the int Array list with the current state and print it inside the console
                    System.out.println("Temping:  " + Arrays.toString(arr));
                } else {
                    // if the current int is bigger than previous, make temp equals the new row
                    arr[j] = temp;
                    // listing the int Array list (step by step) and print it inside the console
                    System.out.println("Sorting:  " + Arrays.toString(arr));
                    break;
                }
            }
        }
    }
}
