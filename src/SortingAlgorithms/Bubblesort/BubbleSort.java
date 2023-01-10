package SortingAlgorithms.Bubblesort;

public class BubbleSort {
    public static void main(String[] args) {

    }

    /**
     *     The outer loop with variable i, that iterates through the array n-1 times.
     *     The inner loop with variable j, that iterates through the array up to the last unsorted element.
     *     The if statement that checks whether the current element is greater than the next element and if it is, it swaps them using a temporary variable.
     */
    public static void start() {

        int[] array = {5, 2, 8, 1, 9, 4, 3, 7, 6};
        int n = array.length;

        // outer loop - iterates through the array n-1 times
        for (int i = 0; i < n-1; i++) {
            // inner loop - iterates through the array up to the last unsorted element
            for (int j = 0; j < n-i-1; j++) {
                // if current element is greater than the next element, swap them
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        System.out.println("Sorted Array:");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
