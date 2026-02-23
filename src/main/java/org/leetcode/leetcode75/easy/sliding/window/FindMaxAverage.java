package org.leetcode.leetcode75.easy.sliding.window;

public class FindMaxAverage {
    //Time complexity: O(n) and Space complexity: O(1). It took 1100 ms.
//    public double findMaxAverage(int[] nums, int k) {
//        int counter = 0;
//        int count = 0;
//        double avg = 0;
//        double max = Integer.MIN_VALUE;
//        for(int i=0; i<nums.length; i++){
//            avg+=nums[i];
//            count++;
//            if(count == k && counter < nums.length-k+1){
//                i = counter++;
//                if(max < avg/k)
//                    max = avg/k;
//                avg = 0.0;
//                count=0;
//            }
//        }
//        return max;
//    }
//        public double findMaxAverage(int[] nums, int k) {
//            int end = nums.length;
//            int start = 0;
//            double sum1;
//            double sum2;
//            double max = Integer.MIN_VALUE;
//            while(start < end && start+k <= nums.length && end-k >=0){
//                int[] num1 = Arrays.copyOfRange(nums, start, start+k);
//                int[] num2 = Arrays.copyOfRange(nums, end-k, end);
//                sum1= Arrays.stream(num1).sum();
//                sum2= Arrays.stream(num2).sum();
//                double temp = Math.max(sum1, sum2);
//                if(max < temp) max = temp;
//                start++;
//                end--;
//            }
//            return max/k;
//        }

    //Time complexity: O(n) and Space complexity: O(1). It took 4 ms.
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for(int i=0; i<k; i++){
            sum+=nums[i];
        }
        double max = sum;
        for(int i=k; i<nums.length; i++){
            sum+=nums[i] - nums[i-k];
            max = Math.max(max, sum);
        }

        return max/k;
    }



    public static void main(String[] args) {
        FindMaxAverage findMaxAverage = new FindMaxAverage();
        int[] nums = {5};
        int k = 1;
        double ans = findMaxAverage.findMaxAverage(nums, k);
        System.out.println("Max Average: " + ans);
    }
}
