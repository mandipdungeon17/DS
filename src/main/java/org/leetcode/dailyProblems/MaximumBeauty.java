package org.leetcode.dailyProblems;

import java.util.Arrays;
import java.util.Comparator;

//https://leetcode.com/problems/most-beautiful-item-for-each-query/?envType=daily-question&envId=2024-11-12
public class MaximumBeauty {
//    public int[] maximumBeauty(int[][] items, int[] queries) {
//        int k = 0;
//        int max = 0;
//        Map<Integer, Integer> map = new HashMap<>();
//        while(k < queries.length) {
//            if(null == map.get(queries[k])) {
//                for (int[] i : items) {
//                    if (i[0] <= queries[k]) {
//                        max = Math.max(i[1], max);
//                    }
//                }
//                map.put(queries[k], max);
//            }
//            queries[k] = map.get(queries[k]);
//            max = 0;
//            k++;
//        }
//        return queries;
//    }


    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] ans = new int[queries.length];

        // Sort and store max beauty
        Arrays.sort(items, Comparator.comparingInt(a -> a[0]));
        System.out.println(Arrays.deepToString(items));
        int max = items[0][1];
        for (int i = 0; i < items.length; i++) {
            max = Math.max(max, items[i][1]);
            items[i][1] = max;
        }
        System.out.println(Arrays.deepToString(items));
        for (int i = 0; i < queries.length; i++) {
            // answer i-th query
            ans[i] = binarySearch(items, queries[i]);
        }
        return ans;
    }

    private int binarySearch(int[][] items, int targetPrice) {
        int l = 0;
        int r = items.length - 1;
        int maxBeauty = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (items[mid][0] > targetPrice) {
                r = mid - 1;
            } else {
                // Found viable price. Keep moving to right
                maxBeauty = Math.max(maxBeauty, items[mid][1]);
                l = mid + 1;
            }
        }
        return maxBeauty;
    }

    public static void main(String[] args) {
        MaximumBeauty maximumBeauty = new MaximumBeauty();
        int[][] items = {{1,2},{3,2},{2,4},{5,6},{3,5}};
        int[] queries = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(maximumBeauty.maximumBeauty(items, queries)));
    }
}
