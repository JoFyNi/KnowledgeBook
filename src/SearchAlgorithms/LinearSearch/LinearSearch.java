package SearchAlgorithms.LinearSearch;

public class LinearSearch {

    public static int start(int key) {
        int[] a1= {10,20,30,50,70,90};
        //int key = 50;
        System.out.println(key+" is found at index: "+linearSearch(a1, key));
        //resultArea(key, linearSearch(a1, key));
        return linearSearch(a1, key);
    }

    public static int linearSearch(int[] arr, int key){
        for(int i=0;i<arr.length;i++){
            /*
             repeat for the length of the Integer array
             if i from the Integer array equal the Integer key
             return i
             */
            if(arr[i] == key){
                return i;
            }
        }
        return -1;
    }
}
