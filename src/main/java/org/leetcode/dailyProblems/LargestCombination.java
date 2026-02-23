package org.leetcode.dailyProblems;

//https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/?envType=daily-question&envId=2024-11-07
public class LargestCombination {
    //Time complexity: O(n) and Space complexity: O(n). It took 162 ms to execute.
//    public int largestCombination(int[] candidates) {
//        Map<Integer, List<Integer>> comb = new HashMap<>();
//        for (int candidate : candidates) {
//            calculateBits(candidate, comb);
//        }
//        System.out.println(comb);
//        int max = 0;
//        for(List<Integer> l : comb.values()){
//            max = Math.max(max, l.size());
//        }
//        return max;
//    }
//
//    private void calculateBits(int candidate, Map<Integer, List<Integer>> comb) {
//        String bits = Integer.toBinaryString(candidate);
//        System.out.println(candidate + " -> "+bits);
//        int count =0;
//        for(int i=bits.length()-1; i>=0; i--){
//            if(bits.charAt(i) == '1'){
//                comb.putIfAbsent(count, new ArrayList<>());
//                comb.get(count).add(candidate);
//            }
//            count++;
//        }
//    }

    //Time complexity: O(n) and Space complexity: O(n). It took 60 ms to execute
    public int largestCombination(int[] candidates) {
        int[] num = new int[24];
        for (int candidate : candidates) {
           String bit = Integer.toBinaryString(candidate);
           int count = 0;
           for(int i=bit.length()-1; i>=0; i--){
               if(bit.charAt(i) == '1'){
                   num[count]++;
               }
               count++;
           }
        }
        int max = 0;
        for(int i : num){
            max = Math.max(max, i);
        }
        return max;
    }

    public static void main(String[] args) {
        LargestCombination largestCombination = new LargestCombination();
        int result = largestCombination.largestCombination(new int[]{16,17,71,62,12,24,14});
        System.out.println(result);
    }
}
