package org.leetcode.leetcode150.array_string;

//https://leetcode.com/problems/h-index/?envType=study-plan-v2&envId=top-interview-150
public class CitationHIndex {

    //Time complexity O(nlogn) and space complexity O(1). It took 5ms.
//    public int hIndex(int[] citations) {
//        Arrays.sort(citations);
//        int n = citations.length;
//        for(int i=0;i<n;i++){
//            if(citations[i] >= n-i){
//                return n-i;
//            }
//        }
//        return 0;
//    }

    //Time complexity O(n) and space complexity O(n). It took 0ms.
    public int hIndex(int[] citations) {
        int n =citations.length;
        int[] count = new int[n+1];
        for (int citation : citations) {
            if (citation >= n) {
                count[count.length - 1]++;
            } else {
                count[citation]++;
            }
        }
        int papers = 0;
        for(int i= count.length-1; i>=0; i--){
            papers+= count[i];

            if(papers >=i) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        CitationHIndex c = new CitationHIndex();
//        int[] arr = {3, 0, 6, 1, 5};
//        int[] arr = {0,0,2};
//        int[] arr = {1,2};
//        int[] arr = {4,4,0,0};
        int[] arr = {1,7,9,4};
        int result = c.hIndex(arr);
        System.out.println(result);
    }
}
