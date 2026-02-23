package org.dataStructure.problems.searching;

public class BinarySearch {

    public void binarySearch(int[] arr, int value){
        int start = 0;
//        int end = arr.length;
        int end = arr.length-1;
//        int mid = arr.length/2;
        int mid = (start+end)/2;

        if(arr[start] == value) System.out.println("Index : " + start);
        else if(arr[end-1] == value) System.out.println("Index : " + (end-1));
        else {
            while (/*mid < arr.length*/ start <= end && arr[mid] != value) {
                if (arr[mid] > value) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }
                mid = (end + start)/2;
            }

           if(arr[mid] == value) System.out.println("Index : " + mid);
           else System.out.println("The value is not present in the arrayList");
        }
    }
    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] arr = {1,2,3,4,5,6,7};
        search.binarySearch(arr, 8);

      /*Time Complexity - O(log n) -> 2^x = N where x is the number of steps to complete the search for the worst case scenario.
      * For 16 elements array it takes 4 steps, 2^4 = 16; Similarly, for 32 elements array it takes 5 steps to complete, 2^5 = 32*/
    }
}
