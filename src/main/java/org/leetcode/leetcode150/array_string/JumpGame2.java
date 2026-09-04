package org.leetcode.leetcode150.array_string;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/jump-game-ii/submissions/2066798970/?envType=study-plan-v2&envId=top-interview-150
public class JumpGame2 {

    // Used Recursion strategy. Time Complexity (O(n^2)) and Space Complexity (O(n))
//    public int jump(int[] nums) {
//        int steps = 0;
//        steps = findMinJump(nums, nums.length-1, steps);
//        return steps;
//    }
//
//    private int findMinJump(int[] nums, int i, int steps) {
//        if(i == 0){
//            return steps;
//        }
//        int farthest = 0;
//        int farthestIndex = 0;
//        for(int j=0; j<i; j++){
//            farthest = Math.max(farthest, j + nums[j]);
//            if(farthest >= i){
//                farthestIndex = j;
//                steps++;
//                break;
//            }
//        }
//        return findMinJump(nums, farthestIndex, steps);
//    }

    // Used inner loop strategy. Time Complexity (O(n^2)) and Space Complexity (O(n))
//    public int jump(int[] nums) {
//        int i = nums.length-1;
//        int steps = 0;
//        while(i>0) {
//            int farthest = 0;
//            int farthestIndex = 0;
//            for(int j=0; j<i; j++){
//                farthest = Math.max(farthest, j + nums[j]);
//                if(farthest >= i){
//                    farthestIndex = j;
//                    steps++;
//                    break;
//                }
//            }
//            i = farthestIndex;
//        }
//        return steps;
//    }

    // Time Complexity O(n), space complexity O(1). It took 1ms.
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;   // end of current jump range
        int farthest = 0;     // farthest index reachable in next jump

        // no need to process last index
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // when we reach current range end, we must take a jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();
        int[] nums = {2, 3, 1, 1, 4};
//        int[] nums = {2,3,0,1,4};
//        int[] nums = {1,2};
        int result = jumpGame2.jump(nums);
        System.out.println(result);
    }
}
